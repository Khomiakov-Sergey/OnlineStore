<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>
<body>

<div style="padding: 5px;">
    <a href="${pageContext.request.contextPath}/productList">Product List</a>
    <a href="${pageContext.request.contextPath}/userList">User List</a>
</div>

<h3>Product List</h3>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>

    </tr>
    <c:forEach items="${productList}" var="product" >
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.number}</td>

           <%-- <td>
                <a href="editProduct?code=${product.id}">Edit</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/product/delete?id=${product.id}">Delete</a>
            </td>--%>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/product/create" >Create Product</a>



</body>
</html>