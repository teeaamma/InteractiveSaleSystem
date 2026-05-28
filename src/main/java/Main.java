import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFileName1 = "discount_day.txt";
        String inputFileName2 ="discount_day_without_ext";
        String outputFileName = "output.txt";

        OrderService orderService = new OrderService();
        List<String> result = orderService.calculateFinalPrices(inputFileName1);

        try {
            Files.write(Path.of(outputFileName), result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
