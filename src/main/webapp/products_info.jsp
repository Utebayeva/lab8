<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.bookShop.models.Product" %>
<%@ page import="com.example.bookShop.models.UserAdmin" %>
<%@ page import="com.example.bookShop.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Books info</title>
    </head>
    <body>
    <%! static int counter = 0;%>
    <%
        User user = (User) session.getAttribute("user");
        if (!(user instanceof UserAdmin)){
    %>
        <jsp:forward page="/error_page.jsp" >
            <jsp:param name="err" value="1" />
        </jsp:forward>
    <%
        }

        counter++;

        out.println("<p>You are on this page for the " + counter + " time</p>");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bookShop",
                    "postgres",
                    "password"
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<Product> products = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try {
                ResultSet rs = stmt.executeQuery("SELECT * FROM products;");
                while (rs.next()) {
                    Product newProduct = new Product(
                            Integer.parseInt(rs.getString("product_id")),
                            rs.getString("product_name"),
                            Double.parseDouble(rs.getString("product_price")),
                            rs.getString("product_desc"),
                            Integer.parseInt(rs.getString("product_quantity"))
                    );

                    products.add(newProduct);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(products.size() != 0){

    %>

    <h2>Products Information: </h2>
    <table style="border: 1px solid black;">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>quantity</th>
            <th>price</th>
        </tr>

    <%
          for (Product p: products){
              out.println("<tr>");
                out.println("<td>" + p.getProduct_id() + "</td>");
                out.println("<td>" + p.getProduct_name() + "</td>");
                out.println("<td>" + p.getProduct_desc() + "</td>");
                out.println("<td>" + p.getProduct_quantity() + "</td>");
                out.println("<td>" + p.getProduct_price() + "</td>");
              out.println("</tr>");
          }
        }
    %>

    </table>
    <h4><a href="/admin">Back</a></h4>
    <h4><a href="/profile.jsp">Profile</a></h4>
    <h4><a href="/index.jsp">Main page</a></h4>
    <h4><a href="/mainPage">Logout</a></h4>

    </body>
</html>
