package bakery;

public class Food implements Buyable{
    private String name;

    public Food(String name) throws BakeryException {
        setName(name);
    }


    @Override
    public String calcName() {
        return name;
    }

    @Override
    public double calcPrice() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws BakeryException {
        if(name.length()<3)
            throw new BakeryException("Name zu kurz (Food)");
        this.name = name;
    }
}
