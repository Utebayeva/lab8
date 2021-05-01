<%@ page import="com.example.bookShop.models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.bookShop.models.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Books</title>
    </head>
    <body>
        <h2>Books</h2>
        <%
            User usr = (User) session.getAttribute("user");
            ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");
        %>

        <h4>Balance: <%= usr.getBalance() %></h4>
        <h4><a href="/profile.jsp">Profile</a></h4>
        <h4><a href="/index.jsp">Main page</a></h4>
        <h4><a href="/mainPage">Logout</a></h4>
        <hr>

        <%
            for (Product p: products) {
                out.println("<form action=\'/make-order\'>");
                out.println("<h3>Book name: " + p.getProduct_name() + "</h3>");
                out.println("<p>Description: " + p.getProduct_desc() + "</p>");
                out.println("<p>Quantity: " + p.getProduct_quantity() + "</p>");
                out.println("<p>Price: " + p.getProduct_price() + "</p>");
                out.println("<button name=\"product_id\" type=\"submit\" value=\"" + p.getProduct_id() + "\">Get</button>");
                out.println("<form>");
                out.println("<br><hr><br>");
            }
        %>
    </body>
</html>
