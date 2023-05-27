package se.iths.model;

public class CartItem {
    long productId;
    Product product;
    long quantity;
    String price;

    public CartItem(Product product, long quantity) {
        this.productId = product.getId();
        this.product = product;
        this.quantity = quantity;
        this.price = String.valueOf(product.getPrice());
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return quantity * Double.parseDouble(price);
    }

    @Override
    public String toString() {
        return quantity + " pcs " + product + " " + getPrice() + " SEK";
    }

}
