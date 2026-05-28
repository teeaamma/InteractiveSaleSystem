package parser;

import entity.Discount;
import entity.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NoExtensionOrderParser implements OrderParser {
    @Override
    public List<Order> parseOrders(String fileName) {
        Discount discount = new Discount(50);
        try(Stream<String> lines = Files.lines(Path.of(fileName))) {
            return new ArrayList<>(
                    lines.map(line -> parseOrder(line, discount, "#"))
                            .toList()
            );
        }
        catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + fileName, e);
        }
    }
}
