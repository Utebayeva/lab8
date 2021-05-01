<%@ page import="com.example.bookShop.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <%
            User user = (User) session.getAttribute("user");
            out.println(user.getLogin());
        %>
    </title>
</head>
<body>
    <h1>User Info</h1>
    <h2>User ID: <% out.println(user.getId()); %></h2>
    <h2>Login: <% out.println(user.getLogin()); %></h2>
    <h2>Balance: <% out.println(user.getBalance()); %></h2>

    <a href="/products">Books</a>
    <a href="/my_orders">My orders</a>
    <a href="/mainPage">Logout</a>
</body>
</html>
