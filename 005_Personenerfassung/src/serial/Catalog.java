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
  public Catalog()  {
    this.objects = new LinkedList<>();
//    this.restore();
  }
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
//    this.persist();
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
      // Tritt nur beim ersten Mal auf
      e.printStackTrace();
    }
  }
  
  /**
   * Wiederherstellen aller zuletzt persistierter Person-Objekte.
   * ... inlkusive aller Beziehungen zwischen ihnen.
   */
  public void restore()  {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("catalog.ser"))) {
      objects = (List<Person>) ois.readObject();
    }
    catch (ClassNotFoundException e) {
      // kann nicht auftreten
      e.printStackTrace();
    }
    catch (IOException e) {
      // kann nicht auftreten
      e.printStackTrace();
    }
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
  }
  
  @Override
  public String toString() {
    return "Catalog{" +
        "objects=" + objects +
        '}';
  }
}
