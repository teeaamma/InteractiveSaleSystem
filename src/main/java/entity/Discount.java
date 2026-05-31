package entity;

public class Discount {

    private int discountPercent;

    public Discount(int discountPercent){
        this.discountPercent = discountPercent;
    }

    public void setDiscountPercent(int discountPercent){
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}
