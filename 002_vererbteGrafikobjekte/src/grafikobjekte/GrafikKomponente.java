package grafikobjekte;

import at.wima.grafx.GrafxException;

public abstract class GrafikKomponente {
    private boolean initialized;

    public GrafikKomponente(){}

    public abstract void draw() throws  GrafxException;

    public boolean isInitialized(){return initialized;}

    public void setInitialized(boolean initialized){
        this.initialized=initialized;
    }

}
