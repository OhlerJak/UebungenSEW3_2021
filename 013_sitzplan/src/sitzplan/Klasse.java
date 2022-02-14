package sitzplan;

import java.util.Map;
import java.util.TreeMap;

public class Klasse {
    private String name;
    private TreeMap<Sitz,Schueler> treeMap;

    public Klasse(String name){
        setName(name);
        treeMap = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setzen(Sitz sitz, Schueler schueler){
        if(schueler==null){
            schueler=new Schueler("<frei>");
        }
        treeMap.put(sitz,schueler);
    }

    public void printSitzplan(){
        System.out.println(this.name+"---------------------------");

        Map.Entry<Sitz, Schueler> entry = treeMap.pollFirstEntry();
        int aktrow =0;
        while (entry!=null){
            if(entry.getKey().getRow()>aktrow){
                System.out.println();
                aktrow=entry.getKey().getRow();
            }
            System.out.printf("%15s ",entry.getValue());





            entry=treeMap.pollFirstEntry();
        }


        System.out.println();
        System.out.println("-------------------------------");

    }
}




















