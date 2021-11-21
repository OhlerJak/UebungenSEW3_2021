package bakery;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class CartTest {
  Cart c;


  public CartTest() {
  }


  @BeforeEach
  public void setUp() {
    c = new Cart();
  }


  @Test
  public void testGetValue() {
    try {
      c.addContent(new Position(new Drink("Kaffee", 0.2, 1.6), 2));
      assertEquals(3.2, c.getValue(), 0.001);

      c.addContent(new Position(new FoodPacked("Nuts", 0.6), 3));
      assertEquals(5, c.getValue(), 0.001);

      c.addContent(new Position(new FoodOpen("Leberkäse", 1, 7.9), 1));
      assertEquals(12.9, c.getValue(), 0.001);
    }
    catch (BakeryException ex) {
      fail("Unexpected Exception thrown!");
    }
  }


  @Test
  public void testPrintReceipt() {
  }
}
