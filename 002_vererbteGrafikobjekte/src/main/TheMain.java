package main;


import at.wima.grafx.Grafx;
import at.wima.grafx.GrafxException;
import grafikframe.GrafikFrame;
import grafikobjekte.*;


public class TheMain {
  
  public static void main(String[] args) {
    try {
      GrafikFrame grafikFrame = new GrafikFrame();

      //Für Testzwecke:

      grafikFrame.addKomp(new Kreis(new Punkt(50, 70), 10));
      //grafikFrame.addKreis(new Kreis(new Punkt(-3, 30), 10));
      //grafikFrame.addLinie(new Linie(new Punkt(50, 80), new Punkt(50, 120)));
      //grafikFrame.addLinie(new Linie(new Punkt(50, 80),new Punkt(50, 80)));
      
      //grafikFrame.addLinie(new Linie(new Punkt(50, 120), new Punkt(30, 150)));
      grafikFrame.addKomp(new Linie(new Punkt(50, 120), new Punkt(70, 150)));
      
      grafikFrame.addKomp(new Linie(new Punkt(50, 100), new Punkt(30, 80)));
      //grafikFrame.addLinie(new Linie(new Punkt(50, 100), new Punkt(70, 80)));
      
      grafikFrame.addKomp(new Mantschkerl(new Punkt(80, 80)));
      
      grafikFrame.addKomp(new Mantschkerl(new Punkt(120, 40), 0.5));
      
      grafikFrame.addKomp(new Mantschkerl(new Punkt(180, 30), 1.6));

    }
    catch (GrafikException ex) {
      System.err.println(ex.getMessage());
    }
    catch (GrafxException e) {
      System.err.println(e.getMessage());
    }
  }
}
