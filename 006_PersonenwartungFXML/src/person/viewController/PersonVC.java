package person.viewController;


import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.model.Geschlecht;
import person.model.Person;
import serial.Catalog;

import java.io.IOException;
import java.text.NumberFormat;



public class PersonVC {


    //FXML-Nodes für den Zugriff
  @FXML
  protected VBox vBox;

  @FXML
  private HBox gpKey;
  @FXML
  private  Label lbSvnr;
  @FXML
  private  TextField tfSvnr;

  @FXML
  private  GridPane gpNonKey;
  @FXML
  private  Label lbNname;
  @FXML
  private  TextField tfNname;
  @FXML
  private  Label lbVname;
  @FXML
  private  TextField tfVname;
  @FXML
  private  Label lbGebDat;
  @FXML
  private  DatePicker dpGebDat;
  @FXML
  private  Label lbGroesse;
  @FXML
  private  TextField tfGroesse;
  @FXML
  private  Label lbGeschlecht;
  @FXML
  private  ChoiceBox cbGeschlecht;

  @FXML
  private  HBox hbButtons;
  @FXML
  private  Button btCancel;
  @FXML
  private  Button btSave;

  @FXML
  private  TextField tfMsg;



  // Model
  private Person model;
  
  // Helper
  private static final NumberFormat NUMBERFORMAT_2DECIMALS;

  //Für das Herreinladen der FXML-Datei
  FXMLLoader loader;
  Parent root;
  
  
  static {
    NUMBERFORMAT_2DECIMALS = NumberFormat.getNumberInstance();
    NUMBERFORMAT_2DECIMALS.setMaximumFractionDigits(2);
    NUMBERFORMAT_2DECIMALS.setMinimumFractionDigits(2);
  }
  
  
 public PersonVC() {
    this.model = new Person();
  }

  
  public static void show(Stage stage, Catalog catalog) {
      //Model laden
    Person.setCatalog(catalog);
    PersonVC personVC = new PersonVC();


    //Laden der FXML Datei
    personVC.loader = new FXMLLoader(PersonVC.class.getResource("View.fxml"));
    try {
      personVC.root = (Parent) personVC.loader.load();
    }catch (IOException ex){
      ex.printStackTrace();
    }


    // View anzeigen
    Scene scene = new Scene(personVC.root);
    stage.setTitle("Personenwartung");
    stage.setScene(scene);
    stage.show();

  }

  //Wird nachdem alle Nodes geladen wurden aufgerufen
    @FXML
    public void initialize() {
      //Dinge welche man in der .fxml Datei nicht einstellen kann:



    // Focus Lost für Schlüsselfeld einrichten
    // An das Property "focused" wird ein neu erstellter Change-Listener angehängt,
    // der reagiert, wenn das Feld verlassen wird.
    tfSvnr.focusedProperty().addListener(
        (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
          //System.out.println(oldValue + "/" + tfSvnr.getText() + "/" + newValue);
          if (oldValue && tfSvnr.getText() != null && tfSvnr.getText().trim().length() != 0) {
            // Model selektieren
            select();
          }
        });


    cbGeschlecht.setItems(Geschlecht.valuesAsObservableList());


    // Meldung für den Benutzer
    showSuccessMessage("Ok, bitte SVNR eingeben!");
    
    // Ausgangszustand einstellen
    // Fenster im "Key Display Modus" und kein Model ausgewählt.
    reset_display();
  }
  
  
  /**
   * Anzeige zurücksetzen.
   * Diese Methode setzt die Wartungsanzeige auf den Ausgangszustand zurück. Das
   * heißt, das ein leeres Model in Bearbeitung ist und dass nur das
   * Schlüsselfeld angezeigt (Key Display Modus) wird.
   */
  private void reset_display() {
    // Leeres Model verbinden
    setModel(new Person());
    
    // "Keydisplay-Modus" einschalten
    fullDisplay(false);
  }
  
  
  /**
   * Anzeige setzen.
   * Erstellt eine Verbindung mit einem neuen Model-Objekt und lädt die Modelwerte in die Controls.
   *
   * @param person Model
   */
  private void setModel(Person person) {
    // Model setzen
    model = person;
    
    // Controls mit Current füllen
    if (model != null) {
      tfSvnr.setText(model.getSvnr());
      tfNname.setText(model.getNname());
      tfVname.setText(model.getVname());
      dpGebDat.setValue(model.getGebDat());
      tfGroesse.setText(model.getGroesse() != null ?
          NUMBERFORMAT_2DECIMALS.format(model.getGroesse()) :
          "");
      cbGeschlecht.setValue(model.getGeschlecht());
    }
  }
  
  
  /**
   * Wechselt den Display-Modus.
   * Ist full true, so wird in den Full Disaply Modus gewechselt. D.h., dass das Schlüsselfeld gesperrt, die
   * Nichtschlüsselfelder und die Buttons angezeigt werden.
   * Ist full false, so wird in den Key Display Modus gewechselt. D.h., dass nur das Schlüsselfeld sicht- und
   * änderbar ist.
   *
   * @param full
   */
  private void fullDisplay(boolean full) {
    tfSvnr.setEditable(!full);
    gpNonKey.setVisible(full);
    btSave.setVisible(full);
    btCancel.setVisible(full);
    
    if (full) {
      tfNname.requestFocus();
    }
    else {
      tfSvnr.requestFocus();
    }
  }
  
  
  /**
   * Model selektieren.
   * Diese Methode sorgt dafür, dass dem Fenster ein Model zugeordnet wird.
   * Entweder in dem es von der Datenbank eines selektieren (lässt), oder in dem
   * es ein leeres Model erstellt. Danach kann das Fenster in den
   * "Fulldisplay-Modus" umgeschaltet werden.
   */
  private void select() {
    try {
      // Model selektieren versuchen
      Person person = Person.selectBySvnr(tfSvnr.getText());
      
      if (person != null) {
        // Model wurde gefunden, Benutzer möchte kein neues Objekt erstellen,
        // sondern ein bestehendes warten
        
        // selektiertes Model verbinden
        setModel(person);
        
        showSuccessMessage("Ok, Daten selektiert!");
      }
      else {
        // Model wurde nicht gefunden, Benutzer möchte ein neues erstellen.
        // Er setzt einfach mit der Bearbeitung des bereits verbundenen, leeren Models
        // fort.
        showSuccessMessage("Ok, bitte Daten erfassen!");
      }
      
      // In den Fulldisplay-Modus umaschalten
      fullDisplay(true);
    }
    catch (Exception ex) {
      // Fehlermeldungen des Models an den Benutzer weiterleiten
      showErrorMessage(ex.toString());
    }
  }
  
  
  /**
   * Model speichern.
   * Diese Methode sorgt dafür, dass die Veränderungen, die der Benutzer gemacht
   * hat, gespeichert werden.
   */
  private void save() {
    try {
      // Current aus controls füllen
      model.setSvnr(tfSvnr.getText());
      model.setNname(tfNname.getText());
      model.setVname(tfVname.getText());
      model.setGroesse(NUMBERFORMAT_2DECIMALS.parse(tfGroesse.getText()).doubleValue());
      model.setGebDat(dpGebDat.getValue());
      model.setGeschlecht((Geschlecht) cbGeschlecht.getValue());
      
      // Wartung oder Erstellung?
      model.save();
      
      showSuccessMessage("Ok, Daten gesichert!");
      
      // Alles zurück auf Ausgangsstatus
      reset_display();
    }
    catch (Exception ex) {
      // Fehlermeldungen an den Benutzer weiterleiten
      showErrorMessage(ex.getMessage());
    }
  }
  
  
  /**
   * Alles verwerfen.
   * Diese Methode verwirft eine begonnene Neuerstellung oder Änderung eines
   * Models.
   */
  private void cancel() {
    // Alles zurück auf Start
    reset_display();
    showSuccessMessage("Ok, Vorgang abgebrochen!");
  }
  
  
  /**
   * Fehlermeldung anzeigen.
   *
   * @param message Nachrichtentext
   */
  private void showErrorMessage(String message) {
    tfMsg.setText(message);
    tfMsg.setStyle("-fx-text-inner-color: red;");
  }
  
  
  /**
   * Erfolgsmeldung anzeigen.
   *
   * @param message Nachrichtentext
   */
  private void showSuccessMessage(String message) {
    tfMsg.setText(message);
    tfMsg.setStyle("-fx-text-inner-color: green;");
  }



  //Für FXML Button
  public void btCancelOnAction() {
    cancel();
  }
  public void btSaveOnAction(){
    save();
  }

}
