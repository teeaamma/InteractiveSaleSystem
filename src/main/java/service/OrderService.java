package service;

import entity.Discount;
import entity.Order;
import parser.OrderParser;
import parser.OrderParserFactory;

import java.util.Comparator;
import java.util.List;

public class OrderService {

    private final DiscountService discountService;

    public OrderService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public List<String> calculateFinalPrices(String inputFileName, int baseDiscount){
        OrderParser orderParser = OrderParserFactory.getParser(inputFileName);
        Discount discount = discountService.createDiscount(baseDiscount);

        List<Order> orders = orderParser.parseOrders(inputFileName, discount);

        return orders.stream()
                .sorted(Comparator.comparing(Order::getTime))
                .map(order -> {
                    int price = calculateTotalPrice(order, order.getDiscount());
                    discountService.decreaseDiscountPercent(order.getDiscount(), 5);
                    return order.getClient() + " - " + price;
                })
                .toList();
    }

    private int calculateTotalPrice(Order order, Discount discount){
        int basePrice = order.getWeight() * Order.PRICE_FOR_ONE_KILO;
        float discountRate = (float) discount.getDiscountPercent() / 100;
        return (int) (basePrice * (1 - discountRate));
    }
}
