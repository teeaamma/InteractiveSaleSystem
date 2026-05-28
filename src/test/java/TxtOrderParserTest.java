import entity.Discount;
import entity.Order;
import parser.OrderParser;
import parser.TxtOrderParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TxtOrderParserTest {

    @TempDir
    Path tempDir;

    @Test
    void parseOrder_regular(){
        String orderStr = "2023-04-03T18:23:17|Test|4500";
        Discount discount = new Discount();
        OrderParser orderParser = new TxtOrderParser();

        Order order = orderParser.parseOrder(orderStr, discount, "\\|");

        assertEquals(LocalDateTime.parse("2023-04-03T18:23:17"), order.getTime());
        assertEquals("Test", order.getClient());
        assertEquals(4500, order.getWeight());
        assertEquals(discount, order.getDiscount());
    }

    @Test
    void parseOrder_wrongDelimiter(){
        String orderStr = "2023-04-03T18:23:17*Test*4500";
        Discount discount = new Discount();
        OrderParser orderParser = new TxtOrderParser();

        assertThrows(RuntimeException.class, () -> orderParser.parseOrder(orderStr, discount, "\\|"));
    }

    @Test
    void parseOrder_wrongArgsCount(){
        String orderStr = "2023-04-03T18:23:17|Test|4500|50";
        Discount discount = new Discount();
        OrderParser orderParser = new TxtOrderParser();

        assertThrows(RuntimeException.class, () -> orderParser.parseOrder(orderStr, discount, "\\|"));
    }

    @Test
    void parseOrders_regular() throws IOException {
        Path tempFile = tempDir.resolve("test.txt");
        OrderParser orderParser = new TxtOrderParser();

        Files.write(tempFile, List.of(
                "2023-04-03T18:23:17|TestFirst|1000",
                "2023-04-03T18:22:17|TestSecond|2000"
        ));

        List<Order> orders = orderParser.parseOrders(tempFile.toString());

        assertEquals(2, orders.size());

        assertEquals(LocalDateTime.parse("2023-04-03T18:23:17"), orders.get(0).getTime());
        assertEquals("TestFirst", orders.get(0).getClient());
        assertEquals(1000, orders.get(0).getWeight());

        assertEquals(LocalDateTime.parse("2023-04-03T18:22:17"), orders.get(1).getTime());
        assertEquals("TestSecond", orders.get(1).getClient());
        assertEquals(2000, orders.get(1).getWeight());
    }

    @Test
    void parseOrders_FileDoesNotExist(){
        OrderParser orderParser = new TxtOrderParser();

        assertThrows(RuntimeException.class, () -> orderParser.parseOrders("TestFile.txt"));
    }
}
