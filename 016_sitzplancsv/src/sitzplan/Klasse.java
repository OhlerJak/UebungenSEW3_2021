package sitzplan;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.TreeMap;


/**
 * Einfache Klasse.
 */

public class Klasse {
  private final String bezeichnung;
  private final TreeMap<Sitz, Schueler> sitzplan;


  /**
   * Konstruktor aus Bezeichnung.
   *
   * @param bezeichnung
   */
  public Klasse(String bezeichnung) {
    this.bezeichnung = bezeichnung;
    sitzplan = new TreeMap<>();
  }


  /**
   * Getter für Bezeichnung.
   *
   * @return Bezeichnung.
   */
  public String getBezeichnung() {
    return bezeichnung;
  }


  /**
   * Schüler auf Sitz platzieren.
   *
   * @param sitz     Sitz
   * @param schueler Schüler
   */
  public void setzen(Sitz sitz, Schueler schueler) {
    this.sitzplan.put(sitz, schueler);
  }


  /**
   * Sitzplan ausgeben
   */
  public void printKlassenliste() {
    System.out.println("Sitzplan der Klasse " + bezeichnung);

    int rowCur;
    rowCur = -1;

    for (Sitz s : sitzplan.navigableKeySet()) {
      if (rowCur != s.getRow()) {
        System.out.println("");
        rowCur = s.getRow();
      }

      String name = "";
      if (sitzplan.get(s) != null) {
        name = sitzplan.get(s).getName();
      } else {
        name = "<frei>";
      }
      System.out.print(name + "\t");
    }
    System.out.println();
  }

  private void load(File file){
    try(FileReader input = new FileReader(file)){
      String text = "";


      while (input.ready()) {
        text += ((char) input.read());

      }




    }catch (IOException ex){
      ex.printStackTrace();
    }




}

private void printKlassenliste(OutputStream out){

}


}
