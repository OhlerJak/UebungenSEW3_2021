package bakery;

public class FoodPacked extends Food{
    private double price;
    public FoodPacked(String name, double price) throws BakeryException {
        super(name);
        setPrice(price);
    }

    public void setPrice(double price) throws BakeryException {
        if(price<=0)
            throw new BakeryException("Preis zu gering (Packed)");
        this.price = price;
    }

    @Override
    public double calcPrice() {
        return price;
    }
}
