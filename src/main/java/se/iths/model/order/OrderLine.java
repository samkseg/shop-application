package se.iths.model.order;

import se.iths.model.product.Product;

public class OrderLine {
    long productId;
    String product;
    long quantity;
    String price;

    public OrderLine(Product product, long quantity) {
        this.productId = product.getId();
        this.product = product.getName();
        this.quantity = quantity;
        this.price = String.valueOf(product.getPrice());
    }

    public OrderLine(long productId, String product, long quantity, String price) {
        this.productId = productId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
    public long getProductId() {
        return productId;
    }

    public String getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return quantity * Double.parseDouble(price);
    }

    @Override
    public String toString() {
        return quantity + " " + product + " " + getTotalPrice() + " SEK";
    }
}
