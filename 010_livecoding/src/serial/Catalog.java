package serial;

import model.Benutzer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;




public class Catalog {


  private  List<Benutzer> objects;
  private static Catalog catalog;

  public static Catalog getInstance(){
    if(catalog==null){
      catalog = new Catalog();
    }
  return catalog;
  }

  private Catalog(){
    objects = new LinkedList<>();
  }

  public void persist() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("catalog.ser"))) {
      oos.writeObject(objects);
    }
    catch (IOException e) {
      // kann nicht auftreten
      e.printStackTrace();
    }
  }




  public void restore() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("catalog.ser"))) {
      objects = (List<Benutzer>) ois.readObject();
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


  public  Benutzer findBenutzerByEmail(String email) {
    int i = 0;
    while (i < objects.size() && !objects.get(i).getEmail().equals(email)) {
      i++;
    }
    if (i >= objects.size()) {
      return null;
    }
    else {
      return objects.get(i);
    }
  }
  
  public  void save(Benutzer benutzer) {
    if (objects.contains(benutzer)) {
      objects.remove(benutzer);
    }
    objects.add(benutzer);
  }
}
