package viewController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sitzplan.Klasse;
import sitzplan.Schueler;
import sitzplan.Sitz;

import java.io.IOException;

public class SitzplanC {
  @FXML
  private TextField tfKlasse;
  @FXML
  private GridPane gpRoom;
  
  @FXML
  private void onBtSaveAction(ActionEvent actionEvent) {
    save();
  }
  
  
  public static void show(Stage stage) {
    try {
      // View und Controller erstellen
      FXMLLoader loader = new FXMLLoader(SitzplanC.class.getResource("sitzplanV.fxml"));
      Parent root = (Parent) loader.load();
//
//      // Controller ermitteln
//      RegisterC registerC = (RegisterC) loader.getController();
      
      // View anzeigen
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Sitzplan");
      stage.show();
    }
    catch (IOException ex) {
      ex.printStackTrace(System.err);
      Platform.exit();
    }
  }
  
  @FXML
  public void initialize() {
  }
  
  
  private void save() {
    Klasse klasse = new Klasse(tfKlasse.getText());
    
    int row = 0;
    int col = -1;
    for (Node nd : gpRoom.getChildren()) {
      col++;
      if (col > 2) {
        row++;
        col = 0;
      }
      Sitz sitz = new Sitz(row, col);
      
      if ((((TextField) nd).getText()).length() !=0) {
        klasse.setzen(sitz, new Schueler(((TextField) nd).getText()));
      }
      else {
        klasse.setzen(sitz, null);
      }
    }
    
    klasse.printKlassenliste();
  }
}
