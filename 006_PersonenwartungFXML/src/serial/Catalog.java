package serial;

import person.model.Person;

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
  private List<Person> objects;
  
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
      objects = (List<Person>) ois.readObject();
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
    while (i < objects.size() && !objects.get(i).getSvnr().equals(svnr)) {
      i++;
    }
    
    if (i < objects.size()) {
      found = objects.get(i);
    }
    
    return found;
  }
  
  /**
   * Speichert das Person-Objekt im Katalog.
   * Existiert bereits ein gleiches (!) im Catalog, so wird das existierende zuerst entfernt.
   *
   * @param person Objekt
   */
  public void save(Person person) {
    if (objects.contains(person)) {
      // Objekt mit gleicher SVNR existiert schon im Catalog
      objects.remove(person);
    }
    objects.add(person);
  
    System.out.println(objects);
  }
  
  
  @Override
  public String toString() {
    return "Catalog{" +
        "objects=" + objects +
        '}';
  }
}
