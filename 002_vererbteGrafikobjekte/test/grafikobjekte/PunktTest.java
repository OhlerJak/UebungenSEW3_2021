package grafikobjekte;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PunktTest {
  private Punkt p;


  public PunktTest() {
  }

  @BeforeEach
  public void setUp() {
    try {
      p = new Punkt(3, 4);
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testPunkt() {
    try {
      new Punkt(3, 4);
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testPunkt2() {
    try {
      new Punkt(-1, 4);
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("X-Koordinate muss zumindest 0 sein!", ex.getMessage());
    }
  }


  @Test
  public void testPunkt3() {
    try {
      new Punkt(1, -1);
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("Y-Koordinate muss zumindest 0 sein!", ex.getMessage());
    }
  }


  @Test
  public void testSetX() {
    try {
      p.setX(5);
      assertEquals(5, p.getX());
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testSetX2() {
    try {
      p.setX(-1);
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("X-Koordinate muss zumindest 0 sein!", ex.getMessage());
    }
  }


  @Test
  public void testSetY() {
    try {
      p.setY(5);
      assertEquals(5, p.getY());
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testSetY2() {
    try {
      p.setY(-1);
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("Y-Koordinate muss zumindest 0 sein!", ex.getMessage());
    }
  }


  @Test
  public void testEquals() {
    assertTrue(p.equals(p));
  }


  @Test
  public void testEquals2() {
    try {
      Punkt p2 = new Punkt(3, 4);
      assertTrue(p.equals(p2));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testEquals3() {
    try {
      Punkt p2 = new Punkt(3, 5);
      assertFalse(p.equals(p2));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testEquals4() {
    try {
      Kreis k = new Kreis(p, 4);
      assertFalse(p.equals(k));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }

}
