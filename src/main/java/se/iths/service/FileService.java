package se.iths.service;

import se.iths.model.Order;
import se.iths.model.OrderLine;
import se.iths.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileService {
    private HashMap<Long, Product> products = new HashMap<>();
    private HashMap<Long, Order> orders = new HashMap<>();
    private BufferedReader bufferedReader;
    private String[] strings;
    private String[] orderLines;
    private String data;
    private long productCount = 0;
    private long orderCount = 0;

    public void fileReader() {
        try {
            bufferedReader = new BufferedReader(new FileReader("inventory.txt"));
            loadProducts();
            bufferedReader = new BufferedReader(new FileReader("orders.txt"));
            loadOrders();
        } catch (Exception e) {
            fileWriter();
        }
    }


    public void fileWriter() {
        try {
            BufferedWriter productsWriter = new BufferedWriter(new FileWriter("inventory.txt"));
            readProducts(productsWriter);
            BufferedWriter orderWriter = new BufferedWriter(new FileWriter("orders.txt"));
            readOrders(orderWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readOrders(BufferedWriter writer) throws IOException {
        writer.write(writeOrders(orders));
        writer.close();
    }

    private String writeOrders(HashMap<Long, Order> orders) {
        if (!(orders.get(orders.size() -1) == null)) {
            orderCount = orders.get(orders.size() -1).getId();
        }
        data = orderCount + "\n";
        for (Order order : orders.values()) {
            data += order.getId() + "/";
            data += order.getPrice() + "/";
            for (OrderLine orderLine : order.getOrderLines()) {
                if (!orderLine.getPrice().contains("%")) {
                    data += orderLine.getProductId() + "," + orderLine.getProduct() + "," + orderLine.getQuantity() + "," + orderLine.getTotalPrice() + ",";

                }}
            data += "/" + "\n";
        }
        return data;
    }

    private void loadOrders() throws IOException {
        orderCount = Long.parseLong(bufferedReader.readLine());
        String row;
        while ((row = bufferedReader.readLine()) != null) {
            strings = row.split("/");
            orderLines = strings[2].split(",");
            List<OrderLine> list = new ArrayList<>();
            for (int i = 0; i < orderLines.length; i+=4) {
                list.add(new OrderLine(Long.parseLong(orderLines[i]), orderLines[i+1], Long.parseLong(orderLines[i+2]), orderLines[i+3]));
            }
            orders.put(Long.parseLong(strings[0]),new Order(Long.parseLong(strings[0]), Double.parseDouble(strings[1]), list));
        }
    }

    private void readProducts(BufferedWriter writer) throws IOException {
        writer.write(writeProducts(products));
        writer.close();
    }

    private String writeProducts(HashMap<Long, Product> products) {
        if (!(products.get(products.size()-1) == null)) {
            productCount = products.get(products.size()-1).getId();
        }
        data = productCount + "\n";
        for (Product product : products.values()) {
            data += product.getId() + "," + product.getName()  + "," + product.getPrice() + "," + product.getCategory() + "," + product.getBrand() + "," + product.getQuantity()  + "," + "\n";
        }
        return data;
    }

    private void loadProducts() throws IOException {
        productCount = Long.parseLong(bufferedReader.readLine());
        String row;
        while ((row = bufferedReader.readLine()) != null) {
            strings = row.split(",");
            for (int i = 0; i < strings.length; i+=6) {
                products.put(Long.parseLong(strings[i]),new Product(Long.parseLong((strings[i])), strings[i+1], Double.parseDouble(strings[i+2]), strings[i+3], strings[i+4], Long.parseLong(strings[i+5])));
            }
        }
    }

    public HashMap<Long, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Long, Order> orders) {
        this.orders = orders;
    }

    public HashMap<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Long, Product> products) {
        this.products = products;
    }

    public long getProductCount() {
        return productCount;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public void setOrderCount(long orderCount) {
        this.orderCount = orderCount;
    }
}
