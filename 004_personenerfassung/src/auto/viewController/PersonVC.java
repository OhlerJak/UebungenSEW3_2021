package auto.viewController;


import auto.model.Geschlecht;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import auto.model.Person;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Optional;


public class PersonVC {
  private final VBox root = new VBox();
  
  private final GridPane fields = new GridPane();
  private final Label lbSvnr = new Label();
  private final TextField tfSvnr = new TextField();
  private final Label lbNname = new Label();
  private final TextField tfNname = new TextField();
  private final Label lbVname = new Label();
  private final TextField tfVname = new TextField();
  private final Label lbGroesse = new Label();
  private final TextField tfGroesse = new TextField();
  private final Label lbGebdate = new Label();
  private final DatePicker dpGebdate = new DatePicker();
  private final Label lbSex = new Label();
  private final ChoiceBox<Geschlecht> cbSex = new ChoiceBox();

  private final HBox buttons = new HBox();
  private final Button btCancel = new Button();
  private final Button btSave = new Button();
  
  private final TextField tfMsg = new TextField();
  
  // Model
  private Person model;
  
  // Helper
  private static final NumberFormat df;
  
  
  static {
    df = NumberFormat.getNumberInstance();
    df.setMaximumFractionDigits(2);
    df.setMinimumFractionDigits(2);
  }
  
  
  private PersonVC() {
    this.model = new Person();
  }
  
  
  public static void show(Stage stage) {
    PersonVC personVC = new PersonVC();
    
    // View aufbauen, Handeler hinzufügen, ...
    personVC.init();
    
    // View anzeigen
    Scene scene = new Scene(personVC.root);
    stage.setTitle("Personenwartung");
    stage.setScene(scene);
    stage.show();
  }
  
  
  private void init() {
    // Root
    root.setSpacing(10);
    root.setPadding(new Insets(5, 5, 5, 5));
    
    // Fields
    root.getChildren().add(fields);
    fields.setHgap(5);
    fields.setVgap(5);
    
    int row = -1;
    
    // - SVNR
    row++;
    lbSvnr.setText("SVNR:");
    fields.add(lbSvnr, 0, row);
    
    fields.add(tfSvnr, 1, row);
    
    // - Nachname
    row++;
    lbNname.setText("Nachname:");
    fields.add(lbNname, 0, row);
    
    fields.add(tfNname, 1, row);
    
    // - Vorname
    row++;
    lbVname.setText("Vorname:");
    fields.add(lbVname, 0, row);
    
    fields.add(tfVname, 1, row);

    //- Geburtsdatum
    row++;
    lbGebdate.setText("Geburtsdatum:");
    fields.add(lbGebdate,0,row);

    fields.add(dpGebdate,1,row);

    // - Größe
    row++;
    lbGroesse.setText("Größe:");
    fields.add(lbGroesse, 0, row);
    
    fields.add(tfGroesse, 1, row);
    tfGroesse.setAlignment(Pos.CENTER_RIGHT);

    // - Geschlecht
    row++;
    lbSex.setText("Geschlecht:");
    fields.add(lbSex,0,row);

    //cbSex.setItems(FXCollections.observableArrayList(Geschlecht.values()));
    cbSex.getItems().setAll(Geschlecht.values());
    fields.add(cbSex,1,row);
    
    // Buttons
    root.getChildren().add(buttons);
    buttons.setAlignment(Pos.CENTER_RIGHT);
    buttons.setSpacing(5);
    
    // - Button Cancel
    btCancel.setText("Cancel");
    buttons.getChildren().add(btCancel);
    btCancel.setOnAction(e -> cancel());
    
    // - Button Calc
    btSave.setText("Save");
    buttons.getChildren().add(btSave);
    btSave.setDefaultButton(true);
    btSave.setOnAction(e -> save());
    
    // Message-Bereich
    root.getChildren().add(tfMsg);
    tfMsg.setEditable(false);
    tfMsg.setFocusTraversable(false);
  }
  
  
  private void save() {
    try {
      // View-Felder in Model speichern
      model.setSvnr(tfSvnr.getText());
      model.setNname(tfNname.getText());
      model.setVname(tfVname.getText());
      model.setGroesse(df.parse(tfGroesse.getText()).doubleValue());
      model.setGebdate(dpGebdate.getValue());
      model.setGschlcht(cbSex.getValue());
      
      // Model speichern
      model.save();
      
      // View zurücksetzen
      clear();
      
      // Nachricht
      tfMsg.setText("Ok, gesichert!");
      tfMsg.setStyle("-fx-text-inner-color: green;");
    }
    catch (Exception ex) {
      // Fehlermeldung
      tfMsg.setText(ex.getMessage());
      tfMsg.setStyle("-fx-text-inner-color: red;");
    }
  }
  
  
  private void cancel() {
    Alert alConfirm = new Alert(Alert.AlertType.CONFIRMATION);
    alConfirm.setHeaderText("Sicher?");
    alConfirm.setContentText("Wirklich Canceln?");
    Optional<ButtonType> result = alConfirm.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      // View zurücksetzen
      clear();
      
      // Nachricht
      tfMsg.setText("Ok, Wartung abgebrochen!");
      tfMsg.setStyle("-fx-text-inner-color: green;");
    }
    else {
      // Nachricht
      tfMsg.setText("Ok, kein Abbruch der Wartung vorgenommen!");
      tfMsg.setStyle("-fx-text-inner-color: green;");
    }
  }
  
  
  private void clear() {
    // Nächstes Model
    model = new Person();
    
    // View für nächste Person herrichten
    tfSvnr.setText(null);
    tfNname.setText(null);
    tfVname.setText(null);
    tfGroesse.setText(null);
    dpGebdate.setValue(null);
    cbSex.setValue(null);

  }
}
