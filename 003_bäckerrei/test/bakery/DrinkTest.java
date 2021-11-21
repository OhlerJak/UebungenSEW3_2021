package bakery;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class DrinkTest {
  Drink d;
  
  public DrinkTest() {
  }


  @BeforeEach
  public void setUp() {
    try {
      d = new Drink("Kaffee", 0.2, 1.3);
    }
    catch (BakeryException ex) {
      Logger.getLogger(DrinkTest.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


  @Test
  public void testSetName() {
    try {
      d.setName("xy");
      fail("Exception not thrown!");
    }
    catch (BakeryException ex) {
//      assertEquals(ex.getMessage(),"Name muss aus zumindest 3 Zeichen bestehen!");
    }
    catch (Exception e) {
      fail("Invalid exception thrown!");
    }
  }


  @Test
  public void testSetCupSize1() {
    try {
      d.setCupSize(0);
      fail("Exception not thrown!");
    }
    catch (BakeryException ex) {
    }
    catch (Exception e) {
      fail("Invalid exception thrown!");
    }
  }


  @Test
  public void testSetCupSize2() {
    try {
      d.setCupSize(1.1);
      fail("Exception not thrown!");
    }
    catch (BakeryException ex) {
    }
    catch (Exception e) {
      fail("Invalid exception thrown!");
    }
  }


  @Test
  public void testSetPrice() {
    try {
      d.setPrice(-0.1);
      fail("Exception not thrown!");
    }
    catch (BakeryException ex) {
    }
    catch (Exception e) {
      fail("Invalid exception thrown!");
    }
  }

  @Test
  public void testGetName() {
    assertEquals("Kaffee, 0.2 l", d.calcName());
  }


  @Test
  public void testGetPrice() {
    assertEquals(1.3, d.calcPrice(),0.001);
  }
}
