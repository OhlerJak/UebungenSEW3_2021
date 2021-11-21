package person.model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;


public enum Geschlecht {
  M, W, D;
  
  
  public static ObservableList<Geschlecht> valuesAsObservableList() {
    return FXCollections.observableList(Arrays.asList(values()));
  }
}
