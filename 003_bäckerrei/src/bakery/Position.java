package bakery;


import java.text.NumberFormat;

public class Position {
    private double amount;
    private Buyable item;


    public Position(Buyable item, double amount) throws BakeryException {
        setItem(item);
        setAmount(amount);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) throws BakeryException {
        if(amount<=0)
            throw new BakeryException("Zu wenige Items (Pos)");
        this.amount = amount;
    }

    public Buyable getItem() {
        return item;
    }

    public void setItem(Buyable item) {
        this.item = item;
    }

    public double calcPrice(){

        return  item.calcPrice()*amount;
    }
}
