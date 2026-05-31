package service;

import entity.Discount;
import exception.DiscountException;

public class DiscountService {

    public Discount createDiscount(int discountPercent){
        return new Discount(validateDiscount(discountPercent));
    }

    private int validateDiscount(int discountPercent){
        if (discountPercent < 0 || discountPercent > 100)
            throw new DiscountException("Скидка не может быть меньше 0% или больше 100%: " + discountPercent);
        return discountPercent;
    }

    public void decreaseDiscountPercent(Discount discount, int percent){
        int discountPercent = discount.getDiscountPercent();

        if (discountPercent <= percent)
            discount.setDiscountPercent(0);
        else
            discount.setDiscountPercent(discountPercent - percent);
    }
}
