import Entity.Discount;
import Entity.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void getClientAndPrice_regular(){
        Order order = new Order(
                LocalDateTime.parse("2021-02-09T16:00:22"),
                "testClient",
                100,
                new Discount()
        );

        String result = order.getClientAndPrice();
        assertEquals("testClient - 500", result);
    }

    @Test
    void getClientAndPrice_fullDiscount(){
        Order order = new Order(
                LocalDateTime.parse("2021-02-09T16:00:22"),
                "testClient",
                100,
                new Discount(100)
        );

        String result = order.getClientAndPrice();
        assertEquals("testClient - 0", result);
    }

    @Test
    void getClientAndPrice_withoutDiscount(){
        Order order = new Order(
                LocalDateTime.parse("2021-02-09T16:00:22"),
                "testClient",
                100,
                new Discount(0)
        );

        String result = order.getClientAndPrice();
        assertEquals("testClient - 1000", result);
    }
}
