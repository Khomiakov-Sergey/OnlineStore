<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>

<c:import url="/pages/fragments/header.jsp"/>
<c:import url="/pages/fragments/menu.jsp"/>

<h3>Create Product</h3>

<form method="POST" action="${pageContext.request.contextPath}/product/create" accept-charset="UTF-8">
    <table border="0">
        <tr>
            <td>Category</td>
            <td>
                <select id="categoryType" name="categoryType" required="required">
                    <option value="IPHONE">Iphone</option>
                    <option value="MAC">Mac</option>
                    <option value="IPAD">Ipad</option>
                    <option value="WATCH">Watch</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" placeholder="name" pattern="^\w.+"
                       title="Name should contain some symbols. e.g. Iphone 10 Pro"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" placeholder="price" pattern="\d+(\.\d{2})?"
                       title="Price should contain integers or double value. e.g. 222.10"/></td>
        </tr>
        <tr>
            <td>Number</td>
            <td><input type="text" name="number" placeholder="number" pattern="^[0-9]+$"
                       title="Number should only contain integers. e.g. 8"/></td>
        </tr>

        <tr>
            <td>Description</td>
            <td><input type="text" name="description" placeholder="description" pattern="^\w.*"
                       title="Description should contain any symbols."/></td>
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