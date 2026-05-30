package parser;

import entity.Discount;
import entity.Order;
import exception.OrderParsingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DelimiterOrderParser implements OrderParser{

    private final String delimiter;

    public DelimiterOrderParser(String delimiter){
        this.delimiter = delimiter;
    }

    @Override
    public List<Order> parseOrders(String fileName) {
        Discount discount = new Discount(50);
        try(Stream<String> lines = Files.lines(Path.of(fileName))) {
            return new ArrayList<>(
                    lines.map(line -> parseOrder(line, discount))
                            .toList()
            );
        }
        catch (IOException e) {
            throw new OrderParsingException("Ошибка при чтении файла: " + fileName);
        }
    }

    @Override
    public Order parseOrder(String str, Discount discount) {
        String[] args = str.split(delimiter);
        if (args.length > 3)
            throw new OrderParsingException("Ошибка в формате заказа (неправильное количество аргументов): " + str);
        try {
            return new Order(
                    LocalDateTime.parse(args[0]),
                    args[1],
                    Integer.parseInt(args[2]),
                    discount
            );
        } catch (Exception e){
            throw new OrderParsingException("Ошибка в формате заказа: " + str);
        }
    }
}
