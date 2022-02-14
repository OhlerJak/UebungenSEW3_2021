package main;

import controllerview.BikeCv2;
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
    BikeCv2.show(primaryStage);
  }
  
  @Override
  public void stop() {
    Catalog.getInstance().persist();
  }
}
