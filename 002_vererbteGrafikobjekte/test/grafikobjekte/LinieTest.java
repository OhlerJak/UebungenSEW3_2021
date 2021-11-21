package grafikobjekte;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LinieTest {
  private Linie l;


  public LinieTest() {
  }



  @BeforeEach
  public void setUp() {
    try {
      l = new Linie(new Punkt(3, 4), new Punkt(8, 9));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testLinie() {
    try {
      new Linie(new Punkt(3, 4), new Punkt(8, 9));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testLinie2() {
    try {
      new Linie(null, new Punkt(8, 9));
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("Startpunkt muss angegeben werden!",ex.getMessage());
    }
  }


  @Test
  public void testLinie3() {
    try {
      new Linie(new Punkt(8, 9),null);
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("Endpunkt muss angegeben werden!",ex.getMessage());
    }
  }


  @Test
  public void testLinie4() {
    try {
      new Linie(new Punkt(8, 9), new Punkt(8, 9));
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("Startpunkt und Endpunkt müssen verschieden sein!",ex.getMessage());
    }
  }


  @Test
  public void testGetStart() {
    try {
      assertTrue(l.getStart().equals(new Punkt(3, 4)));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testGetEnde() {
    try {
      assertTrue(l.getEnde().equals(new Punkt(8, 9)));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testEquals() {
    assertTrue(l.equals(l));
  }


  @Test
  public void testEquals2() {
    try {
      Linie l2 = new Linie(new Punkt(3, 4), new Punkt(8, 9));
      assertTrue(l.equals(l2));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testEquals3() {
    try {
      Linie l2 = new Linie(new Punkt(3, 5), new Punkt(8, 9));
      assertFalse(l.equals(l2));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }


  @Test
  public void testEquals4() {
    try {
      Kreis k = new Kreis(new Punkt(3, 4), 4);
      assertFalse(l.equals(k));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }
}
