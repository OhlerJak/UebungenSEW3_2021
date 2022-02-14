package sitzplan;

public class Schueler {
    String name;

    public Schueler(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
