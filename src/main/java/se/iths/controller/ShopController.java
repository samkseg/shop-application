package se.iths.controller;

import se.iths.data.ProductRepository;
import se.iths.model.Product;
import se.iths.service.FileService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ShopController {
    static long id = 0;
    ProductRepository repository = new ProductRepository();

    FileService fileService = new FileService();
    Scanner scanner = new Scanner(System.in);
    public ShopController() {
    }
    public void start() {
        fileService.fileReader();
        repository.setRepository(fileService.getProducts());
        id = fileService.getCount();
        menu();
    }

    private void shutDown() {
        fileService.setProducts(repository.getRepository());
        fileService.fileWriter();
        System.out.println(fileService.getProducts().values().stream().toList());
        System.exit(0);
    }

    public void menu() {
        while (true) {
            System.out.println("""
                Menu
                
                1. View products
                2. Add product
                3. Update product
                4. Remove product
                5. Exit
                """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> viewMenu();
                case "2" -> addMenu();
                case "3" -> updateMenu();
                case "4" -> removeMenu();
                case "5", "e", "E" -> shutDown();
            }
        }
    }

    private void removeMenu() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            String name = optionalProduct.get().getName();
            repository.removeProduct(id);
            System.out.println(name + " has been removed.");
        } else {
            System.out.println("Could not find product.");
        }
    }

    private void updateMenu() {
        System.out.println("""
                Update Product
                
                1. Update name
                2. Update price
                3. Update category
                4. Update brand
                5. Update quantity
                6. Back
                """);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> updateName();
            case "2" -> updatePrice();
            case "3" -> updateCategory();
            case "4" -> updateBrand();
            case "5" -> updateQuantity();
            case "6", "e", "E" -> {}
        }
    }

    private void updateQuantity() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        System.out.println("Update quantity: ");
        long quantity = scanner.nextLong();
        scanner.nextLine();
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setQuantity(quantity);
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New quantity: " + quantity);
        } else {
            System.out.println("Could not find product.");
        }
    }

    private void updateBrand() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Update brand: ");
        String category = scanner.nextLine();
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setCategory(category);
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New category: " + category);
        } else {
            System.out.println("Could not find product.");
        }
    }

    private void updateCategory() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Update category: ");
        String category = scanner.nextLine();
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setCategory(category);
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New category: " + category);
        } else {
            System.out.println("Could not find product.");
        }
    }

    private void updatePrice() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        System.out.println("Update price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setPrice(price);
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New price: " + price);
        } else {
            System.out.println("Could not find product.");
        }
    }

    private void updateName() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Update name: ");
        String name = scanner.nextLine();
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setName(name);
            System.out.println(name + " has been updated.");
        } else {
            System.out.println("Could not find product.");
        }
    }

    private void addMenu() {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Category: ");
        String category = scanner.nextLine();
        System.out.println("Brand: ");
        String brand = scanner.nextLine();
        System.out.println("Quantity: ");
        long quantity = scanner.nextLong();
        scanner.nextLine();
        Product product = new Product(id+1, name, price, category, brand, quantity);
        repository.addProduct(product);
        id = id +1;
        System.out.println(product.getName() + " has been added.");
    }

    private void viewMenu() {
        System.out.println("""
                View Menu
                
                1. All products
                2. Search by name
                3. Search by category
                4. Find by price
                5. Find by ID
                6. Back
                """);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> viewAll();
            case "2" -> searchByName();
            case "3" -> searchByCategory();
            case "4" -> findByPrice();
            case "5" -> findById();
            case "6", "e", "E" -> {}
        }
    }

    private void findById() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            System.out.println(optionalProduct.get());
        } else {
            System.out.println("Could not find product.");
        }
    }

    private void findByPrice() {
        System.out.println("Minimum price: ");
        long min = scanner.nextLong();
        System.out.println("Max price: ");
        long max = scanner.nextLong();
        scanner.nextLine();
        List<Product> list = repository.findByPrice(min, max);
        if (list.isEmpty()) {
            System.out.println("Could not find any products.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void searchByCategory() {
        System.out.println("Category: ");
        String category = scanner.nextLine();
        List<Product> list = repository.findByCategory(category);
        if (list.isEmpty()) {
            System.out.println("Could not find any products.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void searchByName() {
        System.out.println("Name: ");
        String  name = scanner.nextLine();
        List<Product> list = repository.findByName(name);
        if (list.isEmpty()) {
            System.out.println("Could not find any products.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void viewAll() {
        List<Product> list = repository.findAll();
        if (list.isEmpty()) {
            System.out.println("Could not find any products.");
        } else {
            System.out.println("All products: ");
            list.forEach(System.out::println);
        }

    }
}
