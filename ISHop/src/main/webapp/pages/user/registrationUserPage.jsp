<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Create Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>

<c:import url="/pages/fragments/header.jsp"/>
<c:import url="/pages/fragments/menu.jsp"/>

<h3>Create User</h3>
<form method="POST" action="${pageContext.request.contextPath}/user/registration">
    <table border="0">
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstName" placeholder="firstName" pattern="^[a-zA-Zа-яА-ЯёЁ]+$"
                       title="First name should contain only letters. e.g. Jonh"/></td>
        </tr>
        <tr>
            <td>Second name</td>
            <td><input type="text" name="secondName" placeholder="secondName" pattern="^[a-zA-Zа-яА-ЯёЁ]+$"
                       title="Second name should contain only letters. e.g. Smith"/></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="text" name="age" placeholder="age" pattern="^[0-9]{1,3}$"
                       title="Age should only contain integers. e.g. 28"/></td>
        </tr>

        <tr>
            <td>Login</td>
            <td><input type="text" name="login" placeholder="login" pattern="^\w+$"
                       title="First name should contain letters + numbers, or just letters. e.g. Jonh1"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" placeholder="password" pattern="^\w+$"
                       title="Password should contain letters or numbers. e.g. hsdw234D"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<c:import url="/pages/fragments/footer.jsp"/>

</body>
</html>
