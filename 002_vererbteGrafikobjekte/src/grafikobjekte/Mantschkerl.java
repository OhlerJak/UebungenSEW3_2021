package grafikobjekte;

import at.wima.grafx.GrafxException;

/**
 * Grafikobjekt Männchen.
 * Mit Hilfe des linken, oberen Punktes und eines Zoom-Faktors (1,0 = 100%, 0,5
 * = 50%, 1,2 = 120%) wird ein Männchen spezifiziert, das im GrafikFrame
 * angezeigt werden kann.
 * <br>
 * Kann im GrafikFrame angezeigt werden.
 */
public class Mantschkerl extends GrafikKomponente{
  
  private Punkt linksOben;
  private double zoom;
  //private int breite;
  private Kreis kopf;
  private Linie rumpf;
  private Linie armLinks;
  private Linie armRechts;
  private Linie beinLinks;
  private Linie beinRechts;
  
  /**
   * Konstruktor.
   * Erstellt ein Männchen in Standardgröße.
   *
   * @param linksOben Linker, oberer Punkt
   * @throws GrafikException Wenn unplausible / falsche Parameter
   */
  public Mantschkerl(Punkt linksOben) throws GrafikException {
    this(linksOben, 1.0);
  }
  
  /**
   * Konstruktor.
   *
   * @param linksOben Linker, oberer Punkt
   * @param zoom      Zoom-Faktors (1,0 = 100%, 0,5 = 50%, 1,2 = 120%, ...)
   * @throws GrafikException Wenn unplausible / falsche Parameter
   */
  public Mantschkerl(Punkt linksOben, double zoom) throws GrafikException {
    setLinksOben(linksOben);
    setZoom(zoom);
    
    int x = linksOben.getX();
    int y = linksOben.getY();
    
    kopf = new Kreis(createZoomedPoint(x + 20, y + 10), round(10 * zoom));
    
    rumpf = new Linie(createZoomedPoint(x + 20, y + 20),
        createZoomedPoint(x + 20, y + 60));
    
    armLinks = new Linie(createZoomedPoint(x + 20, y + 40),
        createZoomedPoint(x, y + 20));
    armRechts = new Linie(createZoomedPoint(x + 20, y + 40),
        createZoomedPoint(x + 40, y + 20));
    
    beinLinks = new Linie(createZoomedPoint(x + 20, y + 60),
        createZoomedPoint(x, y + 90));
    beinRechts = new Linie(createZoomedPoint(x + 20, y + 60),
        createZoomedPoint(x + 40, y + 90));
  }
  
  private void setLinksOben(Punkt linksOben) throws GrafikException {
    if (linksOben == null) {
      throw new GrafikException("Linker, oberer Punkt muss angegeben werden!");
    }
    this.linksOben = linksOben;
    if(!isInitialized()&&zoom!=0){
      setInitialized(true);
    }
  }
  
  private void setZoom(double zoom) throws GrafikException {
    if (zoom <= 0) {
      throw new GrafikException("Zoomfaktor muss größer 0 sein! ");
    }
    this.zoom = zoom;
    if(!isInitialized()&&linksOben!=null){
      setInitialized(true);
    }
  }
  
  /**
   * Getter für linken, oberen Punkt
   *
   * @return linken, oberen Punkt
   */
  public Punkt getLinksOben() {
    return linksOben;
  }
  
  /**
   * Getter für Zoom-Faktor.
   *
   * @return Zoom-Faktor
   */
  public double getZoom() {
    return zoom;
  }
  
  private Punkt createZoomedPoint(int x, int y) throws GrafikException {
    return new Punkt(round(zoom * (x - linksOben.getX()) + linksOben.getX()),
        round(zoom * (y - linksOben.getY()) + linksOben.getY()));
  }
  
  private int round(double val) {
    return (int) Math.round(val);
  }
  
  /**
   * Stellt Männchen dar.
   *
   * @throws at.wima.grafx.GrafxException
   */
  public void draw() throws GrafxException {
    if (isInitialized()) {
      kopf.draw();

      rumpf.draw();

      armLinks.draw();
      armRechts.draw();

      beinLinks.draw();
      beinRechts.draw();
    }
  }
}
