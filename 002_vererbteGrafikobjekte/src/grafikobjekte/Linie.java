package grafikobjekte;

import at.wima.grafx.Grafx;
import at.wima.grafx.GrafxException;
import javafx.scene.paint.Color;

/**
 * Grafikobjekt Linie.
 * Kann im GrafikFrame angezeigt werden.
 */
public class Linie extends GrafikKomponente{
  
  private Punkt start;
  private Punkt ende;
  
  /**
   * Konstruktor.
   *
   * @param start Startpunkt
   * @param ende  Endpunkt
   * @throws GrafikException Wenn unplausible / falsche Parameter
   */
  public Linie(Punkt start, Punkt ende) throws GrafikException {
    setStart(start);
    setEnde(ende);
  }
  
  private void setStart(Punkt start) throws GrafikException {
    if (start == null) {
      throw new GrafikException("Startpunkt muss angegeben werden!");
    }
    
    if (ende != null && start.equals(ende)) {
      throw new GrafikException("Startpunkt und Endpunkt müssen verschieden sein!");
    }
    
    this.start = start;
    if(!isInitialized()&&ende!=null){
      setInitialized(true);
    }
  }
  
  private void setEnde(Punkt ende) throws GrafikException {
    if (ende == null) {
      throw new GrafikException("Endpunkt muss angegeben werden!");
    }
    
    if (start != null && start.equals(ende)) {
      throw new GrafikException("Startpunkt und Endpunkt müssen verschieden sein!");
    }
    
    this.ende = ende;
    if(!isInitialized()&&start!=null){
      setInitialized(true);
    }
  }
  
  /**
   * Getter für Startpunkt.
   *
   * @return Startpunkt
   */
  public Punkt getStart() {
    return start;
  }
  
  /**
   * Getter für Endpunkt.
   *
   * @return Endpunkt
   */
  public Punkt getEnde() {
    return ende;
  }
  
  /**
   * Ermittelt / definiert Gleichheit.
   *
   * @param other andere Linie
   * @return true, wenn other deckungsgleich mit dieser Linie ist
   */
  public boolean equals(Object other) {
    // selbes Objekt?
    if (this == other) {
      return true;
    }
    
    // Existiert other?
    if (other == null) {
      return false;
    }
    
    // Gleiche Klasse?
    if (this.getClass() != other.getClass()) {
      return false;
    }
    
    Linie otherLinie = (Linie) other;
    
    // gleiche Attributswerte (Referenztypen)?
    // sicher ungleich null, daher einfach!
    if (!this.start.equals(otherLinie.start)) {
      return false;
    }
    
    if (!this.ende.equals(otherLinie.ende)) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Stellt Linie dar.
   *
   * @throws at.wima.grafx.GrafxException
   */
  public void draw() throws GrafxException {
    if(isInitialized()) {
      Grafx.delPolyline(toString());
      Grafx.addPolylinePoint(toString(), start.getX(), start.getY(), Color.BLACK);
      Grafx.addPolylinePoint(toString(), ende.getX(), ende.getY(), Color.BLACK);
    }
  }
}
