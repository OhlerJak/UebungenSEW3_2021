package sitzplan;

/**
 * Ein Sitzplatz.
 */
public class Sitz implements Comparable {
  
  private final int row;
  private final int seat;
  
  /**
   * Konstruktor.
   * <p>
   *
   * @param row  Reihe
   * @param seat Sitz in Reihe
   */
  public Sitz(int row, int seat) {
    this.row = row; // no setter, sorry
    this.seat = seat;
  }
  
  /**
   * Getter für Reihe.
   * <p>
   *
   * @return Reihe
   */
  public int getRow() {
    return row;
  }
  
  /**
   * Getter für Sitz in Reihe
   * <p>
   *
   * @return Sitz in Reihe
   */
  public int getSeat() {
    return seat;
  }
  
  /**
   * Stringrepräsentation
   * <p>
   *
   * @return Stringrepräsentation
   */
  @Override
  public String toString() {
    return "" + row + "/" + seat;
  }
  
  /**
   * Vergleich mit einem anderen Sitz.
   *
   * @param other anderer Sitz
   * @return &lt; 0, wenn dieser kleiner als andere; 0, wenn gleich; &gt; 0 wenn
   * größer
   */
  @Override
  public int compareTo(Object other) {
    Sitz otherSitz = (Sitz) other;
    return this.hashCode() - otherSitz.hashCode();
  }
  
  @Override
  public int hashCode() {
    return this.row * 50 + this.seat;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Sitz other = (Sitz) obj;
    if (this.row != other.row) {
      return false;
    }
    if (this.seat != other.seat) {
      return false;
    }
    return true;
  }
}
