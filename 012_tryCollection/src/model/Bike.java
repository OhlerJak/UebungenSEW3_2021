package model;



import java.io.Serializable;

public class Bike implements Serializable {
  // Attribute
  String rahmennr;
  String markeType;
  String farbe;


  // Konstruktor
  public Bike(String rahmennummer, String markeType, String farbe){
    setRahmennr(rahmennummer);
    setMarkeType(markeType);
    setFarbe(farbe);
  }

  public Bike(String rahmennr, String markeType) {
    this(rahmennr,markeType, "");
  }

  // Getter und Setter

  public String getRahmennr() {
    return rahmennr;
  }

  public void setRahmennr(String rahmennr)  {

    this.rahmennr = rahmennr;
  }

  public String getMarkeType() {
    return markeType;
  }

  public void setMarkeType(String markeType) {
    this.markeType = markeType;
  }

  public String getFarbe() {
    return farbe;
  }

  public void setFarbe(String farbe) {
    this.farbe = farbe;
  }

  // Methoden


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    
    Bike bike = (Bike) o;
    
    return rahmennr.equals(bike.rahmennr);
  }

  @Override
  public String toString() {
    return rahmennr+" | "+ markeType+" | "+farbe;
  }
}
