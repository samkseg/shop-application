package se.iths.model.cart;

public class Over15kDiscount extends Discount {
    public Over15kDiscount(long id) {
        super(id);
    }
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.8;
    }
}
