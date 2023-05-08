package main;

import model.AuthenticatedUser;
import model.OrderStatus;
import service.UserService;
import service.UserServiceImpl;

import java.util.Scanner;

public class Main {

    public static AuthenticatedUser auth = null;

    public static void main(String[] args) {

        String status = "APPROVED";
        System.out.println(OrderStatus.findStatus(status));

        System.out.println("Welcome to ikubEat");
        try (Scanner sc = new Scanner(System.in)) {
            char exit;
            do {
                System.out.println("Login");
                System.out.println("E-mail address");
                String email = sc.nextLine();
                System.out.println("Password");
                String password = sc.nextLine();
                if (!email.isEmpty() && !password.isEmpty()) {
                    UserService userService = new UserServiceImpl();
                    userService.login(email, password);
                    if (auth != null) {
                        System.out.println("Welcome " + auth.getName());
                        showMenu();
                    } else {
                        System.out.println("Wrong credentials! Try again!");
                    }
                } else {
                    System.out.println("Email and password are required");
                }
                System.out.println("Press Q to exit or any other key to login again");
                exit = sc.nextLine().charAt(0);
            } while (exit != 'Q');
            System.out.println("Thank you for using ikubEat");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void showMenu() {
        switch (auth.getRole()) {
            case "ADMIN":
                AdminMenu.menu();
                break;
            case "RESTAURANT":
                System.err.println(auth.getRestaurantId());
                if (auth.getRestaurantId() > 0) {
                    RestaurantMenu.menu();
                    break;
                } else {
                    System.out.println("You do not manage any restaurant!");
                    return;
                }
            case "CLIENT":
                ClientMenu.menu();
                break;
            default:
                System.err.println("Menu not available!");
        }
    }
}
