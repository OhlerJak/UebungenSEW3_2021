package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Benutzer;
import model.RegisterException;
import serial.Catalog;
import viewcontroller.RegisterC;

public class TheMain extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Catalog.getInstance().restore();
    RegisterC.show(primaryStage);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    Catalog.getInstance().persist();
  }
}