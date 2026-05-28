package Parser;

import Entity.Discount;
import Entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderParser {
    List<Order> parseOrders(String fileName);

    default Order parseOrder(String str, Discount discount, String delimiter){
        String[] args = str.split(delimiter);
        if (args.length > 3)
            throw new RuntimeException("Ошибка в формате заказа (неправильное количество аргументов): " + str);
        LocalDateTime time;
        String client;
        int weight;
        try {
            time = LocalDateTime.parse(args[0]);
            client = args[1];
            weight = Integer.parseInt(args[2]);
        } catch (Exception e){
            throw new RuntimeException("Ошибка в формате заказа: " + str);
        }
        return new Order(time, client, weight, discount);
    }
}
