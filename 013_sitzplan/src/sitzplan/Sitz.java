package sitzplan;

public class Sitz implements Comparable<Sitz> {
    private int row, collum;

    public Sitz(int row, int collum){
        setCollum(collum);
        setRow(row);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCollum() {
        return collum;
    }

    public void setCollum(int collum) {
        this.collum = collum;
    }



    @Override
    public int compareTo(Sitz o) {

        int sum = this.getRow() - o.getRow();

        if(sum==0){
            sum= this.getCollum() -o.getCollum();
        }

         return sum;
    }
}
