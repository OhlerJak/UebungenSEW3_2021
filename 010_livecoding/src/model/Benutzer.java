package model;

import serial.Catalog;

import java.io.Serializable;

public class Benutzer implements Serializable {
  private String email;
  private String nachname;
  private String vorname;
  
  public Benutzer(String email, String nachname, String vorname) throws RegisterException {
    setEmail(email);
    setNachname(nachname);
    setVorname(vorname);
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getNachname() {
    return nachname;
  }
  
  public void setNachname(String nachname) throws RegisterException {
    if (nachname == null) {
      nachname = "";
    }
    if (nachname.length() <= 0) {
      throw new RegisterException("Nachname muss angegeben werden!");
    }
    this.nachname = nachname;
  }
  
  public String getVorname() {
    return vorname;
  }
  
  public void setVorname(String vorname) {
    this.vorname = vorname;
  }
  
  public static Benutzer find(String email) {
    return Catalog.getInstance().findBenutzerByEmail(email);
  }
  
  public void save() {
    Catalog.getInstance().save(this);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    
    Benutzer benutzer = (Benutzer) o;
  
    return email != null ? email.equals(benutzer.email) : benutzer.email == null;
  }
  
  @Override
  public String toString() {
    return "Benutzer{" +
        "email='" + email + '\'' +
        ", nachname='" + nachname + '\'' +
        ", vorname='" + vorname + '\'' +
        '}';
  }
}
