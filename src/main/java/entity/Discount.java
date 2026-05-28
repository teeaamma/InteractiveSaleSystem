package entity;

import exception.DiscountException;

public class Discount {

    private static final int DEFAULT_DISCOUNT_PERCENT = 50;

    private int discountPercent;

    public Discount(){
        discountPercent = DEFAULT_DISCOUNT_PERCENT;
    }

    public Discount(int discountPercent){
        if (discountPercent < 0 || discountPercent > 100)
            throw new DiscountException("Скидка не может быть меньше 0% или больше 100%: " + discountPercent);
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
