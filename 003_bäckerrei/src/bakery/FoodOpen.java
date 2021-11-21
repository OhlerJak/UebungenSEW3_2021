package bakery;

public class FoodOpen extends Food{
    private double unit, pricePerUnit;
    public FoodOpen(String name, double unit, double pricePerUnit) throws BakeryException {

        super(name);
        setUnit(unit);
        setPricePerUnit(pricePerUnit);
    }

    public void setUnit(double unit) throws BakeryException {
        if(unit<=0){
            throw new BakeryException("Zu wenig (FoodOpen)");
        }
        this.unit = unit;
    }

    public void setPricePerUnit(double pricePerUnit) throws BakeryException {
        if(pricePerUnit<=0){
            throw new BakeryException("Zu geringer Preis (FoodOpen)");
        }
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public double calcPrice() {
        return unit*pricePerUnit;
    }
}
