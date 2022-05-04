<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

</head>
<body>
<c:import url="/pages/fragments/header.jsp"/>
<c:import url="/pages/fragments/menu.jsp"/>

<h3>Product List</h3>
<c:if test="${userType == 'ADMIN'}">
    <a href="${pageContext.request.contextPath}/product/create">Create Product</a>
</c:if>

<table class="table table-bordered">
    <thead align="center">
    <tr>
        <th scope="col">№</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Description</th>
        <th scope="col">Image</th>
        <c:choose>
            <c:when test="${sessionScope.userType == 'ADMIN'}">
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </c:when>
            <c:otherwise>
                <th scope="col">Buy</th>
            </c:otherwise>
        </c:choose>
    </tr>
    </thead>

    <c:forEach items="${productList}" var="product">
        <tr>
            <td align="center">${product.id}</td>
            <td align="center">${product.name}</td>
            <td align="center">${product.price}$</td>
            <td align="center">${product.number}</td>
            <td align="center">${product.description}</td>
            <td align="center"><img src="${pageContext.request.contextPath}/images/${product.id}.jpg"></td>
            <c:choose>
                <c:when test="${sessionScope.userType == 'ADMIN'}">
                    <td>
                        <a href="${pageContext.request.contextPath}/product/edit?id=${product.id}">Edit</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/product/delete?id=${product.id}">Delete</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <a href="${pageContext.request.contextPath}/product/buy?id=${product.id}">Buy</a>
                    </td>
                </c:otherwise>
            </c:choose>
         </tr>
    </c:forEach>
</table>

<c:import url="/pages/fragments/footer.jsp"/>

</body>
</html>