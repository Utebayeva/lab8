package com.example.bookShop.servlets;

import com.example.bookShop.configs.DatabaseConnection;
import com.example.bookShop.models.User;
import com.example.bookShop.models.UserAdmin;
import com.example.bookShop.models.UserJust;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/auth")
    public class AuthorizationServlet extends javax.servlet.http.HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Connection connection = DatabaseConnection.getConnection();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE user_login like '" + login + "'");

            User user = null;

            while (rs.next()){
                if(login.equals("admin")){
                    user = new UserAdmin(
                            Integer.parseInt(rs.getString("user_id")),
                            rs.getString("user_login"),
                            rs.getString("user_password"),
                            Double.parseDouble(rs.getString("user_balance")));
                } else {
                    user = new UserJust(
                            Integer.parseInt(rs.getString("user_id")),
                            rs.getString("user_login"),
                            rs.getString("user_password"),
                            Double.parseDouble(rs.getString("user_balance")));
                }
            }

            if(user != null){
                if(password.equals(user.getPassword())){
                    session.setAttribute("user", user);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("/authorization.jsp");
    }
}
