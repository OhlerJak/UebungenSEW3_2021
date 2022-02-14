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
import javafx.stage.Stage;
import model.Bike;
import model.BikeExecption;

import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BikeCv2 {
  public Button btToText;
  public Button btFromText;
  @FXML
  private TextField tfRahmennr;
  
  @FXML
  private TextField tfMarkeType;
  
  @FXML
  private TextField tfFarbe;
  
  @FXML
  private Button btCancel;
  
  @FXML
  private Button btSelect;
  
  @FXML
  private Button btSave;
  
  private Bike model;
  
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
      FXMLLoader loader = new FXMLLoader(BikeCv2.class.getResource("bikeV.fxml"));
      Parent root = loader.load();
      
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
    tfRahmennr.setDisable(!disable);
    
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

  public void btToTextOnAction(ActionEvent actionEvent) {

    modeltoText();
  }

  public void btFromTextOnAction(ActionEvent actionEvent) {
    modelFromText();
  }



  private void modeltoText()  {
try {
  model.setMarkeType(tfMarkeType.getText());
  model.setFarbe(tfFarbe.getText());
  //Name: Bike.txt
  try (FileWriter out = new FileWriter("Bike.txt")) {

    out.write(model.toString());

  } catch (IOException ex) {
    ex.printStackTrace();
  }
}catch (BikeExecption ex){
  ex.printStackTrace();
}


  }

  private void modelFromText()  {

      File file = new File("C:\\Users\\ohler\\Schule\\3AHIT\\SEW\\UebungenSEW3_2021\\Bike.txt");
      try(Scanner sc = new Scanner(file)){
        sc.useDelimiter("\\Z");
        String text= sc.next();

        model= new Bike(text,false);
        tfRahmennr.setText(model.getRahmennr());
        tfMarkeType.setText(model.getMarkeType());
        tfFarbe.setText(model.getFarbe());

        disableNonkey(false);

      }catch (FileNotFoundException | BikeExecption ex){
        ex.printStackTrace();
      }
  }
}
