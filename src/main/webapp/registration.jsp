<%@ page import="com.example.bookShop.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h1>Registration</h1>
    <h4><a href="/index.jsp">Back</a></h4>
    <form action="/registration">
        <%@ page import="javax.servlet.http.HttpServlet" %>
        <%!
            private HttpServletResponse resp;
        %>
        <%
            User usr = (User) session.getAttribute("user");
            if(usr == null){
        %>

        <div>
            <label for="login">Username: </label>
            <input type="text" name="login" id="login">
        </div>
        <div>
            <label for="password">Password: </label>
            <input type="text" name="password" id="password">
        </div>
        <div>
            <label for="password2">Repeat password:</label>
            <input type="text" name="password2" id="password2">
        </div>

        <input type="submit">

        <%
            } else {
                out.println("<h2>You are already logged in</h2><br>");
                out.println("<a href=\"/mypage\">Back</a><br>");
                out.println("<a href=\"/mainPage\">Exit</a>");
            }
        %>

    </form>
</body>
</html>
