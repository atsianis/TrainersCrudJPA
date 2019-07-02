<%-- 
    Document   : updatetrainer
    Created on : 1 Ιουλ 2019, 10:56:44 μμ
    Author     : samsung np350
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entities.Trainer"%>
<%! private Trainer t; %>
<% t = (Trainer)request.getAttribute("trainer"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        <h1>Update Trainer</h1>
        <form method="POST" action="updatetrainer">
            <input type="hidden" name="id" value="<%= t.getId() %>" />           
            First Name: <input name="name" type="text" value="<%= t.getFname() %>"/><br />
            Last Name: <input name="surname" type="text" value="<%= t.getLname() %>"/><br />
            <input type="Submit" name="new" value="Update Trainer" /><br />
        </form>
</html>
