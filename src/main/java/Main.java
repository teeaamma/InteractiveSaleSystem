import service.DiscountService;
import service.OrderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFileName1 = "discount_day.txt";
        String inputFileName2 ="discount_day_without_ext";
        String outputFileName = "output.txt";

        DiscountService discountService = new DiscountService();
        OrderService orderService = new OrderService(discountService);
        List<String> result = orderService.calculateFinalPrices(inputFileName1, 50);

        try {
            Files.write(Path.of(outputFileName), result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
