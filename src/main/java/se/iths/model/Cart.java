package se.iths.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    List<CartItem> items = new ArrayList<>();

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

    public double getTotalPrice() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) {
            sum  += items.get(i).getTotalPrice();
        }
        return sum;
    }
}
