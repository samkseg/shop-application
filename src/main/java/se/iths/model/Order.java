package se.iths.model;

import java.util.List;

public class Order {
    long id;
    List<OrderLine> orderLines;

    public Order (long id, List<OrderLine> orderLines) {
        this.id = id;
        this.orderLines = orderLines;
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
            items = items + orderLine.quantity + " pcs " + orderLine.getProduct() + " " + orderLine.getPrice() + " SEK\n";
        }
        return "Order ID: " + id + "\n" + items + " \nTotal: " + getTotalPrice() + " SEK";
    }

    public double getTotalPrice() {
        double sum = 0.0;
        for (int i = 0; i < orderLines.size(); i++) {
            sum  += orderLines.get(i).getTotalPrice();
        }
        return sum;
    }
}
