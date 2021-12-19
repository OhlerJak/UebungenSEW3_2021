package serial;

import person.model.Person;
import todo.model.ToDo;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Schüler-Katalog
 * Dient der Persistierung von Schüler-Objekten.
 */
public class Catalog {
  /**
   * Alle Schüler-Objekte
   */
  private List<Serializable> objects;
  
  /**
   * Konstruktor.
   */
  public Catalog() {
    this.objects = new LinkedList<>();
  }
  
  /**
   * Persistieren aller bislang geSaveTer Schüler-Objekte.
   * Mehr noch: nicht nur die Person, für die explizit save() aufgerufen wurde, werden gespeichert, sondern auch alle
   * jene Obekte, auf die sie verweisen (würden).
   */
  public void persist() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("catalog.ser"))) {
      oos.writeObject(objects);
    }
    catch (IOException e) {
      // kann nicht auftreten
      e.printStackTrace();
    }
  }
  
  /**
   * Wiederherstellen aller zuletzt persistierter Person-Objekte.
   * ... inlkusive aller Beziehungen zwischen ihnen.
   */
  public void restore() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("catalog.ser"))) {
      objects = (List<Serializable>) ois.readObject();
    }
    catch (FileNotFoundException e) {
      System.out.println("No Catalog found! Nothing to restore!");
    }
    catch (InvalidClassException e) {
      System.out.println("Catalog contains class description of different version! Nothing to restore!");
    }
    catch (ClassNotFoundException e) {
      System.out.println("Catalog contains class description of unknown class! Nothing to restore!");
    }
    catch (IOException e) {
      // kann nicht auftreten
      e.printStackTrace();
    }
  }
  
  
  /**
   * Person-Objekt nach Atrribut svnr selektieren.
   * Entsprechen mehrere Objekte dem Kriterium, so wird das erste zurückgegeben.
   *
   * @param svnr Selektionskriterium
   * @return Person-Objekt
   */
  public Person selectBySvnr(String svnr) {
    Person found = null;
    
    int i = 0;
    while (i < objects.size() && (!(objects.get(i) instanceof Person) || !((Person)objects.get(i)).getSvnr().equals(svnr) )){
      i++;
    }
    
    if (i < objects.size()) {
      found = (Person) objects.get(i);
    }
    
    return found;
  }
  
  /**
   * Speichert das Person-Objekt im Katalog.
   * Existiert bereits ein gleiches (!) im Catalog, so wird das existierende zuerst entfernt.
   *
   *
   */
  public void save(Serializable obj) {
    if (objects.contains(obj)) {
      // Objekt mit gleicher SVNR existiert schon im Catalog
      objects.remove(obj);
    }
    objects.add(obj);
  
    System.out.println(objects);
  }

  public void remove(Serializable obj){
    if (objects.contains(obj)) {
      // Objekt mit gleicher SVNR existiert schon im Catalog
      objects.remove(obj);
    }

  }

  public List selectByPerson(Person person){
    List<ToDo> todos = new LinkedList<>();

    for(int i = 0;i<objects.size();i++){
      if(objects.get(i) instanceof ToDo && ((ToDo)objects.get(i)).getVerantwortlicher()==person){
        todos.add((ToDo) objects.get(i));

      }

    }
    return todos;
  }
  
  
  @Override
  public String toString() {
    return "Catalog{" +
        "objects=" + objects +
        '}';
  }
}
