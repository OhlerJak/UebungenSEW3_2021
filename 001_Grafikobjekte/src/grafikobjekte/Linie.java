package grafikobjekte;

import at.wima.grafx.Grafx;
import at.wima.grafx.GrafxException;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Linie {
    private Punkt start, ende;

    public Linie(Punkt s, Punkt e){
        setEnde(e);
        setStart(s);
    }

    public Punkt getStart() {
        return start;
    }

    public void setStart(Punkt start) {
        this.start = start;
    }

    public Punkt getEnde() {
        return ende;
    }

    public void setEnde(Punkt ende) {
        this.ende = ende;
    }
    public void draw() throws GrafxException {

        Grafx.addLine(start.getX(),start.getY(),ende.getX(),ende.getY(), Color.WHITE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Linie)) return false;
        Linie linie = (Linie) o;
        return start.equals(linie.start) && ende.equals(linie.ende);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, ende);
    }
}
