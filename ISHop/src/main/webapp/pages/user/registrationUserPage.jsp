<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>

<div style="background: #E0E0E0; height: 85px; padding: 10px;">
    <div style="float: left">
        <h1>IShop</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <!-- User store in session with attribute: loginedUser -->
        Hello <b>${loginedUser.login}</b>
        <br/>
        Search <input name="search">

    </div>

</div>

<div style="padding: 5px;" align="right">

    <a href="${pageContext.request.contextPath}/">Home</a>
    |
    <a href="${pageContext.request.contextPath}/product/productList">Product List</a>
    |
    <a href="${pageContext.request.contextPath}/user/userInfo">My Account Info</a>
    |
    <a href="${pageContext.request.contextPath}/user/login">Login</a>

</div>

<h3>Create User</h3>
<form method="POST" action="${pageContext.request.contextPath}/user/registration">
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
            <td><input type="password" name="password" placeholder="password"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<div
        style="background: #E0E0E0; text-align: center; padding: 5px; margin-top: 10px;">

    @Copyright it-academy.by

</div>

</body>
</html>
