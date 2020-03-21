package pl.kapusta.sdanalysis.strategy;

public class Buyer {
    private Boolean buyed;
    private Double amount;

    public Buyer() {
        buyed = false;
        amount = 0.0;
    }

    public void buy(Double price) {
        if (!buyed) {
            amount += price;
            buyed = true;
        }
    }

    public void sell(Double price) {
        if (buyed) {
            amount -= price;
            buyed = false;
        }
    }

    public Double result(){
        return amount;
    }
}
