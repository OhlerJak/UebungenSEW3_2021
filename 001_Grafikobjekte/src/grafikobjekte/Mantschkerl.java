package grafikobjekte;

import at.wima.grafx.GrafxException;

public class Mantschkerl {
private Kreis kopf;
private Linie rumpf, armR, armL, beinR, beinL;
private Punkt p,schulter,becken;

private double size=1;

public Mantschkerl(Punkt p, double size){
    setP(p);
    setSize(size);

}
public Mantschkerl (Punkt p){
    this(p,1);
}

    public Punkt getP() {
        return p;
    }

    public void setP(Punkt p) {
        this.p = p;
        generate();
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
    if(!(size>2.5||size<=0)){
        this.size = size;
        generate();
    }

    }
    //erstellen der Komponenten des Mantschkerl
    private void generate(){
    kopf=new Kreis(p,(int)(size*50));
    rumpf=new Linie(new Punkt(p.getX(),p.getY()+kopf.getR()),new Punkt(p.getX(),p.getY()+kopf.getR()+(int)(150*size)));
    schulter=new Punkt(rumpf.getStart().getX(),(rumpf.getStart().getY()+(int)(40*size)));
    becken= rumpf.getEnde();
    armR=new Linie(schulter,new Punkt(schulter.getX()+(int)(100*size),schulter.getY()-(int)(75*size)));
    armL=new Linie(schulter,new Punkt(schulter.getX()-(int)(100*size),schulter.getY()-(int)(75*size)));

    beinL=new Linie(becken,new Punkt(becken.getX()-(int)(75*size),becken.getY()+(int)(75*size)));
    beinR=new Linie(becken,new Punkt(becken.getX()+(int)(75*size),becken.getY()+(int)(75*size)));

    }
    public void draw() throws GrafxException {
    kopf.draw();
    rumpf.draw();
    armR.draw();
    armL.draw();
    beinL.draw();
    beinR.draw();
    }
}
