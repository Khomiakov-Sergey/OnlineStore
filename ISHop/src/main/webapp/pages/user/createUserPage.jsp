<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>
        Create Users
    </title>
</head>
<body>

<div style="padding: 5px;">
    <a href="${pageContext.request.contextPath}/productList">Product List</a>
    <a href="${pageContext.request.contextPath}/userList">User List</a>

</div>

<h3>Create User</h3>
<form method="POST" action="${pageContext.request.contextPath}/user/create">
    <table border="0">
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstName" placeholder="firstName"/></td>
        </tr>
        <tr>
            <td>Second name</td>
            <td><input type="text" name="secondName" placeholder="secondName"/></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="text" name="age" placeholder="age"/></td>
        </tr>

        <tr>
            <td>Login</td>
            <td><input type="text" name="login" placeholder="login"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" placeholder="login"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/userList">Cancel</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
