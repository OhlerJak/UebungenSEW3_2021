package serial;

import model.Bike;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Objekt-Katalog
 * Dient der Persistierung von Objekten.
 */
public class Catalog {
  private List<Bike> objects;
  
  
  private static Catalog instance;
  
  /**
   * Konstruktor.
   */
  private Catalog() {
    this.objects = new LinkedList<>();
  }
  
  
  public static Catalog getInstance() {
    if (instance == null) {
      instance = new Catalog();
    }
    return instance;
  }
  
  
  /**
   * Persistieren aller bislang geSaveTer Objekte.
   * ... inklusive aller Beziehungen zwischen ihnen.
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
   * Wiederherstellen aller zuletzt persistierter Objekte.
   * ... inlkusive aller Beziehungen zwischen ihnen.
   */
  public void restore() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("catalog.ser"))) {
      objects = (List<Bike>) ois.readObject();
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
   * Speichert ein Objekt im Katalog.
   * Existiert bereits ein gleiches (!) im Catalog, so wird das existierende zuerst entfernt.
   *
   * @param bike zu speicherndes Objekt
   */
  public void save(Bike bike) {
    // In der Liste aller Objekte updaten oder inserten
    objects.remove(bike);
    objects.add(bike);
  }
  
  
  @Override
  public String toString() {
    return "Catalog{" +
        "serObjects=" + objects +
        '}';
  }
  
  public Bike selectBikeByRahmennr(String rahmennr) {
    Bike found = null;
    
    int i = 0;
    while (i < objects.size() && !objects.get(i).getRahmennr().equals(rahmennr)) {
      i++;
    }
  
    if (i < objects.size()) {
      found = objects.get(i);
    }
    
    return found;
  }
}
