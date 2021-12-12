package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Rechner;
import model.RechnerException;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

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

    private Rechner model;


    private static final NumberFormat NF;
    static {
        NF = NumberFormat.getNumberInstance();
        NF.setMaximumFractionDigits(5);
        NF.setMinimumFractionDigits(0);
    }


    public static  void launch(Stage stage, Rechner rechner) throws IOException {
        FXMLLoader loader =new FXMLLoader(Controller.class.getResource("View.fxml"));

        Parent root =(Parent) loader.load();

        Controller controller = loader.getController();

        controller.model = rechner;
        Scene scene= new Scene(root);
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

    public void btfunconact(ActionEvent actionEvent)  {
        String func =  ((Button) actionEvent.getSource()).getText();
        double nr =0;
        try {

           nr = Double.parseDouble(tfinput.getText());

            tfinput.setText("");


        }catch (Exception ex){
            showmes("ungültige Eingabe");
        }
        try {
            switch (func) {
                case "+":
                    model.addNumber(nr);
                    model.addition();
                    break;
                case "-":
                    model.addNumber(nr);
                    model.subtraction();
                    break;
                case "*":
                    model.addNumber(nr);
                    model.multiplication();
                    break;
                case "/":
                    model.addNumber(nr);
                    model.division();

                    break;
                case "enter":
                    model.addNumber(nr);
                    break;
                case "C":
                    break;
                case "CE":
                    model.resetStack();
                default:
                    showmes("Unbekannter Button");
                    break;
            }
            lvstack.setItems(FXCollections.observableList(model.getStack()));
        }catch (RechnerException ex){
            showmes(ex.getMessage());
        }



    }


    public void btnronact(ActionEvent actionEvent) {
       String text =  ((Button) actionEvent.getSource()).getText();
        tfinput.setText(tfinput.getText()+text);

    }

    private void showmes(String mes){
        tfmes.setText(mes);
    }

}
