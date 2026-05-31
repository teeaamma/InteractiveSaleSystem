package parser;

import entity.Discount;
import entity.Order;

import java.util.List;

public interface OrderParser {
    List<Order> parseOrders(String fileName, Discount discount);

    Order parseOrder(String str, Discount discount);
}
