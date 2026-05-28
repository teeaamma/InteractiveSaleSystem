import Entity.Discount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountTest {

    @Test
    void shouldDecreaseDiscount(){
        Discount discount = new Discount();
        discount.decreaseDiscountPercent(5);
        assertEquals(45, discount.getDiscountPercent());
    }

    @Test
    void shouldNotDecreaseDiscount(){
        Discount discount = new Discount(0);
        discount.decreaseDiscountPercent(3);
        assertEquals(0, discount.getDiscountPercent());
    }

    @Test
    void discountCanNotBeNegative(){
        Discount discount = new Discount(3);
        discount.decreaseDiscountPercent(5);
        assertEquals(0, discount.getDiscountPercent());
    }

    @Test
    void discountCanNotBeMoreThanHundred(){
        assertThrows(RuntimeException.class, () -> new Discount(101));
    }
}
