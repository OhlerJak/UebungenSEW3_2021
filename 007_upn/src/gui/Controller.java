package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;

public class Controller {
    public TextField tfinput;
    public ListView lvstack;
    public GridPane gpmain;
    public Button bt1;
    public Button bt2;
    public Button bt3;
    public Button bt4;
    public Button bt5;
    public Button bt6;
    public Button bt7;
    public Button bt8;
    public Button bt9;
    public Button bt0;


    public Button btplus;
    public Button btminus;
    public Button btmal;
    public Button btdiv;
    public Button btenter;

    public TextField tfmes;


    private FXMLLoader loader;
    private Parent root;

    public static  void launch(Stage stage)  {

        Controller controller = new Controller();
        controller.loader = new FXMLLoader(Controller.class.getResource("View.fxml"));

        try{
            controller.root =(Parent) controller.loader.load();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Scene scene= new Scene(controller.root);
        stage.setTitle("Rechner");
        stage.setScene(scene);
        stage.show();



    }

    public Controller(){
    }

    @FXML
    public void initialize(){
        showmes("Fertig erstellt! ");
    }

    public void btfunconact(ActionEvent actionEvent) {
        String text =  ((Button) actionEvent.getSource()).getText();
        System.out.println(text);


    }


    public void btnronact(ActionEvent actionEvent) {
       String text =  ((Button) actionEvent.getSource()).getText();
        tfinput.setText(tfinput.getText()+text);

    }

    private void showmes(String mes){
        tfmes.setText(mes);
    }

}
