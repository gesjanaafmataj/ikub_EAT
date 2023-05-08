package main;

import java.util.Scanner;

public final class ClientMenu {

    private ClientMenu() {
    }

    public static void menu() {
        System.out.println("1) See all restaurants");
        System.out.println("2) Find restaurant by name");
        System.out.println("3) Check restaurant products");
        System.out.println("4) Check order status");
        System.out.println("5) List all orders");

        Scanner sc = new Scanner(System.in);
        int selectedMenu = sc.nextInt();

        switch (selectedMenu) {
            case 1:
                //kthe listen nga db dhe zgjidh nje prej vlerave
                System.out.println("Select one restaurant");
                break;
            case 2:
                //merr input emrin e restorantit dhe kthe listen nga db
                //zgjidh nje prej vlerave
                System.out.println("Select one restaurant");
                break;
            case 3:
                //merr listen e produkteve per menun aktive
                System.out.println("Insert products in shopping cart");
                //select product and quantity

                //place order
                break;
            case 4:
                //merr si input order id dhe kthe statusin per order
                break;
            case 5:
                //kthe listen per te gjitha orders qe ka kryer client
                break;
            default:
                System.out.println("Menu not available");
                break;
        }
    }
}
