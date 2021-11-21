package bakery;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class FoodPackedTest {
  FoodPacked f;


  public FoodPackedTest() {
  }


  @BeforeEach
  public void setUp() {
    try {
      f = new FoodPacked("Manner", 0.6);
    }
    catch (BakeryException ex) {
      Logger.getLogger(FoodPackedTest.class.getName()).log(Level.SEVERE, null,
        ex);
    }
  }


  @Test
  public void testSetName() {
    try {
      f.setName("x");
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
      f.setPrice(-5);
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
    assertEquals(0.6, f.calcPrice(), 0.001);
  }
}
