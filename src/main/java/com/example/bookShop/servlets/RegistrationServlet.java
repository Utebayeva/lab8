package com.example.bookShop.servlets;

import com.example.bookShop.configs.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/registration")
public class RegistrationServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        if(!password.equals(password2)){
            resp.sendRedirect("/registration.jsp");
        } else {
            Connection connection = DatabaseConnection.getConnection();
            int rows = 0;
            PreparedStatement statementInsertOrder = null;

            try {
                statementInsertOrder = connection.prepareStatement("INSERT INTO users (user_login, user_password, user_balance) " +
                        "VALUES (?, ?, 10000);");
                statementInsertOrder.setString(1, login);
                statementInsertOrder.setString(2, password);
                statementInsertOrder.executeUpdate();

                if(rows > 0) {
                    System.out.println("User created");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        resp.sendRedirect("/authorization.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/mypage").include(req,resp);
    }
}
