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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/products")
public class ProductsServlet extends javax.servlet.http.HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();

        User usr = (User) session.getAttribute("user");

        if(usr == null){
            resp.sendRedirect("/authorization.jsp");
        }

        Connection connection = DatabaseConnection.getConnection();

        Statement stmt;
        ResultSet rs = null;
        ArrayList<Product> products = new ArrayList<>();

        try {
            stmt = connection.createStatement();
            if(stmt != null)
                rs = stmt.executeQuery("SELECT * FROM products");

            while (rs.next()){
                Product newProduct = new Product(
                        Integer.parseInt(rs.getString("product_id")),
                        rs.getString("product_name"),
                        Double.parseDouble(rs.getString("product_price")),
                        rs.getString("product_desc"),
                        Integer.parseInt(rs.getString("product_quantity")));
                products.add(newProduct);
            }

            session.setAttribute("products", products);
            getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }


}
