package viewcontroller;

import javafx.application.Platform;
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
import model.Benutzer;
import model.RegisterException;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterC {
  //Bereiche
  public GridPane gpinput;
  public HBox hbsearch;
  public HBox hbbtn;
  public Button btSearch;
  public Button btSave;


  @FXML
  private TextField tfEmail;
  
  @FXML
  private TextField tfVorname;
  
  @FXML
  private TextField tfNachname;
  
  
  public static void show(Stage stage) {
    try {
      // View und Controller erstellen
      FXMLLoader loader = new FXMLLoader(RegisterC.class.getResource("registerV.fxml"));
      Parent root = (Parent) loader.load();
//
//      // Controller ermitteln
//      RegisterC registerC = (RegisterC) loader.getController();
//
//      // View zusätzlich konfigurieren, Handler hinzufügen; alles, das nicht in FXML möglich war
//      registerC.init();
    
      // View anzeigen
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("User Profile");
      stage.show();
    }
    catch (IOException ex) {
      ex.printStackTrace(System.err);
      Platform.exit();
    }
  }
  
  @FXML
  private void initialize() {
    setinputallowed(false);

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
  public void btSearchOnAction(ActionEvent actionEvent) {search();}

  
  private void save() {
    try {
      Benutzer benutzer = new Benutzer(
          tfEmail.getText(),
          tfNachname.getText(),
          tfVorname.getText()
      );
      benutzer.save();
  
      clear();
  
      Alert success = new Alert(Alert.AlertType.INFORMATION, "Erfolgreich gespeichert!");
      success.showAndWait();
      setinputallowed(false);
    }
    catch (RegisterException e) {
      Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage());
      error.showAndWait();
    }
  }
  
  private void cancel() {
    clear();
    setinputallowed(false);
  }

  private void search(){
    String email = this.tfEmail.getText();

    if(checkEmail(email)) {

      Benutzer benutzer = Benutzer.find(email);
      if (benutzer == null) {
        Alert info = new Alert(Alert.AlertType.INFORMATION, "Benutzer existiert nicht. Bitte erfassen!");
        info.showAndWait();
      } else {
        tfNachname.setText(benutzer.getNachname());
        tfVorname.setText(benutzer.getVorname());
      }

      setinputallowed(true);
    }
    else {
      Alert error = new Alert(Alert.AlertType.ERROR,"Email ungültig");
      error.showAndWait();
    }
  }
  
  private void clear() {
    tfEmail.setText(""      );
    tfNachname.setText("");
    tfVorname.setText("");
  }


  private boolean checkEmail(String email){

    //Regex aus https://howtodoinjava.com/java/regex/java-regex-validate-email-address/ kopiert
    String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
   // String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    Pattern pattern = Pattern.compile(regex);

    return pattern.matcher(email).matches();

  }



  private void setinputallowed(boolean allowed){

    gpinput.setVisible(allowed);
    hbbtn.setVisible(allowed);
    hbsearch.setDisable(allowed);

    btSave.setDefaultButton(allowed);
    btSearch.setDefaultButton(!allowed);



  }


}
