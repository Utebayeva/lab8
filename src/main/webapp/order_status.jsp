<%@ page import="com.example.bookShop.models.User" %>
<%@ page import="com.example.bookShop.models.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Order</title>
    </head>
    <body>

        <%
            User user = (User) session.getAttribute("user");
            Product product = (Product) session.getAttribute("product");

            double beforeTransactionBalance = (double) session.getAttribute("beforeTransactionBalance");
            boolean transactionCheck = (boolean) session.getAttribute("transactionCheck");
        %>

        <h2>
            <%= user.getLogin() %> <br>
            Balance: <%= beforeTransactionBalance %>
        </h2>
        <hr>
        <%
            out.println("<p> Book name: " + product.getProduct_name() + "</p>");
            out.println("<p> Description: " + product.getProduct_desc() + "</p>");
            out.println("<p> Price: " + product.getProduct_price() + "</p>");
            out.println("<br>");
            if (transactionCheck) {
                out.println("<h2>Purchased</h2>");
            } else {
                out.println("<h2>Not enough money</h2>");
            }
        %>
        <br>
        <h4><a href="/products">Books</a></h4>
        <h4><a href="/index.jsp">Main page</a></h4>
        <h4><a href="/mainPage">Logout</a></h4>
    </body>
</html>


