<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <%
        String err = request.getParameter("err");
        if (err.equals("1"))
            out.println("<h2>This page is only available to admins</h2>");
        else if (err.equals("2"))
            out.println("<h2>You must log in</h2>");
    %>
</body>
</html>
