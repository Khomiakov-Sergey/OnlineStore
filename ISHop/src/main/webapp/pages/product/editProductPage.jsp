<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>

<c:import url="/pages/fragments/header.jsp"/>
<c:import url="/pages/fragments/menu.jsp"/>

<h3>Edit Product</h3>

<form method="POST" action="${pageContext.request.contextPath}/product/edit">
    <table border="0">
        <tr>
            <td>ID</td>
            <td><input type="text" name="id" value="${product.id}"/></td>
            <td style="color:red;">${product.id}</td>
        </tr>

        <tr>
            <td>Category</td>
            <td>
                <select id="categoryId" name="categoryId" required="required">
                    <option value="1">Iphone</option>
                    <option value="2">Mac</option>
                    <option value="3">Ipad</option>
                    <option value="4">Watch</option>
                </select>
            <td>
        </tr>

        <tr>
            <td>Name</td>
            <td><input type="text" name="name" placeholder="name"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" placeholder="price"/></td>
        </tr>
        <tr>
            <td>Number</td>
            <td><input type="text" name="number" placeholder="number"/></td>
        </tr>

        <tr>
            <td>Description</td>
            <td><input type="text" name="description" placeholder="description"/></td>
        </tr>


        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/product/productList">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<c:import url="/pages/fragments/footer.jsp"/>

</body>
</html>