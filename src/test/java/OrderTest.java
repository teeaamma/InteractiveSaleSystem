import entity.Discount;
import entity.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void getTotalPrice_regular(){
        Order order = new Order(
                LocalDateTime.parse("2021-02-09T16:00:22"),
                "testClient",
                100,
                new Discount()
        );

        int result = order.getTotalPrice();
        assertEquals(500, result);
    }

    @Test
    void getTotalPrice_fullDiscount(){
        Order order = new Order(
                LocalDateTime.parse("2021-02-09T16:00:22"),
                "testClient",
                100,
                new Discount(100)
        );

        int result = order.getTotalPrice();
        assertEquals(0, result);
    }

    @Test
    void getTotalPrice_withoutDiscount(){
        Order order = new Order(
                LocalDateTime.parse("2021-02-09T16:00:22"),
                "testClient",
                100,
                new Discount(0)
        );

        int result = order.getTotalPrice();
        assertEquals(1000, result);
    }
}
