package grafikobjekte;

import at.wima.grafx.Grafx;
import at.wima.grafx.GrafxException;
import javafx.scene.paint.Color;

/**
 * Grafikobjekt Kreis.
 * Kann im GrafikFrame angezeigt werden.
 */
public class Kreis extends GrafikKomponente{
  
  private int r;
  private Punkt mittelpunkt;
  
  /**
   * Kontruktor.
   *
   * @param mittelpunkt Mittelpunkt
   * @param r           Radius
   * @throws GrafikException Wenn unplausible / falsche Parameter
   */
  public Kreis(Punkt mittelpunkt, int r) throws GrafikException {
    setMittelpunkt(mittelpunkt);
    setR(r);
  }
  
  private void setR(int r) throws GrafikException {
    if (r < 1) {
      throw new GrafikException("Radius muss zumindest 1 sein!");
    }
    this.r = r;
    if(!isInitialized()&&mittelpunkt!=null){
      setInitialized(true);
    }
  }
  
  private void setMittelpunkt(Punkt mittelpunkt) throws GrafikException {
    if (mittelpunkt == null) {
      throw new GrafikException("Mittelpunkt muss angegeben werden!");
    }
    this.mittelpunkt = mittelpunkt;
    if(!isInitialized()&&r!=0){
      setInitialized(true);
    }
  }
  
  /**
   * Getter für Radius.
   *
   * @return Radius
   */
  public int getR() {
    return r;
  }
  
  /**
   * Getter für Mittelpunkt.
   *
   * @return Mittelpunkt
   */
  public Punkt getMittelpunkt() {
    return mittelpunkt;
  }
  
  /**
   * Ermittelt / definiert Gleichheit.
   *
   * @param other anderer Kreis
   * @return true, wenn other deckungsgleich mit diesem Kreis ist
   */
  @Override
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
    
    Kreis otherKreis = (Kreis) other;
    
    // gleiche Attributswerte (Referenztypen)?
    if ((this.mittelpunkt == null && otherKreis.mittelpunkt != null)
        || (this.mittelpunkt != null && !this.mittelpunkt.equals(otherKreis.mittelpunkt))) {
      return false;
    }
    
    // gleiche Attributswerte (Wertetypen)?
    return this.r == otherKreis.r;
  }
  
  /**
   * Stellt Kreis dar.
   *
   * @throws at.wima.grafx.GrafxException
   */
  public void draw() throws GrafxException {
    if (isInitialized()) {
      Grafx.delEllipse(toString());
      Grafx.addEllipse(toString(), mittelpunkt.getX() - r, mittelpunkt.getY() - r, 2 * r, 2 * r, Color.BLACK, false);
    }
  }
}
