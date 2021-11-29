package main;

import gui.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Rechner;
import model.RechnerException;

public class TheMain extends Application {


    public static void main(String[] args) throws RechnerException {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Controller.launch(primaryStage, new Rechner());
    }
}
