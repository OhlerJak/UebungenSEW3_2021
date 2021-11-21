package main;


import bakery.*;


/**
 * Oldstyle Test Driver
 */
public class TheMain {
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Cart cart = new Cart();

    //Für Testzwecke:
    try {
      cart.addContent(new Position(new Drink("Kaffee", 0.2, 1.6), 2));
      cart.addContent(new Position(new FoodPacked("Nuts", 0.6), 300));
      cart.addContent(new Position(new FoodOpen("Leberkäse", 1, 14.50), 0.200));

      cart.printReceipt();
    }
    catch (BakeryException ex) {
      System.out.println("Unexpected Exception!");
    }
  }
}
