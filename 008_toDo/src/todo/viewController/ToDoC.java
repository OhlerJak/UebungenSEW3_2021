package todo.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import serial.Catalog;

import java.io.IOException;

public class ToDoC {


    public HBox hbadd;
    public TextField tfadd;
    public Button btadd;
    public ListView lvtodo;
    public Button btclose;
    public TextField tfmes;
    private Parent root;



    public ToDoC(){
    }

    public static void show(Stage stage)  {

        try {
            FXMLLoader loader = new FXMLLoader(ToDoC.class.getResource("ToDoView.fxml"));
            Parent root =  (Parent) loader.load();


            ToDoC controller = loader.getController();
            controller.init();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("ToDo");
            stage.show();


        }catch (IOException ex){
            ex.printStackTrace();
            System.exit(1);
        }
    }
    private void init(){

    }


    public void btclose(ActionEvent actionEvent) {

    }

    public void btadd(ActionEvent actionEvent) {
    }
}
