package main;


import sitzplan.Klasse;
import sitzplan.Schueler;
import sitzplan.Sitz;

public class TheMainConsole {
  
  public static void main(String[] args) {
    Klasse dreiahit = new Klasse("3AHIT");
    
    dreiahit.setzen(new Sitz(2, 2), new Schueler("Musterfrau"));
    dreiahit.setzen(new Sitz(1, 1), new Schueler("Huber"));
    dreiahit.setzen(new Sitz(1, 2), new Schueler("Maier"));
    dreiahit.setzen(new Sitz(2, 1), new Schueler("Pospisil"));
    dreiahit.setzen(new Sitz(1, 3), null);
    dreiahit.setzen(new Sitz(1, 4), new Schueler("Drapac"));
    
    dreiahit.printSitzplan();
  }
}
