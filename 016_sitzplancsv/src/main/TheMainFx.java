package main;

import javafx.application.Application;
import javafx.stage.Stage;
import viewController.SitzplanC;

public class TheMainFx extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    SitzplanC.show(primaryStage);
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
