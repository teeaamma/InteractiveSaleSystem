import entity.Order;
import parser.OrderParser;
import parser.OrderParserFactory;

import java.util.Comparator;
import java.util.List;

public class OrderService {

    public List<String> calculateFinalPrices(String inputFileName){
        OrderParser orderParser = OrderParserFactory.getParser(inputFileName);

        List<Order> orders = orderParser.parseOrders(inputFileName);

        return orders.stream()
                .sorted(Comparator.comparing(Order::getTime))
                .map(order -> {
                    String orderClientAndPrice = order.getClientAndPrice();
                    order.decreaseDiscount(5);
                    return orderClientAndPrice;
                })
                .toList();
    }
}
