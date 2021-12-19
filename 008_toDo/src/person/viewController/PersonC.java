package person.viewController;


import person.model.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.*;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import serial.Catalog;
import todo.model.ToDo;
import todo.viewController.ToDoC;


/**
 * Controller Personenwartung.
 * <br>
 * Objekte dieser Klasse werden vom FXML-Loader erstellt. Dabei füllt dieser
 * auch die mit @FXML markiertenen Referenzen auf die Controls der View und
 * verknüpft die mit @FXML gekennzeichneten Methoden als Actions, etc.
 * <br>
 * Es handelt sich hierbei um ein sog. Wartungsfenster, das die Erstellung und
 * Veränderung von Datensätzen der Tabelle Person erlaubt. Besonders zu erwähnen
 * sind hier der Umgang mit der Sichtbarkeit von gerade nicht verwendeten
 * Feldern und Buttons.
 */
public class PersonC {
  public Button btToDo;
  // Panes & Controls
  @FXML
  private TextField tfSvnr;
  @FXML
  private AnchorPane apNonKey;
  @FXML
  private TextField tfNname;
  @FXML
  private TextField tfVname;
  @FXML
  private DatePicker dpGebDat;
  @FXML
  private TextField tfGroesse;
  @FXML
  private ChoiceBox<Geschlecht> cbGeschlecht;
  @FXML
  private Button btSave;
  @FXML
  private Button btCancel;
  @FXML
  private TextField tfMsg;

  // aktuelles Model
  private Person model;

  // Helper
  private static final String VIEWNAME = "PersonV.fxml";
  private static final NumberFormat NUMBERFORMAT_2DECIMALS;



  static {
    NUMBERFORMAT_2DECIMALS = NumberFormat.getNumberInstance();
    NUMBERFORMAT_2DECIMALS.setMaximumFractionDigits(2);
    NUMBERFORMAT_2DECIMALS.setMinimumFractionDigits(2);
  }

  private Stage ownStage;
  private Catalog catalog;

  /**
   * Aktion, die von der View beim Drücken des Buttons Save ausgeführt wird.
   *
   * @param event
   */
  @FXML
  private void btSaveOnAction(ActionEvent event) {
    save();
  }


  /**
   * Aktion, die von der View beim Drücken des Buttons Cancel ausgeführt wird.
   *
   * @param event
   */
  @FXML
  private void btCancelOnAction(ActionEvent event) {
    cancel();
  }


  /**
   * NoArg-Konstruktor.
   * <br>
   * Muss public existieren, weil der Loader den Controller instantiiert. Darf
   * nicht "selbst" aufgerufen werden.
   */
  public PersonC() {
    this.model = new Person();
  }


  /**
   * Anzeige der View.
   * <br>
   * Diese Methode erstellt eine Instanz der View und dieses Controllers
   * (FXML-Loader) und richtet alles (also vor allem den Controller) so weit
   * ein, dass es angezeigt werden kann.
   *
   * @param stage     Stage, in der die View angezeigt werden soll; null, wenn
   *                  neue erstellt werden soll.
   * @param catalog   Datenbank
   */
  public static void show(Stage stage, Catalog catalog) {
    try {
      Person.setCatalog(catalog);
  
      // View und Controller erstellen
      FXMLLoader loader = new FXMLLoader(PersonC.class.getResource(VIEWNAME));
      Parent root = (Parent) loader.load();

      // Controller ermitteln
      PersonC personC = (PersonC) loader.getController();

      // View zusätzlich konfigurieren, Handler hinzufügen; alles, das nicht in FXML möglich war
      personC.init();
      personC.ownStage = stage;
      personC.catalog = catalog;

      // View anzeigen
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Personenwartung");
      stage.show();

    }
    catch (IOException ex) {
      Logger.getLogger(PersonC.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("Something wrong with " + VIEWNAME + "!");
      ex.printStackTrace(System.err);
      System.exit(1);
    }
  }


  /**
   * Initialisieren.
   * <br>
   * Diese Methode wird nur von show() verwendet, um den Controller zu
   * initialisieren. Das umfasst u.a. die Konfiguration der Controls, die
   * Verbindung der Controls mit den Model-Feldern, etc.
   * <br>
   * Ursprünglich war dies Teil der Methode show() und alle Referenzen auf
   * "this" mussten mit "personC" erfolgen, weil show ja "static" sein muss. Das
   * geht so einfacher.
   */
  private void init() {
    // Controls initialisieren
    // Choicebox
    cbGeschlecht.setItems(Geschlecht.valuesAsObservableList());
    cbGeschlecht.setValue(cbGeschlecht.getItems().get(0));

    // Focus Lost für Schlüsselfeld einrichten
    // An das Property "focused" wird ein neu erstellter Change-Listener angehängt,
    // der reagiert, wenn das Feld verlassen wird.
    tfSvnr.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
      if (oldValue && tfSvnr.getText() != null && tfSvnr.getText().trim().length() != 0) {
        // Model selektieren
        select();
      }
    });

    // Meldung für den Benutzer
    showSuccessMessage("Ok, bitte SVNR eingeben!");

    // Ausgangszustand einstellen
    // Fenster im "Keydisplay-Modus" und kein Model ausgewählt.
    reset_display();
  }


  /**
   * Anzeige zurücksetzen.
   * <br>
   * Diese Methode setzt die Wartungsanzeige auf den Ausgangszustand zurück. Das
   * heißt, das ein leeres Model in Bearbeitung ist und dass nur das
   * Schlüsselfeld angezeigt (Keydisplay-Modus) wird.
   */
  private void reset_display() {
    // Leeres Model verbinden
    setModel(new Person());

    // "Keydisplay-Modus" einschalten
    fullDisplay(false);
  }


  /**
   * Löst die Verbindung mit dem alten und erstellt eine Verbindung mit dem
   * neuen Model.
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
    apNonKey.setVisible(full);
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
   * <br>
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
   * <br>
   * Diese Methode sorgt dafür, dass die Veränderungen, die der Benutzer gemacht
   * hat, gespeichert werden. In Abhängigkeit davon, ob es sich um die Wartung
   * eines bestehenden Models oder um die Erstellung eines neuen Models handelt,
   * werden unterschiedliche Methoden dazu aufgerufen; letztlich muss das ja
   * entweder zu einem "insert" oder einem "update" führen.
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
   * <br>
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

  public void btToDoOnAction(ActionEvent actionEvent) {

    if(model.getSvnr()!=null) {
      ToDoC.show(ownStage, model, catalog);
      showSuccessMessage("Todos geladen!");
    }
    else {
      showErrorMessage("Keine Person geladen!");
    }

  }
}
