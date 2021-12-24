<%--
  Created by IntelliJ IDEA.
  User: kinga
  Date: 12/24/2021
  Time: 8:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.javaservletassignment.entity.Food" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    Food food = (Food) request.getAttribute("food");
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    if (food == null) {
        food = new Food();
    }
    if (errors == null) {
        errors = new HashMap<>();
    }%>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/admin/include/header.jsp">
        <jsp:param name="title" value="Food form"/>
        <jsp:param name="description" value="Admin area"/>
        <jsp:param name="keywords" value="admin, page...."/>
    </jsp:include>
</head>
<body>

<div class="wrapper d-flex align-items-stretch">
    <jsp:include page="/admin/include/left-menu.jsp"/>

    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5">

        <jsp:include page="/admin/include/navbar.jsp"/>

        <form class="form-horizontal" action="/admin/food/create" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Name:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="category">Category:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="category" placeholder="Enter category" name="category">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="description">Description:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="description" placeholder="Enter description" name="description">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="thumbnail">Description:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="thumbnail" placeholder="Enter thumbnail" name="thumbnail">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="price">Price:</label>
                <div class="col-sm-4">
                    <input type="number" class="form-control" id="price" placeholder="Enter price" name="price">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="sellDate">Sell Date:</label>
                <div class="col-sm-4">
                    <input type="date" class="form-control" id="sellDate" placeholder="Enter Sell Date" name="sellDate">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="editDate">Edit Date:</label>
                <div class="col-sm-4">
                    <input type="date" class="form-control" id="editDate" placeholder="Enter Edit Date" name="editDate">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="status">Edit Date:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="status" placeholder="Enter Status" name="status">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </form>

    </div>
</div>

<jsp:include page="/admin/include/scripts.jsp"/>

</body>
</html>
