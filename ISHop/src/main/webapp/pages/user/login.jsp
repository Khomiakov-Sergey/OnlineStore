<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

</head>
<body>

<c:import url="/pages/fragments/header.jsp"/>
<c:import url="/pages/fragments/menu.jsp"/>

</div>

<h3>Login Page</h3>
<p style="color: red;">${error}</p>


<form method="POST" action="${pageContext.request.contextPath}/user/login">
    <table border="0">
        <tr>
            <td>User Login</td>
            <td><input type="text" name="login" value="${user.login}" pattern="^\w+$"
                       title="First name should contain letters + numbers, or just letters. e.g. Jonh1"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="${user.password}" pattern="^\w+$"
                       title="Password should contain letters or numbers. e.g. hsdw234D"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>

    </table>
    <br/>
    If you don`t have account, click on the link
    <a href="${pageContext.request.contextPath}/user/registration">Create User</a>
</form>

<c:import url="/pages/fragments/footer.jsp"/>

</body>
</html>