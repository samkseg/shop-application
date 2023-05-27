package se.iths.controller;

import se.iths.data.OrderRepository;
import se.iths.data.ProductRepository;
import se.iths.model.*;
import se.iths.model.SummerDiscount;
import se.iths.model.Over15kDiscount;
import se.iths.service.FileService;

import java.util.*;

public class ShopController {
    static long productCount = 0;
    static long orderCount = 0;
    ProductRepository productRepository = new ProductRepository();
    OrderRepository orderRepository = new OrderRepository();
    FileService fileService = new FileService();
    Scanner scanner = new Scanner(System.in);
    Cart cart = new Cart();
    public ShopController() {
    }
    public void start() {
        fileService.fileReader();
        productRepository.setRepository(fileService.getProducts());
        orderRepository.setRepository(fileService.getOrders());
        productCount = fileService.getProductCount();
        orderCount = fileService.getOrderCount();
        menu();
    }

    private void shutDown() {
        save();
        System.exit(0);
    }

    private void save() {
        fileService.setOrderCount(orderCount);
        fileService.setProductCount(productCount);
        fileService.setProducts(productRepository.getRepository());
        fileService.setOrders(orderRepository.getRepository());
        fileService.fileWriter();
    }

    public void menu() {
        while (true) {
            System.out.println("""
                Menu
                
                1. View Shop
                2. Product Register
                
                3. Exit
                """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> viewMenu();
                case "2" -> productRegister();
                case "3", "e", "E" -> shutDown();
            }
        }
    }

    private void productRegister() {
        boolean loop = true;
        while (loop) {
            System.out.println("""
                Product Register
                
                1. Add product
                2. View Products
                3. Update product
                4. Remove product
                
                5. Back
                """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addMenu();
                case "2" -> viewAll();
                case "3" -> updateMenu();
                case "4" -> removeMenu();
                case "5", "e", "E" -> {loop = false;}
            }
        }
    }

    private void removeMenu() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            String name = optionalProduct.get().getName();
            productRepository.removeProduct(id);
            save();
            System.out.println(name + " has been removed.");
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
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
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setQuantity(quantity);
            save();
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New quantity: " + quantity);
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void updateBrand() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Update brand: ");
        String category = scanner.nextLine();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setCategory(category);
            save();
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New category: " + category);
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void updateCategory() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Update category: ");
        String category = scanner.nextLine();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setCategory(category);
            save();
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New category: " + category);
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void updatePrice() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        System.out.println("Update price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setPrice(price);
            save();
            System.out.println(optionalProduct.get().getName() + " has been updated.");
            System.out.println("New price: " + price);
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void updateName() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Update name: ");
        String name = scanner.nextLine();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setName(name);
            save();
            System.out.println(name + " has been updated.");
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
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
        Product product = new Product(productCount +1, name, price, category, brand, quantity);
        productRepository.addProduct(product);
        productCount = productCount +1;
        save();
        System.out.println(product.getName() + " has been added.");
    }

    private void viewMenu() {
        boolean loop = true;
        while (loop) {
            System.out.println("""
                Shop Menu
                
                1. Add to Cart
                2. View Cart
                3. View Orders
                
                4. View All products
                5. Search by name
                6. Search by category
                7. Find by price
                8. Find by ID
                
                9. Back
                """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addToCart();
                case "2" -> viewCart();
                case "3" -> viewOrders();
                case "4" -> viewAll();
                case "5" -> searchByName();
                case "6" -> searchByCategory();
                case "7" -> findByPrice();
                case "8" -> findById();
                case "9", "e", "E" -> {loop = false;}
            }
        }
    }

    private void viewOrders() {
        boolean loop = true;
        while (loop) {
            List<Order> orders = orderRepository.findAll();
            if (orders.isEmpty()) {
                System.out.println("Could not find any orders.");
                goBack();
                loop = false;
            } else {
                System.out.println("All Orders: ");
                for (Order order : orders) {
                    System.out.println("Order ID: " + order.getId() + " Total Price: " + order.getPrice() + " SEK");
                }
                System.out.println("""
                
                1. View order details
                2. Back
                """);
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> viewOrder();
                    case "2", "e", "E" -> {loop = false;}
                }
            }
        }
    }

    private void viewOrder() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            System.out.println(optionalOrder.get());
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void viewCart() {
        boolean loop = true;
        while (loop) {
            System.out.println("""
                Cart""");
            for (CartItem cartItem : cart.getItems()) {
                System.out.println(cartItem.getQuantity() + " " + cartItem.getProduct().getName() + " " + cartItem.getPrice() + " SEK ID: " + cartItem.getProductId());
            }
            if (!cart.getDiscounts().isEmpty()) {
                System.out.println(1 + "  Discount -" + cart.getDiscount() + " SEK");
            }
            System.out.println("\nTotal price: " + cart.getTotalPrice() + " SEK\n");
            System.out.println("""
                1. Confirm
                2. Add/remove discount
                3. Update quantity
                4. Remove item
                4. Empty
                
                5. Back
                """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    checkout();
                    loop = false;
                }
                case "2" -> addDiscount();
                case "3" -> updateCart();
                case "4" -> removeCartItem();
                case "5" -> {
                    cart.clear();
                    System.out.println("Cart has been emptied.");
                    goBack();
                    loop = false;
                }
                case "6", "e", "E" -> {loop = false;}
            }
        }
    }

    private void updateCart() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        System.out.println("Update quantity: ");
        long quantity = scanner.nextLong();
        scanner.nextLine();
        Optional<CartItem> optionalProduct = cart.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setQuantity(quantity);
            save();
            System.out.println(optionalProduct.get().getProduct().getName() + " has been updated.");
            System.out.println("New quantity: " + quantity);
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void addDiscount() {
        System.out.println("\nTotal price: " + cart.getTotalPrice() + " SEK\n");
        System.out.println("""
                Add Discount
                
                1. (20 %) discount for orders over 15k
                2. (10 %) Summer discount
                3. Remove discounts
                
                4. Back
                """);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                add15kDiscount();
            }
            case "2" -> {
                addSummerDiscount();
            }
            case "3" -> {
                removeDiscount();
            }
            case "4", "e", "E" -> {}
        }
    }

    private void addSummerDiscount() {
        cart.addDiscounts(new SummerDiscount(1L));
        System.out.println("Discount has been added.");
        goBack();
    }

    private void removeDiscount() {
        cart.setDiscounts(new HashSet<>());
        System.out.println("Discounts has been removed.");
        goBack();
    }

    private void add15kDiscount() {
        if (cart.getTotalPrice() > 15000) {
            cart.addDiscounts(new Over15kDiscount(2L));
            System.out.println("Discount has been added.");
        } else {
            System.out.println("This discount can only be applied on orders over 15000 SEK.");
        }
        goBack();
    }

    private void removeCartItem() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<CartItem> item = cart.findById(id);

        if(item.isPresent()) {
            cart.remove(item.get());
            System.out.println("Cart has been updated.");
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void checkout() {
        if (!cart.getItems().isEmpty()) {
            List<OrderLine> orderLines = new ArrayList<>();
            for (CartItem cartItem : cart.getItems()) {
                orderLines.add(new OrderLine(cartItem.getProduct(),cartItem.getQuantity()));
            }
            if (!cart.getDiscounts().isEmpty()) {
                orderLines.add(new OrderLine(0L, "Discount", 1L, "-" + String.valueOf(cart.getDiscount())));
            }
            orderCount = orderCount + 1;
            Order order = new Order(orderCount, cart.getTotalPrice(), orderLines);
            orderRepository.addOrder(orderCount, order);
            for (OrderLine orderLine : order.getOrderLines()) {
                Optional<Product> product = productRepository.findById(orderLine.getProductId());
                if (product.isPresent()) {
                    product.get().setQuantity(product.get().getQuantity() - orderLine.getQuantity());
                }
            }
            cart.clear();
            save();
            System.out.println("Order " + orderCount + " has been placed.");
        } else {
            System.out.println("Cart is empty.");
        }
        goBack();
    }

    private void addToCart() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            boolean loop = true;
            while (loop) {
                System.out.println("Quantity: ");
                long quantity = scanner.nextLong();
                scanner.nextLine();
                if (quantity > 0 && quantity <= optionalProduct.get().getQuantity()) {
                    Optional<CartItem> cartItem = cart.add(new CartItem(optionalProduct.get(), quantity));
                    if (cartItem.isPresent()) {
                        System.out.println(quantity + " " + optionalProduct.get().getName() + " has been added to cart.");

                    } else {
                        System.out.println("Product already in cart.");
                    }
                    loop = false;
                } else {
                    System.out.println("Invalid quantity.");
                    loop = false;
                }
            }
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void findById() {
        System.out.println("ID: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            System.out.println(optionalProduct.get());
        } else {
            System.out.println("Could not find product.");
        }
        goBack();
    }

    private void findByPrice() {
        System.out.println("Minimum price: ");
        long min = scanner.nextLong();
        System.out.println("Max price: ");
        long max = scanner.nextLong();
        scanner.nextLine();
        List<Product> list = productRepository.findByPrice(min, max);
        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        } else {
            System.out.println("Could not find any products.");
        }
        goBack();
    }

    private void searchByCategory() {
        System.out.println("Category: ");
        String category = scanner.nextLine();
        List<Product> list = productRepository.findByCategory(category);
        if (!list.isEmpty()) {
            list.forEach(System.out::println);

        } else {
            System.out.println("Could not find any products.");
        }
        goBack();
    }

    private void searchByName() {
        System.out.println("Name: ");
        String  name = scanner.nextLine();
        List<Product> list = productRepository.findByName(name);
        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        } else {
            System.out.println("Could not find any products.");
        }
        goBack();
    }

    private void viewAll() {
        List<Product> list = productRepository.findAll();
        if (!list.isEmpty()) {
            System.out.println("All products: ");
            list.forEach(System.out::println);
        } else {
            System.out.println("Could not find any products.");
        }
        goBack();
    }

    private void goBack() {
        System.out.println("\n1. Back");
        scanner.nextLine();
    }
}
