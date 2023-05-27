package se.iths.model.order;

import java.util.List;

public class Order {
    long id;
    List<OrderLine> orderLines;

    double price;

    public Order (long id, double price, List<OrderLine> orderLines) {
        this.id = id;
        this.orderLines = orderLines;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    @Override
    public String toString() {
        String items = "";
        for (OrderLine orderLine : orderLines) {
            items = items + orderLine.quantity + " " + orderLine.getProduct() + " " + orderLine.getPrice() + " SEK\n";
        }
        return "Order ID: " + id + "\n" + items + " \nTotal: " + getPrice() + " SEK";
    }
}
