package util;

public final class Queries {

    private Queries() {
    }

    public static final String LOGIN = "SELECT u.first_name as name, u.email as email, r.name as role, u.id as id, re.id as restaurant_id " +
            "FROM users u " +
            "INNER JOIN roles r ON r.id = u.role_id " +
            "LEFT OUTER JOIN restaurants re ON re.user_id = u.id " +
            "WHERE u.email = ? AND u.password = ?;";

    public static final String INSERT_CATEGORY = "INSERT INTO categories (name, description) VALUES (?, ?)";
    public static final String FIND_CATEGORY_BY_NAME = "SELECT * FROM categories WHERE name = ?";
    public static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    public static final String DELETE_CATEGORY_BY_NAME = "DELETE FROM categories WHERE name = ?;";

    public static final String FIND_PRODUCTS_BY_CATEGORY_NAME = "SELECT * FROM products WHERE category_id IN (SELECT c.id FROM categories c WHERE c.name = ?);";

    public static final String INSERT_RESTAURANT = "INSERT INTO restaurants (name, address, phone_number) VALUES (?, ?, ?);";
    public static final String FIND_RESTAURANT_BY_NAME = "SELECT * FROM restaurants WHERE name = ?";
    public static final String GET_ALL_RESTAURANTS = "SELECT * FROM restaurants";
    public static final String DELETE_RESTAURANT_BY_NAME = "DELETE FROM restaurants WHERE name = ?";
    public static final String COUNT_RESTAURANTS_BY_MANAGER_EMAIL = "SELECT COUNT(*) as managerCount FROM restaurants WHERE user_id IN (SELECT u.id FROM users u WHERE u.email = ? AND role_id = 2);";
    public static final String ASSIGN_MANAGER_TO_RESTAURANT = "UPDATE restaurants SET user_id = (SELECT u.id FROM users u WHERE u.email = ?) WHERE name = ?;";

    public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status = ? WHERE id = ?;";
    public static final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?;";
    public static final String GET_ALL_ORDERS_SORTED_BY_DATE_DESC = "SELECT * FROM orders ORDER BY order_date DESC;";
    //public static final String FIND_ALL_ODER_BY_RESTAURANT_ID ="";

    public static final String INSERT_MENU = "INSERT INTO menus (restaurant_id, name, description, active_from, active_until) VALUES (?, ?, ?, ?, ?);";
    public static final String DELETE_MENU = "DELETE FROM menus WHERE name = ?;";
    public static final String FIND_ALL_MENUS_BY_RESTAURANT = "SELECT * FROM menus WHERE restaurant_id = ?;";
    public static final String FIND_NAME_OF_ACTIVE_MENU_OF_RESTAURANT = "SELECT name FROM menus WHERE restaurant_id = ? AND active_from <= curtime() AND active_until >= curtime();";

    public static final String FIND_ALL_PRODUCTS_FROM_ACTIVE_MENU_BY_RESTAURANT_NAME = "SELECT p.* FROM products p " +
            "INNER JOIN menus m on p.menu_id = m.id " +
            "INNER JOIN restaurants r on m.restaurant_id = r.id " +
            "WHERE r.name = ? AND active_from <= curtime() AND active_until >= curtime();";

    public static final String FIND_ALL_PRODUCTS_BY_ORDER_ID = "SELECT p.* FROM products p " +
            "INNER JOIN ORDER_PRODUCTS op ON op.product_id = p.id " +
            "INNER JOIN ORDERS o ON o.id = op.order_id " +
            "WHERE o.id = ?;";
}
