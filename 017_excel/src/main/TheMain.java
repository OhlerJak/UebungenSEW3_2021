package main;

import csvoutput.CSVOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TheMain {
    public static void main(String[] args) throws FileNotFoundException {
        CSVOutputStream output = new CSVOutputStream(new FileOutputStream("Test.csv"));
        try {
        output.writeCell(6);
        output.writeCell(1.4);
        output.writeCell('c');
        output.writeRow();

            output.writeCell("hallo");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
