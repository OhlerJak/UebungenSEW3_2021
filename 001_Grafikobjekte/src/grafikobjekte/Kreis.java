package grafikobjekte;

import at.wima.grafx.Grafx;
import at.wima.grafx.GrafxException;
import javafx.scene.paint.Color;


import java.util.Objects;

public class Kreis {
    private int r;
    private Punkt mittelpunkt;
    public Kreis (Punkt p, int r){
        setMittelpunkt(p);
        setR(r);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Punkt getMittelpunkt() {
        return mittelpunkt;
    }

    public void setMittelpunkt(Punkt mittelpunkt) {
        this.mittelpunkt = mittelpunkt;
    }

    public void draw() throws GrafxException {
        Grafx.addEllipse(toString(),mittelpunkt.getX()-r,mittelpunkt.getY()-r,r*2,r*2, Color.WHITE,false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kreis)) return false;
        Kreis kreis = (Kreis) o;
        return r == kreis.r && mittelpunkt.equals(kreis.mittelpunkt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, mittelpunkt);
    }
}
