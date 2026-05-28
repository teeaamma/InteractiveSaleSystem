package Entity;

import java.time.LocalDateTime;

public class Order {

    private static final int PRICE_FOR_ONE_KILO = 10;

    private final LocalDateTime time;
    private final String client;
    private final int weight;
    private final Discount discount;

    public Order(LocalDateTime time, String client, int weight, Discount discount) {
        this.time = time;
        this.client = client;
        this.weight = weight;
        this.discount = discount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getClient() {
        return client;
    }

    public int getWeight() {
        return weight;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void decreaseDiscount(int percent) {
        discount.decreaseDiscountPercent(percent);
    }

    public String getClientAndPrice(){
        return client + " - " + getTotalPrice(discount.getDiscountPercent());
    }

    private int getTotalPrice(int discountPercent){
        return (int) (weight * PRICE_FOR_ONE_KILO * (1 - ((float) discountPercent / 100)));
    }
}
