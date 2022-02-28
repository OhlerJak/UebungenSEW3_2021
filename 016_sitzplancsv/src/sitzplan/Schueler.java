package sitzplan;


/**
 * Ein Schüler. Beliebig um Attribute erweiterbar.
 */
public class Schueler {
  private final String name;
  //private final double height; other attributes as you need
  
  
  /**
   * Konstruktor
   * <p>
   *
   * @param name Nachname
   */
  public Schueler(String name) {
    this.name = name; // no setter, sorry ;-) 
  }
  
  
  /**
   * Getter für Name
   * <p>
   *
   * @return Nachname
   */
  public String getName() {
    return name;
  }
  
  
  /**
   * Stringrepräsentation
   * <p>
   *
   * @return Stringrepräsentation
   */
  @Override
  public String toString() {
    return name;
  }
}
