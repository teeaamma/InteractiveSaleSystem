package Entity;

public class Discount {

    private static final int DEFAULT_DISCOUNT_PERCENT = 50;

    private int discountPercent;

    public Discount(){
        discountPercent = DEFAULT_DISCOUNT_PERCENT;
    }

    public Discount(int discountPercent){
        if (discountPercent > 100)
            throw new RuntimeException("Скидка не может быть больше 100%: " + discountPercent);
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void decreaseDiscountPercent(int percent){
        if (discountPercent <= percent)
            discountPercent = 0;
        else
            discountPercent -= percent;
    }
}
