package se.iths.service;

import se.iths.model.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class FileService {
    Scanner input = new Scanner(System.in);
    private HashMap<Long, Product> products = new HashMap<>();
    BufferedReader bufferedReader;
    String[] strings;
    String data;
    private long id = 0;

    public void fileReader() {
        try {
            bufferedReader = new BufferedReader(new FileReader("inventory.txt"));
            loadFile();
        } catch (Exception e) {
            fileWriter();
        }
    }

    public void fileWriter() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"));
            loadData(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadData(BufferedWriter writer) throws IOException {
        writer.write(writeData(products));
        writer.close();
    }

    private String writeData(HashMap<Long, Product> products) {
        if (!products.isEmpty() && !(products.get(products.size()) == null)) {
            id = products.get(products.size()).getId();
        }
        data = id + "\n";
        for (Product product : products.values()) {
            data += product.getId() + "," + product.getName()  + "," + product.getPrice() + "," + product.getCategory() + "," + product.getBrand() + "," + product.getQuantity()  + "," + "\n";
        }
        return data;
    }

    private void loadFile() throws IOException {
        id = Long.parseLong(bufferedReader.readLine());
        String row;
        while ((row = bufferedReader.readLine()) != null) {
            strings = row.split(",");
            for (int i = 0; i < strings.length; i+=6) {
                products.put(Long.parseLong(strings[i]),new Product(Long.parseLong((strings[i])), strings[i+1], Double.parseDouble(strings[i+2]), strings[i+3], strings[i+4], Long.parseLong(strings[i+5])));
            }
        }
    }

    public HashMap<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Long, Product> products) {
        this.products = products;
    }

    public long getCount() {
        return id;
    }
}
