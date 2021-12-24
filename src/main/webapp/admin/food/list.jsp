<%--
  Created by IntelliJ IDEA.
  User: kinga
  Date: 12/24/2021
  Time: 8:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.javaservletassignment.entity.Food" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    ArrayList<Food> list = (ArrayList<Food>) request.getAttribute("list");
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
%>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/admin/include/header.jsp">
        <jsp:param name="title" value="My admin page"/>
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

        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Description</th>
                <th scope="col">Thumbnail</th>
                <th scope="col">Price</th>
                <th scope="col">Sell Date</th>
                <th scope="col">Edit Date</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (int i = 0; i < list.size(); i++) {
            %>
            <tr>
                <th scope="row"><%=list.get(i).getId()%></th>
                <td><%=list.get(i).getName()%></td>
                <td><%=list.get(i).getCategory()%></td>
                <td><%=list.get(i).getDescription()%></td>
                <td><img src="<%=list.get(i).getThumbnail()%>" alt="<%=list.get(i).getName()%>" width="100" /></td>
                <td><%=list.get(i).getPrice()%></td>
                <td><%=formatter.format(list.get(i).getSellDate())%></td>
                <td><%=formatter.format(list.get(i).getEditDate())%></td>
                <td><%=list.get(i).getStatus()%></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/admin/include/scripts.jsp"/>

</body>
</html>
