package repository;

import model.Order;
import model.OrderStatus;
import util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.Queries.*;

public class OrderRepository {

    public List<Order> findAllAndSortByDateDESC() {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDERS_SORTED_BY_DATE_DESC)) {
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setNumberOfProducts(rs.getInt("number_of_products"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setOrderDate(rs.getDate("order_date"));
                String status = rs.getString("status");
                order.setStatus(OrderStatus.findStatus(status));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public void changeOrderStatus(Integer orderId, OrderStatus status) {
        Order order = findOrderById(orderId);
        if (order != null) {
            if ((order.getStatus().equals(OrderStatus.CREATED) && status.equals(OrderStatus.APPROVED) ||
                    (order.getStatus().equals(OrderStatus.CREATED) && status.equals(OrderStatus.REJECTED))) ||
                    (order.getStatus().equals(OrderStatus.APPROVED) && status.equals(OrderStatus.PREPARED)) ||
                    (order.getStatus().equals(OrderStatus.PREPARED) && status.equals(OrderStatus.DELIVERED))
            ) {
                try (Connection connection = JdbcConnection.connect();
                     PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
                    ps.setString(1, status.getValue());
                    ps.setInt(2, orderId);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public Order findOrderById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement ps = connection.prepareStatement(FIND_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                String orderStatus = rs.getString("status");
                order.setStatus(OrderStatus.findStatus(orderStatus));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setNumberOfProducts(rs.getInt("number_of_products"));
                return order;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    }

