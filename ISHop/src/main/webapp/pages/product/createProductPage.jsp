<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
</head>
<body>

<div style="padding: 5px;">
    <a href="${pageContext.request.contextPath}/productList">Product List</a>
    <a href="${pageContext.request.contextPath}/userList">User List</a>

</div>

<h3>Create Product</h3>

<form method="POST" action="${pageContext.request.contextPath}/product/create">
    <table border="0">
               <tr>
            <td>Name</td>
            <td><input type="text" name="name" placeholder="name"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" placeholder="price" /></td>
        </tr>
        <tr>
            <td>Number</td>
            <td><input type="text" name="number" placeholder="number" /></td>
        </tr>

        <tr>
            <td>Description</td>
            <td><input type="text" name="description" placeholder="description" /></td>
        </tr>



        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="${pageContext.request.contextPath}/productList">Cancel</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>