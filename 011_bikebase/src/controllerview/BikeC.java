package controllerview;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Bike;

public class BikeC {

    public TextField tfFarbe;
    public TextField tfMarke;
    public TextField tfrahmen;
    public HBox hbRahmen;
    public Button btcancel;
    public Button btselect;
    public Button btsave;
    public GridPane gpinput;
    public Button btToText;

    private Bike model;
    public static void show(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(BikeC.class.getResource("BikeV.fxml"));
            Parent parent = loader.load();


            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("BikeBase");
            stage.show();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @FXML
    private void initialize(){

        inputallowed(false);
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        cancel();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        save();
    }

    public void selectOnAction(ActionEvent actionEvent) {
        select();
    }


    private void cancel(){

        clear();
        inputallowed(false);
    }

    private void save(){
        String nr = tfrahmen.getText();
        String marke = tfMarke.getText();
        String farbe = tfFarbe.getText();

        try{
            model = new Bike(nr,marke);
            model.setFarbe(farbe);
            model.save();
            cancel();
        }catch (Exception ex){
            Alert error = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            error.showAndWait();
        }

    }

    private void select(){
        String nr = tfrahmen.getText();

        if(nr.length()<5){
            Alert error = new Alert(Alert.AlertType.ERROR, "Ungültige Rahmennummer");
            error.showAndWait();
        }
        else {
            try {
                model = Bike.select(nr);
                tfMarke.setText(model.getMarkeType());
                tfFarbe.setText(model.getFarbe());

            }catch (Exception ex){
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Benutzer existiert nicht. Bitte erfassen!");
                info.showAndWait();
            }

            inputallowed(true);
        }
    }


    private void clear(){
        tfFarbe.setText("");
        tfrahmen.setText("");
        tfMarke.setText("");
    }

    private void inputallowed(boolean allowed){
        tfrahmen.setDisable(allowed);
        gpinput.setDisable(!allowed);
        btselect.setDisable(allowed);
        btsave.setDisable(!allowed);
        btcancel.setDisable(!allowed);
        btselect.setDefaultButton(!allowed);
        btsave.setDefaultButton(allowed);

    }


}
