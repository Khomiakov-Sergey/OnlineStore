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

<h3>Buy Product</h3>

<form method="POST" action="${pageContext.request.contextPath}/product/buy">
    <input type="hidden" name="id" value="${product.id}"/>
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
            <td><input type="text" name="quantity" placeholder="quantity"/></td>
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

<div
        style="background: #E0E0E0; text-align: center; padding: 5px; margin-top: 10px;">

    @Copyright it-academy.by

</div>
</body>
</html>
