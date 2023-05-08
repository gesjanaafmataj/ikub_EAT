package main;

import model.Menu;
import service.MenuService;
import service.MenuServiceImpl;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public final class RestaurantMenu {

    private RestaurantMenu() {
    }

    public static void menu() {
        System.out.println("1) Manage menus");
        System.out.println("2) Manage products");
        System.out.println("3) Process incoming orders");
        System.out.println("4) List all orders");

        Scanner sc = new Scanner(System.in);
        int selectedMenu = sc.nextInt();

        switch (selectedMenu) {
            case 1:
                manageMenus();
                break;
            case 2:
                manageProducts();
                break;
            case 3:
                processOrdersMenu();
                break;
            case 4:
                break;
            default:
                System.out.println("Menu not available");
                break;
        }
    }

    private static void manageMenus() {
        Scanner sc = new Scanner(System.in);
        char exit;

        do {
            System.out.println("----------- MENU -----------");
            System.out.println("1) Create");
            System.out.println("2) Update");
            System.out.println("3) Delete");
            System.out.println("4) Show all");

            int subMenu = sc.nextInt();
            sc.nextLine();

            MenuService service = new MenuServiceImpl();

            switch (subMenu) {
                case 1:
                    System.out.println("Name:");
                    String name = sc.nextLine();
                    System.out.println("Description");
                    String description = sc.nextLine();
                    System.out.println("Active from (hh:mm:ss)");
                    String activeFrom = sc.nextLine();
                    System.out.println("Active until (hh:mm:ss)");
                    String activeUntil = sc.nextLine();
                    Menu menu = new Menu();
                    menu.setName(name);
                    menu.setDescription(description);
                    Time activeFromTime = new Time(Integer.parseInt(activeFrom.substring(0, 2)), Integer.parseInt(activeFrom.substring(3, 5)), Integer.parseInt(activeFrom.substring(6)));
                    Time activeUntilTime = new Time(Integer.parseInt(activeUntil.substring(0, 2)), Integer.parseInt(activeUntil.substring(3, 5)), Integer.parseInt(activeUntil.substring(6)));
                    menu.setActiveFrom(activeFromTime);
                    menu.setActiveUntil(activeUntilTime);

                    service.save(menu);
                    break;
                case 2:
                    System.out.println("Not available yet!");
                    break;
                case 3:
                    System.out.println("Name of menu you want to delete:");
                    String toDelete = sc.nextLine();
                    service.delete(toDelete);
                    break;
                case 4:
                    List<Menu> menus = service.findAll();
                    menus.forEach(System.out::println);
                    /*for(Menu m : menus) {
                        System.out.println(m);
                    }*/
                    break;
                default:
                    System.err.println("Menu not available");
            }

            System.out.println("Press any key to continue or Q to go to the previous menu");
            exit = sc.nextLine().charAt(0);
        } while (exit != 'Q');
    }

    private static void manageProducts() {
        System.out.println("1) Create");
        System.out.println("2) Update");
        System.out.println("3) Delete");
        System.out.println("4) Find by name and menu");
        System.out.println("5) Show all");
    }

    private static void processOrdersMenu() {
        System.out.println("1) List new orders");
        System.out.println("2) Approve order");
        System.out.println("3) Reject order");
    }
}
