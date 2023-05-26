package se.iths.data;

import se.iths.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    HashMap<Long, Order> repository = new HashMap<>();
    public OrderRepository() {
    }

    public List<Order> findAll() {
        return repository.values().stream().toList();
    }
    public Order addOrder(long id, Order order) {
        repository.put(order.getId(),order);
        return order;
    }

    public Optional<Order> findById(long id) {
        Order order  = repository.get(id);
        if (order == null) {
            return Optional.empty();
        } else {
            return Optional.of(order);
        }
    }

    public HashMap<Long, Order> getRepository() {
        return repository;
    }

    public void setRepository(HashMap<Long, Order> repository) {
        this.repository = repository;
    }
}
