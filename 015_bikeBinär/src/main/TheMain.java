package main;

import controllerview.BikeC15;
import javafx.application.Application;
import javafx.stage.Stage;
import serial.Catalog;

public class TheMain extends Application {
  @Override
  public void init() {
    Catalog.getInstance().restore();
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    BikeC15.show(primaryStage);
  }
  
  @Override
  public void stop() {
    Catalog.getInstance().persist();
  }
}
