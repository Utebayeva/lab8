package com.example.bookShop.servlets;

import com.example.bookShop.configs.DatabaseConnection;
import com.example.bookShop.models.Product;
import com.example.bookShop.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/make-order")
public class BuyProductServlet extends javax.servlet.http.HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        Product product = null;
        boolean transactionCheck = true;
        session.setAttribute("beforeTransactionBalance", user.getBalance());

        try {
            Connection connection = DatabaseConnection.getConnection();

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE product_id = " + Integer.parseInt(req.getParameter("product_id")) + " ;");

            while (rs.next()) {
                product = new Product(
                        Integer.parseInt(rs.getString("product_id")),
                        rs.getString("product_name"),
                        Double.parseDouble(rs.getString("product_price")),
                        rs.getString("product_desc"),
                        Integer.parseInt(rs.getString("product_quantity"))
                );
            }

            System.out.println("Product: " + product.getProduct_name() +  " " + product.getProduct_id());


            int rowsDecreaseUserBalance = 0, rowsDecreaseProductQuantity = 0;
            if (user.getBalance() >= product.getProduct_price()) {
                double afterTransactionBalance = user.getBalance()-product.getProduct_price();
                System.out.println("After transaction balance: " + afterTransactionBalance);
                PreparedStatement stmtUserBalance = connection.prepareStatement("UPDATE users SET user_balance=" + afterTransactionBalance + " WHERE user_id = " + user.getId() + " ;");
                PreparedStatement stmtProductQuantity = connection.prepareStatement("UPDATE products SET product_quantity=" + (product.getProduct_quantity()-1) + " WHERE product_id=" + product.getProduct_id() + " ;");

                rowsDecreaseUserBalance = stmtUserBalance.executeUpdate();
                rowsDecreaseProductQuantity = stmtProductQuantity.executeUpdate();

                user.setBalance(afterTransactionBalance);
                session.setAttribute("user", user);
                session.setAttribute("product", product);
            }
            if (rowsDecreaseUserBalance>0 && rowsDecreaseProductQuantity>0) {
                transactionCheck = true;
                session.setAttribute("transactionCheck", transactionCheck);

                int rowsAddOrder = 0;
                Statement statementInsertOrder = connection.createStatement();
                rowsAddOrder = statementInsertOrder.executeUpdate("INSERT INTO orders (user_id, product_id, user_login, product_name) " +
                        "VALUES (" + user.getId() + " , " + product.getProduct_id() + " , '" + user.getLogin() + "' , '" + product.getProduct_name() + "');");

                if(rowsAddOrder > 0)
                    System.out.println("Order inserted");
            }
            System.out.println("Transaction check: " + transactionCheck);

            getServletContext().getRequestDispatcher("/order_status.jsp").forward(req, resp);
        } catch ( SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
