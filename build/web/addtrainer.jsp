<%-- 
    Document   : addtrainer
    Created on : 1 Ιουλ 2019, 9:49:08 μμ
    Author     : samsung np350
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Trainer</h1>
        <form name="addtrainer" method="POST" action="addtrainer">
            <table>
                <tr><td>First Name: </td><td><input name="fname" type="text"></td></tr>
                <tr><td>Last Name: </td><td><input name="lname" type="text"></td></tr>
                <tr><td><input type="Submit" name="new" value="Add Trainer" /></td></tr>
            </table>
        </form>
    </body>
</html>
