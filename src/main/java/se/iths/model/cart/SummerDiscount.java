package se.iths.model.cart;

public class SummerDiscount extends Discount{
    public SummerDiscount(long id) {
        super(id);
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * 0.9;
    }

}
