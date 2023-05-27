package se.iths.model;

import java.util.*;

public class Cart {
    List<CartItem> items = new ArrayList<>();
    HashSet<Discount> discounts = new HashSet<>();

    public Cart () {}
    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void add(CartItem cartItem) {
        items.add(cartItem);
    }

    public Optional<CartItem> findById(long id) {
        for (CartItem item : items) {
            if (item.getProductId() == id) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public void remove(CartItem cartItem) {
        items.remove(cartItem);
    }

    public void clear() {
        items.clear();
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(HashSet<Discount> discounts) {
        this.discounts = discounts;
    }

    public void addDiscounts(Discount discount) {
        this.discounts.add(discount);
    }

    public double getTotalPrice() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) {
            sum  += items.get(i).getTotalPrice();
        }
        if (!discounts.isEmpty()) {
            for (Discount discount : discounts) {
                if (discount instanceof Over15kDiscount && sum > 15000) {
                    sum = discount.applyDiscount(sum);
                }
                if (discount instanceof SummerDiscount) {
                    sum = discount.applyDiscount(sum);
                }
            }
        }
        return sum;
    }

    public double getDiscount() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) {
            sum  += items.get(i).getTotalPrice();
        }
        return sum - getTotalPrice();
    }
}
