package se.iths.data;

import se.iths.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    HashMap<Long, Product> repository = new HashMap<>();
    public ProductRepository() {
    }
    public Product addProduct(Product product) {
       repository.put(product.getId(), product);
       return product;
    }
    public List<Product> findAll() {
        return repository.values().stream().toList();
    }
    public List<Product> findByName(String name) {
        return repository.values().stream().filter(p -> p.getName().equals(name)).toList();
    }
    public List<Product> findByCategory(String category) {
        return repository.values().stream().filter(p -> p.getCategory().equals(category)).toList();
    }
    public Optional<Product> findById(long id) {
        Product product = repository.get(id);
        if (product == null) {
            return Optional.empty();
        } else {
            return Optional.of(product);
        }
    }
    public List<Product> findByPrice(long min, long max) {
        return repository.values().stream().filter(p -> p.getPrice() <= max && p.getPrice() >= min).toList();
    }

    public void removeProduct(long id) {
        repository.remove(id);
    }

    public HashMap<Long, Product> getRepository() {
        return repository;
    }

    public void setRepository(HashMap<Long, Product> repository) {
        this.repository = repository;
    }
}
