package se.iths.model.cart;

import java.util.*;

public class Cart {
    List<CartItem> items = new ArrayList<>();
    HashSet<Discount> discounts = new HashSet<>();

    public Cart () {}

    public List<CartItem> getItems() {
        return items;
    }

    public Optional<CartItem> add(CartItem cartItem) {
        for (CartItem item : items) {
            if (item.getProductId() == cartItem.getProductId()) {
                return Optional.empty();
            }
        }
        items.add(cartItem);
        return Optional.of(cartItem);
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
                sum = discount.applyDiscount(sum);
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
