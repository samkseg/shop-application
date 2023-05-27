package se.iths.model;

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

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
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
