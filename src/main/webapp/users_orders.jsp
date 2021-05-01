<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.bookShop.models.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's Orders</title>
</head>
<body>
    <table style="border: 1px solid black">
        <tr>
            <th>Order id</th>
            <th>Book id</th>
            <th>Book name</th>
        </tr>
        <%
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("users_orders");
            for (Order order: orders) {
                out.println("<tr>");
                out.println("<td>" + order.getOrderId() + "</td>");
                out.println("<td>" + order.getProductId() + "</td>");
                out.println("<td>" + order.getProductName() + "</td>");
                out.println("</tr>");
            }
        %>
    </table>

    <br><br>
    <a href="/profile.jsp">Profile</a>
    <a href="/products">Books</a>
</body>
</html>
