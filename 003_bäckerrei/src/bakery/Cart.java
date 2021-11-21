package bakery;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<Position> positions;
    private NumberFormat nf= NumberFormat.getInstance();

    public Cart(){
        nf.setMaximumFractionDigits(2);
        positions = new LinkedList<>();
    }

    public void addContent(Position pos){
        if(!positions.contains(pos)){
            positions.add(pos);
        }
    }

    public double getValue(){
        double val=0;
        for (Position item : positions) {
          val+=  item.calcPrice();
        }
        return val;
    }

    public void printReceipt(){
        System.out.println("Rechnung");
        for (Position item:positions) {
            System.out.println(item.getItem().calcName()+" : "+nf.format(item.calcPrice())+"€");
        }
        System.out.println("-------------------");
    }
}
