package main;

import at.wima.grafx.GrafxException;
import grafikobjekte.*;

public class TheMain {
    public static void main(String[] args) throws GrafxException {

        //Für Testzwecke:
        Grafikframe grafikframe = new Grafikframe(700,700);
        grafikframe.addMantschkerl(new Mantschkerl(new Punkt(300,200),1.2));
        grafikframe.addMantschkerl(new Mantschkerl(new Punkt(400,400),0.1));
       grafikframe.addKreis(new Kreis(new Punkt(500,300),150));
       grafikframe.addLinie(new Linie(new Punkt(20,20), new Punkt(400,400)));

        grafikframe.paint();
    }





}
