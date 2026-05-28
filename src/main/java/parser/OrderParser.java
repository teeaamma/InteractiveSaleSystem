package parser;

import entity.Discount;
import entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderParser {
    List<Order> parseOrders(String fileName);

    default Order parseOrder(String str, Discount discount, String delimiter){
        String[] args = str.split(delimiter);
        if (args.length > 3)
            throw new RuntimeException("Ошибка в формате заказа (неправильное количество аргументов): " + str);
        try {
            return new Order(
                    LocalDateTime.parse(args[0]),
                    args[1],
                    Integer.parseInt(args[2]),
                    discount
            );
        } catch (Exception e){
            throw new RuntimeException("Ошибка в формате заказа: " + str);
        }
    }
}
