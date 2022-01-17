package tryCollection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Bike;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TryC {
    public Button btnext;
    public Label lbText;
    public ListView lvList;

    private int counter;
    private List<Bike> bikes;
    private Bike[] bikearray;

    public static void show(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(TryC.class.getResource("TryV.fxml"));
            Parent parent =loader.load();


            Scene scene = new Scene(parent);
            stage.setTitle("TryCollection");
            stage.setScene(scene);
            stage.show();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public TryC(){
        bikearray= new Bike[]{
                new Bike("00001", "Scot", "navy blue"),
                new Bike("00002","Trek", "furry red"),
                new Bike("00003","Specialized", "night black")};
    }

    @FXML
    public void initialize(){
        counter =0;




    }

    public void nextOnAction(ActionEvent actionEvent) {
        nextStep();
    }


    private void nextStep(){
        counter++;
        Bike merida = new Bike("00004","Merida","cactus green");

        switch (counter){
            case 1:
                lbText.setText("");
                bikes = new LinkedList<>();
                bikes.addAll(Arrays.asList(bikearray));

                break;
            case 2:
                bikes.add(merida);
                bikes.add(0,new Bike("00005","KTM","flaming orange"));
                break;
            case 3:
                String text = merida+" at the Positon: ";
                text = text + (bikes.indexOf(merida)+1);
                lbText.setText(text);
                break;
            case 4:
                lbText.setText(bikes.get(0).toString());
            break;
            case 5:
                lbText.setText(bikes.get(0).toString());
                bikes.remove(0);
                break;
            case 6: {
                bikes.remove(((int) bikes.size() / 2));
                lbText.setText("");
                break;
            }
            case 7:{
                    bikes.add(((int)bikes.size()/2),new Bike("00007","Santa Cruz","fading gray"));
                    break;
                }
            case 8: {
                bikes.get(((int) bikes.size() / 2)).setFarbe("arctic blue");
                break;
            }
                case 9:{
                    int id = ((int)bikes.size()/2);
                    Bike bike = bikes.get(id);
                    bikes.remove(id);
                    bike.setRahmennr("00008");
                    bikes.add(id,bike);
                    break;
                }
            case 10:{
                lbText.setText("List-Size: "+bikes.size());
                break;
            }
            case 11: {
                lbText.setText("");
                System.out.println(bikes.toString());
                break;
            }
                case 12:{
                    System.out.println(bikes.toArray().toString());
                break;
            }
            default:
               lbText.setText("Fertig!");

                break;
        }

        lvList.setItems(FXCollections.observableList(bikes));

    }
}
