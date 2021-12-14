package todo.viewController;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import person.model.Person;
import serial.Catalog;
import todo.model.ToDo;

import java.io.IOException;
import java.security.acl.Owner;
import java.util.LinkedList;
import java.util.List;

public class ToDoC {


    public HBox hbadd;
    public TextField tfadd;
    public Button btadd;
    public ListView lvtodo;
    public Button btclose;
    public TextField tfmes;


    private List<ToDo> todos;
    private Person verantwortlicher;


    public ToDoC() {

    }

    private static Stage main;

    public static void show(Window parent, Person verantworlicher, Catalog catalog) {
        ToDo.setCatalog(catalog);
        if (main== null) {
            main = new Stage();
            main.initModality(Modality.WINDOW_MODAL);
            main.initOwner(parent);
        }
        try {
            FXMLLoader loader = new FXMLLoader(ToDoC.class.getResource("ToDoView.fxml"));
            Parent root = (Parent) loader.load();


            ToDoC controller = loader.getController();
            controller.verantwortlicher = verantworlicher;
            controller.init();

            Scene scene = new Scene(root);
            main.setScene(scene);
            main.setTitle("ToDo");

            main.show();


        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void init() {
       updateList();


        lvtodo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                ((ToDo) newValue).remove();
                updateList();
                System.out.println("Ignore this:");
        });

    }


    public void btclose(ActionEvent actionEvent) {
        main.close();
    }

    public void btadd(ActionEvent actionEvent) {


        if (tfadd.getText().length() >= 3) {
            ToDo toDo = new ToDo(tfadd.getText(), verantwortlicher);
            toDo.save();

            updateList();
            setSuccmes("Erfolgreich gespeichert!");
        } else {

            setErrormes("ToDo zu kurz! ");
        }



    }
private void setErrormes(String text){
    tfmes.setText(text);
    tfmes.setStyle("-fx-text-inner-color: red");
}

private void setSuccmes(String text){
        tfmes.setText(text);
        tfmes.setStyle("-fx-text-inner-color: green");
}



private void updateList(){
    todos = ToDo.findByPerson(verantwortlicher);

    lvtodo.setItems(FXCollections.observableList(todos));

}


}






