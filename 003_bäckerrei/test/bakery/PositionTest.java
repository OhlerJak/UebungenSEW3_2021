package bakery;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {
  Position p1, p2, p3;


  public PositionTest() {
  }


  @BeforeEach
  public void setUp() {
    try {
      p1 = new Position(new Drink("Kaffee", 0.2, 1.0), 2.0);
      p2 = new Position(new FoodPacked("Nuts", 0.6), 3);
      p3 = new Position(new FoodOpen("Leberkäse", 1, 1.450), 1);
    }
    catch (BakeryException ex) {
      Logger.getLogger(PositionTest.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


  @Test
  public void testSetAmount() {
    try {
      p1.setAmount(0);
      fail("Exception not thrown!");
    }
    catch (BakeryException ex) {
    }
    catch (Exception e) {
      fail("Invalid exception thrown!");
    }
  }


  @Test
  public void testGetPrice() {
    assertEquals(2, p1.calcPrice(), 0.001);
    assertEquals(1.8, p2.calcPrice(), 0.001);
    assertEquals(1.45, p3.calcPrice(), 0.001);
  }


  @Test
  public void testToString() {
  }
}
