import entity.Discount;
import exception.DiscountException;
import org.junit.jupiter.api.Test;
import service.DiscountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountServiceTest {

    @Test
    void shouldDecreaseDiscount(){
        DiscountService discountService = new DiscountService();
        Discount discount = discountService.createDiscount(50);
        discountService.decreaseDiscountPercent(discount, 5);
        assertEquals(45, discount.getDiscountPercent());
    }

    @Test
    void shouldNotDecreaseDiscount(){
        DiscountService discountService = new DiscountService();
        Discount discount = discountService.createDiscount(0);
        discountService.decreaseDiscountPercent(discount, 5);
        assertEquals(0, discount.getDiscountPercent());
    }

    @Test
    void discountCanNotBeNegative(){
        DiscountService discountService = new DiscountService();
        Discount discount = discountService.createDiscount(3);
        discountService.decreaseDiscountPercent(discount, 5);
        assertEquals(0, discount.getDiscountPercent());
    }

    @Test
    void discountCanNotBeMoreThanHundred(){
        DiscountService discountService = new DiscountService();
        assertThrows(DiscountException.class, () -> discountService.createDiscount(101));
    }

    @Test
    void discountCanNotBeLessThanZero(){
        DiscountService discountService = new DiscountService();
        assertThrows(DiscountException.class, () -> discountService.createDiscount(-1));
    }
}
