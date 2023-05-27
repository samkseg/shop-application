package se.iths.model.cart;

import java.util.Objects;

public abstract class Discount {
    private final long id;

    public Discount(long id) {
        this.id = id;
    }
    abstract double applyDiscount(double amount);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id == discount.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
