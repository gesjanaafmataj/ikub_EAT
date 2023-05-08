package main;

import model.Category;
import model.Restaurant;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.RestaurantService;
import service.RestaurantServiceImpl;

import java.util.List;
import java.util.Scanner;

public final class AdminMenu {

    private AdminMenu() {
    }

    public static void menu() {
        System.out.println("1) Manage Restaurants");
        System.out.println("2) Manage Users");
        System.out.println("3) List Orders");
        System.out.println("4) Manage Categories");

        Scanner sc = new Scanner(System.in);
        char exit;

        do {
            int selectedMenu = sc.nextInt();

            switch (selectedMenu) {
                case 1:
                    manageRestaurantMenu();
                    break;
                case 2:
                    manageUsers();
                    break;
                case 3:
                    break;
                case 4:
                    manageCategories();
                    break;
                default:
                    System.out.println("Menu not available");
            }

            sc.nextLine();
            exit = sc.nextLine().charAt(0);
        } while (exit != 'Q');
    }

    private static void manageRestaurantMenu() {
        Scanner sc = new Scanner(System.in);
        char exit;

        do {
            System.out.println("----------- RESTAURANT -----------");
            System.out.println("1) Create");
            System.out.println("2) Update");
            System.out.println("3) Delete");
            System.out.println("4) Find by name");
            System.out.println("5) Show all");
            System.out.println("6) Assign Manager To Restaurant");

            int subMenu = sc.nextInt();
            sc.nextLine();

            RestaurantService service = new RestaurantServiceImpl();

            switch (subMenu) {
                case 1:
                    System.out.println("Name");
                    String name = sc.nextLine();
                    System.out.println("Address");
                    String address = sc.nextLine();
                    System.out.println("Phone number");
                    String phoneNumber = sc.nextLine();
                    boolean isSaved = service.save(name, address, phoneNumber);
                    if (isSaved) {
                        System.out.println("Restaurant was saved successfully");
                    }
                    break;
                case 2:
                    System.out.println("Not available yet!");
                    break;
                case 3:
                    //Todo:: delete only restaurants that don't have any menu
                    System.out.println("Name of restaurant you want to delete");
                    String toDelete = sc.nextLine();
                    service.delete(toDelete);
                    break;
                case 4:
                    System.out.println("Search for restaurant by name");
                    String search = sc.nextLine();
                    Restaurant restaurant = service.findRestaurantByName(search);
                    System.out.println(restaurant);
                    break;
                case 5:
                    List<Restaurant> restaurants = service.findAll();
                    restaurants.forEach(System.out::println);
                    //same as above
                    /*for(Restaurant r : restaurants) {
                        System.out.println(r);
                    }*/
                    break;
                case 6:
                    //Todo:: afisho listen e menaxhereve qe nuk kane nje restorant dhe te restoranteve qe nuk kane menaxher
                    System.out.println("Manager email");
                    String managerEmail = sc.nextLine();
                    System.out.println("Restaurant name");
                    String restaurantName = sc.nextLine();
                    service.assignManagerToRestaurant(managerEmail, restaurantName);
                    break;
                default:
                    System.err.println("Menu not available");
            }

            System.out.println("Press any key to continue or Q to go to the previous menu");
            exit = sc.nextLine().charAt(0);
        } while (exit != 'Q');
    }

    private static void manageUsers() {
        System.out.println("1) Create");
        System.out.println("2) Update");
        System.out.println("3) Delete");
        //always return only one or 0
        System.out.println("4) Find by phone number");
        //return 0 or more
        //System.out.println("4) Find by first name and/or last name");
        System.out.println("5) Show all");
    }

    private static void manageCategories() {
        Scanner sc = new Scanner(System.in);
        char exit;

        do {
            System.out.println("----------- CATEGORY -----------");
            System.out.println("1) Create");
            System.out.println("2) Update");
            System.out.println("3) Delete");
            System.out.println("4) Find by name");
            System.out.println("5) Show all");
            int subMenu = sc.nextInt();
            sc.nextLine();

            CategoryService categoryService = new CategoryServiceImpl();

            switch (subMenu) {
                case 1:
                    System.out.println("Category name");
                    String name = sc.nextLine();
                    System.out.println("Category description");
                    String description = sc.nextLine();
                    categoryService.save(name, description);
                    System.out.println("Category inserted");
                    break;
                case 2:
                    System.out.println("Update not implemented yet!");
                    break;
                case 3:
                    System.out.println("Category name you want to delete");
                    String categoryToDelete = sc.nextLine();
                    categoryService.deleteByName(categoryToDelete);
                    break;
                case 4:
                    String categoryToFind = sc.nextLine();
                    Category category = categoryService.findByName(categoryToFind);
                    System.out.println(category);
                    break;
                case 5:
                    List<Category> categories = categoryService.findAll();
                    System.out.println(categories);
                    break;
                default:
                    System.err.println("Menu not available");
            }

            System.out.println("Press any key to continue or Q to go to the previous menu");
            exit = sc.nextLine().charAt(0);
        } while (exit != 'Q');
    }
}
