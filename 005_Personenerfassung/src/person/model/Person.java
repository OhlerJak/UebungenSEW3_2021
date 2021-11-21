package person.model;


import serial.Catalog;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Model Person.
 * Objekte dieser Klasse können in einem Objektkatalog de/serialisiert werden.
 */

public final class Person implements Serializable {
  private String svnr;
  private String nname;
  private String vname;
  private LocalDate gebDat;
  private Double groesse;
  //private String geschlecht;
  private Geschlecht geschlecht;
  
  // Objektkatalog
  private static Catalog catalog;
  
  
  /**
   * Konstruktor
   */
  public Person() {
//    svnr = null;
//    nname = null;
//    vname = null;
//    gebDat = null;
//    geschlecht = null;
  }
  
  
  public static Catalog getCatalog() {
    return catalog;
  }
  
  public static void setCatalog(Catalog catalog) {
    Person.catalog = catalog;
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
  
  
  public LocalDate getGebDat() {
    return gebDat;
  }
  
  public void setGebDat(LocalDate gebDat) throws PersonException {
    if (gebDat == null) {
      throw new PersonException("Geburtsdatum muss angegeben werden!");
    }
    
    if (gebDat.isBefore(LocalDate.now().minusYears(120)) || gebDat.isAfter(LocalDate.now())) {
      throw new PersonException("Das Geburtsdatum darf nicht mehr als 120 Jahre in der Vergangenheit oder in der Zukunft liegen!");
    }
    
    this.gebDat = gebDat;
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
  
  
  public Geschlecht getGeschlecht() {
    return geschlecht;
  }
  
  public void setGeschlecht(Geschlecht geschlecht) throws PersonException {
    if (geschlecht == null) {
      throw new PersonException("Geschlecht muss angegeben werden!");
    }
    
    this.geschlecht = geschlecht;
  }
  
  
  @Override
  public String toString() {
    return getSvnr() + "/"
        + getNname() + "/"
        + getVname() + "/"
        + getGebDat() + "/"
        + getGroesse() + "/"
        + getGeschlecht();
  }
  
  
  /**
   * Personen mit der gleichen SVNR sind gleich!
   *
   * @param o anderes Objekt
   * @return true, wenn die this.svnr == o.svnr
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    
    Person person = (Person) o;
    
    return svnr.equals(person.svnr);
  }
  
  
  /**
   * Sichert die Person im Objektkatalog.
   */
  public void save() {
    catalog.save(this);
    System.out.println(catalog);
  }
}
