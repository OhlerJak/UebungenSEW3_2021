package controllerview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Bike;
import model.BikeExecption;
import sun.nio.cs.ISO_8859_2;

import java.io.*;
import java.nio.charset.Charset;

public class BikeC15 {
  @FXML
  private TextField tfRahmennr;
  
  @FXML
  private TextField tfMarkeType;
  
  @FXML
  private TextField tfFarbe;
  
  @FXML
  private Button btFromText;
  
  @FXML
  private Button btToText;
  
  @FXML
  private Button btSelect;
  
  @FXML
  private Button btSave;
  
  private Stage stage;
  private Bike model;
  
  @FXML
  private void btFromTextOnAction(ActionEvent actionEvent) {
    textIn();
  }
  
  @FXML
  private void btToTextOnAction(ActionEvent actionEvent) {
    textOut();
  }
  
  @FXML
  void btCancelOnAction(ActionEvent event) {
    cancel();
  }
  
  @FXML
  void btSaveOnAction(ActionEvent event) {
    save();
  }
  
  @FXML
  void btSelectOnAction(ActionEvent event) {
    select();
  }
  
  public static void show(Stage stage) {
    try {
      FXMLLoader loader = new FXMLLoader(BikeC15.class.getResource("bikeV.fxml"));
      Parent root = loader.load();
      ((BikeC15) loader.getController()).stage = stage;
      
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Bikes");
      stage.show();
    }
    catch (IOException e) {
      e.printStackTrace();
      Platform.exit();
    }
  }
  
  @FXML
  public void initialize() {
    disableNonkey(true);
  }
  
  private void cancel() {
    model = null;
    
    tfRahmennr.setText("");
    tfMarkeType.setText("");
    tfFarbe.setText("");
    
    disableNonkey(true);
  }
  
  private void select() {
    try {
      model = Bike.select(tfRahmennr.getText());
      
      if (model == null) {
        model = new Bike(tfRahmennr.getText());
      }
      
      tfRahmennr.setText(model.getRahmennr());
      tfMarkeType.setText(model.getMarkeType());
      tfFarbe.setText(model.getFarbe());
      
      disableNonkey(false);
      
    }
    catch (BikeExecption e) {
      error(e.getMessage());
    }
  }
  
  private void save() {
    try {
      model.setMarkeType(tfMarkeType.getText());
      model.setFarbe(tfFarbe.getText());
      
      model.save();
      
      cancel();
      
      info("Ok, Bike gesichert!");
    }
    catch (BikeExecption e) {
      error(e.getMessage());
    }
  }
  
  private void disableNonkey(boolean disable) {
    // Disable Fields
    tfMarkeType.setDisable(disable);
    tfFarbe.setDisable(disable);
    
    // Dis/Enable Buttons
    btSelect.setDisable(!disable);
    btSave.setDisable(disable);
    
    btFromText.setDisable(!disable);
    btToText.setDisable(disable);
  }
  
  private void info(String msg) {
    Alert error = new Alert(Alert.AlertType.INFORMATION, msg);
    error.showAndWait();
  }
  
  private void error(String msg) {
    Alert error = new Alert(Alert.AlertType.ERROR, msg);
    error.showAndWait();
  }
  
  private void textIn()  {
    if (model == null) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Read from Text File");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Text Files", "*.txt"));
      fileChooser.setInitialDirectory(new File("C:\\Users\\ohler\\Schule\\3AHIT\\SEW\\Ausgabe"));
      File textIn = fileChooser.showOpenDialog(stage);
      
      if (textIn != null) {
        try(InputStreamReader in = new InputStreamReader(new FileInputStream(textIn),new ISO_8859_2() )){
          int x;
          String line="";
          while ((x=in.read())!=-1){
            line += (char)x;
          }


          String[] fields = line.split("/");


          tfRahmennr.setText(fields[0]);
          tfMarkeType.setText(fields[1]);
          if(fields.length==3) {
            tfFarbe.setText(fields[2]);
          }
          model = new Bike(tfRahmennr.getText());
          
          disableNonkey(false);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        catch (BikeExecption e) {
          this.error(e.getMessage());
        }
      }
    }
  }
  
  private void textOut() {

    try {
      model.setMarkeType(tfMarkeType.getText());
      model.setFarbe(tfFarbe.getText());

      if (model != null) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Write to Text File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialDirectory(new File("C:\\Users\\ohler\\Schule\\3AHIT\\SEW\\Ausgabe"));
        fileChooser.setInitialFileName("bike" + model.getRahmennr());
        File textOut = fileChooser.showSaveDialog(stage);



        if (textOut != null) {

          Charset charset = new ISO_8859_2();

          try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(textOut),charset)){

            String outputString = model.getRahmennr()+"/"+ model.getMarkeType()+"/"+ model.getFarbe();
            out.write(outputString);

          }catch (IOException e){
            error(e.getMessage());

          }

        }
      }

    }
    catch (BikeExecption e ) {
      error(e.getMessage());
    }

  }
}