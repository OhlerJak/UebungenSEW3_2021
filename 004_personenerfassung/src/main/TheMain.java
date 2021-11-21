package main;

import auto.model.Person;

import javafx.application.Application;
import javafx.stage.Stage;
import auto.viewController.PersonVC;

public class TheMain extends Application {
  @Override
  public void start(Stage stage) {
    PersonVC.show(stage);
  }


  @Override
  public void stop() throws Exception {
    super.stop();
    Person.deserialisieren();

  }

  @Override
  public void init() throws Exception {
    super.init();
    Person.serialisieren();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
