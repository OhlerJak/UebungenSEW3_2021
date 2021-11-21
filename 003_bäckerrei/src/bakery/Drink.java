package bakery;

public class Drink implements Buyable{
  private String name;
  private double cupSize, price;

    public Drink (String name, double cupSize, double price) throws BakeryException {
        setPrice(price);
        setName(name);
        setCupSize(cupSize);

    }
    @Override
    public String calcName() {
        return name+", "+cupSize+" l";
    }

    @Override
    public double calcPrice() {
        return price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws BakeryException {
        if(name.length()<3)
            throw new BakeryException("Name zu Kurz (Drink)");
        this.name = name;
    }

    public double getCupSize() {
        return cupSize;
    }

    public void setCupSize(double cupSize) throws BakeryException {
        if(cupSize<=0||cupSize>1)
            throw new BakeryException("Cup zu klein!(Drink)");
        this.cupSize = cupSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws BakeryException {
        if(price<=0)
            throw new BakeryException("Preis zu niedrig (Drink)");
        this.price = price;
    }
}
