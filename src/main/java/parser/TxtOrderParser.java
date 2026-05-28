package parser;

import entity.Discount;
import entity.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TxtOrderParser implements OrderParser {

    @Override
    public List<Order> parseOrders(String fileName) {
        List<Order> orders;
        Discount discount = new Discount();
        try(Stream<String> lines = Files.lines(Path.of(fileName))) {
            orders = new ArrayList<>(
                    lines.map(line -> parseOrder(line, discount, "\\|"))
                    .toList()
            );
        }
        catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + fileName, e);
        }
        return orders;
    }

}
