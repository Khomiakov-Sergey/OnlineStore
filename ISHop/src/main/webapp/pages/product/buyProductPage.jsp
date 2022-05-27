<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buy Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

</head>
<body>

<c:import url="/pages/fragments/header.jsp"/>
<c:import url="/pages/fragments/menu.jsp"/>

<h3>Buy Product</h3>

<form method="POST" action="${pageContext.request.contextPath}/product/buy">
    <input type="hidden" name="id" value="${product.id}"/>
    <input type="hidden" name="category_id" value="${product.categoryId}"/>
    <input type="hidden" name="name" value="${product.name}"/>
    <input type="hidden" name="price" value="${product.price}"/>
    <input type="hidden" name="description" value="${product.description}"/>
    <input type="hidden" name="number" value="${product.number}"/>
    <table class="table table-bordered">
        <thead align="center">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Price for one</th>
            <th scope="col">Description</th>
            <th scope="col">Image</th>
            <th scope="col">Choose quantity</th>
        </tr>
        </thead>
        <tr>
            <td align="center">${product.name}</td>
            <td align="center">${product.price}$</td>
            <td align="center">${product.description}</td>
            <td align="center"><img src="${pageContext.request.contextPath}/images/${product.id}.jpg"></td>
            <td><input type="text" name="quantity" placeholder="quantity" pattern="^[0-9]+$"
                       title="Quantity should only contain integers. e.g. 8"/></td>
        </tr>
    </table>
    <br/>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
            <a href="${pageContext.request.contextPath}/product/productList">Cancel</a>
        </td>
    </tr>
</form>

<c:import url="/pages/fragments/footer.jsp"/>

</body>
</html>
