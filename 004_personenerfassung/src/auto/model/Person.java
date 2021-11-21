package auto.model;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class Person implements Serializable{
  private String svnr;
  private String nname;
  private String vname;
  private Double groesse;
  private LocalDate gebdate;
  private Geschlecht gschlcht;
  
  private static List<Person> catalog = new LinkedList<>();

  public static void serialisieren() throws IOException, ClassNotFoundException {

    try {
      FileInputStream fileInputStream
              = new FileInputStream("Serial.txt");
      ObjectInputStream objectInputStream
              = new ObjectInputStream(fileInputStream);
      catalog = (List) objectInputStream.readObject();

      objectInputStream.close();
    }catch (FileNotFoundException ex) {
      //Erster Aufruf nicht vorhanden!
    }
  }
  public static void deserialisieren() throws IOException {

    FileOutputStream fileOutputStream
            = new FileOutputStream("Serial.txt");
    ObjectOutputStream objectOutputStream
            = new ObjectOutputStream(fileOutputStream);
    objectOutputStream.writeObject(catalog);
    objectOutputStream.flush();
    objectOutputStream.close();

  }

  
  
  public Person() {
//    svnr = null;
//    nname = null;
//    vname = null;
//    gebDat = null;
//    geschlecht = null;
  }
  
  
  public String getSvnr() {
    return svnr;
  }
  
  public void setSvnr(String svnr) throws PersonException {
    if (svnr == null) {
      throw new PersonException("Sozialversicherungsnummer muss angegeben werden!");
    }
    
    if (!svnr.matches("[0-9]{4}[0-3][0-9][0-1][0-9][0-9]{2}")) {
      throw new PersonException("Sozialversicherungsnummer muss im Format nnnnttmmjj sein!");
    }
    
    this.svnr = svnr;
  }
  
  
  public String getNname() {
    return nname;
  }
  
  public void setNname(String nname) throws PersonException {
    if (nname == null) {
      throw new PersonException("Nachname muss angegeben werden!");
    }
    
    if (nname.length() < 2) {
      throw new PersonException("Nachname muss länger als 2 Zeichen sein!");
    }
    
    this.nname = nname;
  }
  
  
  public String getVname() {
    return vname;
  }
  
  
  public void setVname(String vname) throws PersonException {
    if (vname != null && vname.length() == 0) {
      vname = null;
    }
    
    if (vname != null && vname.length() < 2) {
      throw new PersonException("Wenn der Vorname angegeben wird, dann muss er länger als 2 Zeichen sein!");
    }
    
    this.vname = vname;
  }
  
  
  public Number getGroesse() {
    return groesse;
  }
  
  public void setGroesse(Double groesse) throws PersonException {
    if (groesse != null
        && (groesse < 0.5 || groesse > 2.5)) {
      throw new PersonException("Wenn die Größe angegeben wird, muss sie zwischen 0,5 m und 2,5 m sein ("
          + groesse + ")!");
    }
    
    this.groesse = groesse;
  }

  public LocalDate getGebdate() {
    return gebdate;
  }

  public void setGebdate(LocalDate gebdate) throws PersonException {
    if(gebdate !=null
    && (gebdate.isAfter(LocalDate.now())) || gebdate.isBefore(LocalDate.of(1900,1,1))){
      throw new PersonException("Wenn das Geburtsdatum angegeben wird, darf es nicht vor 1900 und dem heutigen Datum sein ("
      +gebdate.toString()+")!");
    }


    this.gebdate = gebdate;
  }

  public Geschlecht getGschlcht() {
    return gschlcht;
  }

  public void setGschlcht(Geschlecht gschlcht) {


    this.gschlcht = gschlcht;
  }

  @Override
  public String toString() {
    return getSvnr() + "/"
        + getNname() + "/"
        + getVname() + "/"
        + getGroesse() + "/";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return svnr.equals(person.svnr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(svnr);
  }

  public void save() {
    if(catalog.contains(this)){
      catalog.remove(this);
    }

    catalog.add(this);
    System.out.println(catalog);
  }
}
