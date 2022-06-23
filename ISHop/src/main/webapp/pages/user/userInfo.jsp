<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

</head>
<body>

<c:import url="/pages/fragments/header.jsp"/>
<c:import url="/pages/fragments/menu.jsp"/>

<h3>Hello: ${user.login}</h3>

User First Name: <b>${user.firstName}</b><br/>
User Second Name: <b>${user.secondName}</b><br/>
User Type: <b>${user.userType}</b><br/>
Age: ${user.age } <br/>

<tr>
    <td colspan="2">
        <a href="${pageContext.request.contextPath}/user/logout">Logout</a>
    </td>
</tr>
<c:choose>
    <c:when test="${sessionScope.userType == 'USER'}">
        <h3 align="center">Your order list</h3>
        <table class="table table-bordered">
            <thead align="center">
            <tr>
                <th scope="col">№</th>
                <th scope="col">Product</th>
                <th scope="col">Total cost</th>
                <th scope="col">Quantity</th>
                <th scope="col">Date</th>
                <th scope="col">Image</th>
            </tr>
            </thead>
            <c:forEach items="${orderList}" var="order">
                <c:set var="product" value='${order.product}'/>
                <tr>
                    <td align="center">${order.id}</td>
                    <td align="center">${product.name}</td>
                    <td align="center">${order.totalCost}</td>
                    <td align="center">${order.orderQuantity}</td>
                    <td align="center">${order.orderTime}</td>
                    <td align="center"><img src="${pageContext.request.contextPath}/images/${product.id}.jpg"></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<c:import url="/pages/fragments/footer.jsp"/>

</body>
</html>