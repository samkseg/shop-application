package se.iths;

import se.iths.controller.ShopController;

public class Main {
    static ShopController controller = new ShopController();
    public static void main(String[] args) {
        controller.menu();
    }
}