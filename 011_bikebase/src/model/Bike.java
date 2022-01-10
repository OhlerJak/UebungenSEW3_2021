package model;

import serial.Catalog;

import java.io.Serializable;

public class Bike implements Serializable {
  // Attribute
  String rahmennr;
  String markeType;
  String farbe;


  // Konstruktor
  public Bike(String rahmennummer, String markeType, String farbe) throws BikeExecption {
    setRahmennr(rahmennummer);
    setMarkeType(markeType);
    setFarbe(farbe);
  }

  public Bike(String rahmennr, String markeType) throws BikeExecption {
    this(rahmennr,markeType, null);
  }

  // Getter und Setter

  public String getRahmennr() {
    return rahmennr;
  }

  public void setRahmennr(String rahmennr) throws BikeExecption {

    if(rahmennr.length()<5){
      throw new BikeExecption("Rahmennummer zu kurz (min 5)");
    }
    this.rahmennr = rahmennr;
  }

  public String getMarkeType() {
    return markeType;
  }

  public void setMarkeType(String markeType) throws BikeExecption {
    if(markeType.length()<3){
      throw new BikeExecption("Type zu kurz (min 3)");
    }

    this.markeType = markeType;
  }

  public String getFarbe() {
    return farbe;
  }

  public void setFarbe(String farbe) {
    this.farbe = farbe;
  }

  // Methoden
  public void save(){
    Catalog.getInstance().save(this);
  }

  public static Bike select(String rahmennr){
     return Catalog.getInstance().selectBikeByRahmennr(rahmennr);
  }


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
}
