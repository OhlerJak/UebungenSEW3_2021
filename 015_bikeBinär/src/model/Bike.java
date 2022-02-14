package model;

import serial.Catalog;

import java.io.Serializable;

public class Bike implements Serializable {
  private String rahmennr;
  private String markeType;
  private String farbe;
  
  public Bike(String rahmennr) throws BikeExecption {
    setRahmennr(rahmennr);
  }
  
  public static Bike select(String rahmennr) {
    return Catalog.getInstance().selectBikeByRahmennr(rahmennr);
  }
  
  public String getRahmennr() {
    return rahmennr;
  }
  
  public void setRahmennr(String rahmennr) throws BikeExecption {
    if (rahmennr == null) {
      rahmennr = "";
    }
    if (rahmennr.length() < 5) {
      throw new BikeExecption("Rahmennummer muss zumindest 5 Stellen haben!");
    }
    this.rahmennr = rahmennr;
  }
  
  public String getMarkeType() {
    return markeType;
  }
  
  public void setMarkeType(String markeType) throws BikeExecption {
    if (markeType == null) {
      markeType = "";
    }
    if (markeType.length() < 3) {
      throw new BikeExecption("Marke und Type muss zumindest 3 Stellen haben!");
    }
    this.markeType = markeType;
  }
  
  public String getFarbe() {
    return farbe;
  }
  
  public void setFarbe(String farbe) {
    if (farbe == null) {
      farbe = "";
    }
    this.farbe = farbe;
  }
  
  public void save() {
    Catalog.getInstance().save(this);
  }
  
  @Override
  public String toString() {
    return "Bike{" +
        "rahmennr='" + rahmennr + '\'' +
        ", markeType='" + markeType + '\'' +
        ", farbe='" + farbe + '\'' +
        '}';
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
