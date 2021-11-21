package grafikobjekte;


import at.wima.grafx.GrafxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KreisTest {
  private Kreis k;
  
  
  public KreisTest() {
  }
  
  
  @BeforeEach
  public void setUp() {
    try {
      k = new Kreis(new Punkt(20, 20), 5);
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }
  
  
  @Test
  public void testKreis() {
    try {
      new Kreis(new Punkt(10, 10), 3);
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }
  
  
  @Test
  public void testKreis2a() {
    try {
      new Kreis(null, 3);
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("Mittelpunkt muss angegeben werden!", ex.getMessage());
    }
    catch (Exception ex) {
      fail("GrafikException expected but was " + ex.getClass());
    }
  }
  
  
  @Test
  public void testKreis2b() throws GrafikException {
    Assertions.assertThrows(GrafikException.class, () -> new Kreis(null, 3));
  }
  
  
  @Test
  public void testKreis3() {
    try {
      new Kreis(new Punkt(10, 10), 0);
      fail("Exception expected!");
    }
    catch (GrafikException ex) {
      assertEquals("Radius muss zumindest 1 sein!", ex.getMessage());
    }
  }
  
  
  @Test
  public void testEquals() {
    assertTrue(k.equals(k));
  }
  
  
  @Test
  public void testEquals2() {
    try {
      Kreis k2 = new Kreis(new Punkt(20, 20), 5);
      assertTrue(k.equals(k2));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }
  
  
  @Test
  public void testEquals3() {
    try {
      Kreis k2 = new Kreis(new Punkt(10, 20), 2);
      assertFalse(k.equals(k2));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }
  
  
  @Test
  public void testEquals4() {
    try {
      Punkt p = new Punkt(3, 4);
      assertFalse(k.equals(p));
    }
    catch (GrafikException ex) {
      fail("No exception expected!");
    }
  }
  
}
