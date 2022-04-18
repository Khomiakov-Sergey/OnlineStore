<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>

<div style="padding: 5px;">
    <a href="${pageContext.request.contextPath}/productList">Product List</a>
    <a href="${pageContext.request.contextPath}/userList">User List</a>

</div>

<h3>User List</h3>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>First name</th>
        <th>Second name</th>
        <th>age</th>
    </tr>
    <c:forEach items="${userList}" var="user" >
        <tr>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>${user.age}</td>

        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/user/create" >Create User</a>



</body>
</html>