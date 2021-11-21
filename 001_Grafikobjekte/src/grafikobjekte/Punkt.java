package grafikobjekte;

import java.util.Objects;

public class Punkt {
    private int x, y;

    public Punkt(int x,int y){
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Punkt)) return false;
        Punkt punkt = (Punkt) o;
        return x == punkt.x && y == punkt.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
