import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import service.DiscountService;
import service.OrderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void calculateFinalPrices_txtRegular() throws IOException {
        Path tempFile = tempDir.resolve("test.txt");
        OrderService orderService = new OrderService(new DiscountService());

        Files.write(tempFile, List.of(
                "2023-04-03T18:23:17|TestSecond|1000",
                "2023-04-03T18:22:17|TestFirst|2000",
                "2023-04-03T19:22:17|TestThird|1500"
        ));

        List<String> result = orderService.calculateFinalPrices(tempFile.toString(), 50);

        assertEquals(3, result.size());
        assertEquals("TestFirst - 10000", result.get(0));
        assertEquals("TestSecond - 5500", result.get(1));
        assertEquals("TestThird - 9000", result.get(2));
    }

    @Test
    void calculateFinalPrices_noExtensionRegular() throws IOException {
        Path tempFile = tempDir.resolve("test");
        OrderService orderService = new OrderService(new DiscountService());

        Files.write(tempFile, List.of(
                "2023-04-03T18:23:17#TestSecond#1000",
                "2023-04-03T18:22:17#TestFirst#2000",
                "2023-04-03T19:22:17#TestThird#1500"
        ));

        List<String> result = orderService.calculateFinalPrices(tempFile.toString(), 50);

        assertEquals(3, result.size());
        assertEquals("TestFirst - 10000", result.get(0));
        assertEquals("TestSecond - 5500", result.get(1));
        assertEquals("TestThird - 9000", result.get(2));
    }

    @Test
    void calculateFinalPrices_emptyFile() {
        Path tempFile = tempDir.resolve("test.txt");
        OrderService orderService = new OrderService(new DiscountService());

        assertThrows(
                RuntimeException.class,
                () -> orderService.calculateFinalPrices(tempFile.toString(), 50)
        );
    }

    @Test
    void calculateFinalPrices_null() {
        Path tempFile = tempDir.resolve("test.txt");
        OrderService orderService = new OrderService(new DiscountService());

        assertThrows(
                RuntimeException.class,
                () -> orderService.calculateFinalPrices(tempFile.toString(), 50)
        );
    }
}
