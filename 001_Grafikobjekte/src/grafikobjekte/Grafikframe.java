package grafikobjekte;

import at.wima.grafx.Grafx;
import at.wima.grafx.GrafxException;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Grafikframe {
    private List <Kreis> listK = new LinkedList<>();
    private List <Linie> listL = new LinkedList<>();
    private List <Mantschkerl> listM = new LinkedList<>();

    public Grafikframe(int width,int  height) throws GrafxException {

        Grafx.createDrawing(width,height, Color.BLACK);


    }
    public Grafikframe() throws GrafxException {
        this(800,500);
    }
    public void paint() throws GrafxException {
        for (Kreis k: listK) {
            k.draw();
        }
        for(Linie l:listL){
            l.draw();
        }
        for(Mantschkerl m:listM){
            m.draw();
        }
        Grafx.refresh();
    }
    public void addKreis(Kreis k){
        if(!listK.contains(k)){
            listK.add(k);
        }
    }
    public void addLinie(Linie l) {
        if (!listL.contains(l)) {
            listL.add(l);
        }
    }
    public void addMantschkerl(Mantschkerl m){
        if(!listM.contains(m)){
            listM.add(m);
        }
    }

    }
