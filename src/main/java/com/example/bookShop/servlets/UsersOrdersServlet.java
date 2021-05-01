package com.example.bookShop.servlets;

import com.example.bookShop.configs.DatabaseConnection;
import com.example.bookShop.models.Order;
import com.example.bookShop.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/my_orders")
public class UsersOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        ArrayList<Order> orders = new ArrayList<>();

        Connection connection = DatabaseConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE user_id = " + user.getId() + " ;");

            while (rs.next()) {
                Order order = new Order(
                        Integer.parseInt(rs.getString("order_id")),
                        Integer.parseInt(rs.getString("user_id")),
                        Integer.parseInt(rs.getString("product_id")),
                        rs.getString("user_login"),
                        rs.getString("product_name")
                );
                orders.add(order);
            }
            session.setAttribute("users_orders", orders);
            getServletContext().getRequestDispatcher("/users_orders.jsp").forward(req, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
