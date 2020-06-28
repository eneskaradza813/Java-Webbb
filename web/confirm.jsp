<%-- 
    Document   : confirm
    Created on : Jun 28, 2020, 1:23:24 PM
    Author     : mesa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>You entered:</h1>
        <p>Your first name: <%=request.getParameter("first_name")%></p>
        <p>Your last name: <%=request.getParameter("last_name") %></p>
        <p>Your age: <%=request.getParameter("age") %></p>
    </body>
</html>
