package grafikframe;

import at.wima.grafx.Grafx;
import at.wima.grafx.GrafxException;
import grafikobjekte.GrafikKomponente;
import grafikobjekte.Kreis;
import grafikobjekte.Linie;
import grafikobjekte.Mantschkerl;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Fenster zum Darstellen bestimmter Grafikobjekte.
 * Es können beliebig viele Kreise, Linien und Männchen dargestellt werden.
 */
public class GrafikFrame {

  private final List<GrafikKomponente> komponente;
  
  /**
   * Konstruktor.
   * Erzeugt ein Fenster in Standardgröße (300x200) und stellt Vordergrundfarbe
   * (Schwarz) und Hintergrundfarbe (Grau) ein.
   *
   * @throws at.wima.grafx.GrafxException
   */
  public GrafikFrame() throws GrafxException {
    Grafx.createDrawing("Grafikobjekte", 300, 200, Color.BURLYWOOD);
    
    // Attribute initialisieren
    komponente= new LinkedList<>();
  }

  /**
   * Komponente hinzufügen.
   *
   * @param komp Kommponente
   * @throws at.wima.grafx.GrafxException
   */
  public void addKomp(GrafikKomponente komp) throws GrafxException {
    if (!this.komponente.contains(komp)) {
      this.komponente.add(komp);
      paint();
    }
  }
  
  /**
   * Stellt das Fenster dar.
   *
   * @throws at.wima.grafx.GrafxException
   */
  public void paint() throws GrafxException {

    for(GrafikKomponente k : komponente){
      k.draw();
    }

    Grafx.refresh();
  }
}
