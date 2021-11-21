package grafikobjekte;


/**
 * Punkt.
 * Kann nicht angezeigt werden - dient lediglich zur Angabe von Koordinaten im
 * GrafikFrame.
 */
public class Punkt {
  private int x;
  private int y;
  
  
  /**
   * Konstruktor.
   *
   * @param x X-Koordinate
   * @param y Y-Koordinate
   * @throws GrafikException Wenn unplausible / falsche Parameter
   */
  public Punkt(int x, int y) throws GrafikException {
    setX(x);
    setY(y);
  }
  
  
  /**
   * Setter für X-Koordinate.
   *
   * @param x X-Koordinate
   * @throws GrafikException Wenn unplausible / falsche Parameter
   */
  public final void setX(int x) throws GrafikException {
    if (x < 0) {
      throw new GrafikException("X-Koordinate muss zumindest 0 sein!");
    }
    this.x = x;
  }
  
  
  /**
   * Setter für Y-Koordinate.
   *
   * @param y Y-Koordinate
   * @throws GrafikException Wenn unplausible / falsche Parameter
   */
  public final void setY(int y) throws GrafikException {
    if (y < 0) {
      throw new GrafikException("Y-Koordinate muss zumindest 0 sein!");
    }
    this.y = y;
  }
  
  
  /**
   * Getter für X-Koordinate.
   *
   * @return X-Koordinate
   */
  public int getX() {
    return x;
  }
  
  
  /**
   * Getter für Y-Koordinate.
   *
   * @return Y-Koordinate
   */
  public int getY() {
    return y;
  }
  
  
  /**
   * Ermittelt / definiert Gleichheit.
   *
   * @param other anderer Punkt
   * @return true, wenn other deckungsgleich mit diesem Punkt ist
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
    
    Punkt otherPunkt = (Punkt) other;
    
    // gleiche Attributswerte (Wertetypen)?
    return this.x == otherPunkt.x
        && this.y == otherPunkt.y;
  }
}
