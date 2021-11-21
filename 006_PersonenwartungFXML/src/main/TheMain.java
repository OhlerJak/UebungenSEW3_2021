package main;

import javafx.application.Application;
import javafx.stage.Stage;
import person.viewController.PersonVC;
import serial.Catalog;

public class TheMain extends Application {
  Catalog catalog;
  
  @Override
  public void init() {
    catalog = new Catalog(); // oder mit Singleton: Catalog.getInstance().restore();
    catalog.restore();
  }
  
  @Override
  public void start(Stage stage) {
    PersonVC.show(stage, catalog); // // oder mit Singleton: catalog weglassen
  }
  
  @Override
  public void stop() throws Exception {
    super.stop();
    catalog.persist(); // oder mit Singleton: Catalog.getInstance().persist();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
