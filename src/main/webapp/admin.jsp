<%@ page import="com.example.bookShop.models.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    <h1>Admin</h1>
    <h2>Add book</h2>
    <form action="/addNewProduct">
        <label>Book name</label>
        <input type="text" name="product_name">
        <br>
        <label>Description</label>
        <input type="text" name="product_desc">
        <br>
        <label>Quantity</label>
        <input type="text" name="product_quantity">
        <br>
        <label>Price</label>
        <input type="text" name="product_price">
        <br>
        <input type="submit">
    </form>

    <h2>User's order list: </h2>
    <br>
    <table border="1">
        <tr>
            <th>Order id</th>
            <th>User id</th>
            <th>Product id</th>
            <th>User login</th>
            <th>Product</th>
        </tr>
    <%
        ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orderList");
        for (Order o: orders) {
            out.println("<tr>");
            out.println("<td>" + o.getOrderId() + "</td>");
            out.println("<td>" + o.getUserId() + "</td>");
            out.println("<td>" + o.getProductId()+ "</td>");
            out.println("<td>" + o.getUserLogin() + "</td>");
            out.println("<td>" + o.getProductName() + "</td>");
            out.println("</tr>");
        }
    %>
    </table>

    <br><br>
    <h4><a href="/products_info.jsp">Books info</a></h4>
    <h4><a href="/products">Books</a></h4>
    <h4><a href="/mainPage">Logout</a></h4>
</body>
</html>
