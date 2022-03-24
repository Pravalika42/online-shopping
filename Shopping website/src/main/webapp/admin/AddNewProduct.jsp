<%@page import="dao.ProductDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="database.MySqlConnection"%>
<%@ page import = "model.*" %>
<%@ page import="java.util.*"%>
<%@include file="AdminHeader.jsp"%>
<html>
<head>

<title>Add New Product</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>
<div style="text-align: center; font-size: 30px;">Add new product<i class='fab fa-elementor'></i></div>
<br>
<%
String msg=request.getParameter("msg");
if("done".equals(msg)){
%>
<h3 class="alert">Product Added Successfully!</h3>
<%} %>
<%
if("wrong".equals(msg)){
%>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<%} %>
<%
ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
int id = pDao.getMaxId();
%>
<form action="add-new-product" method="post">
<h3>Product ID: <%out.println(id);%></h3>
<input type="hidden" name="id" value="<%out.println(id);%>">


<div class="left-div">
<h3>Enter Name</h3>
<input class="input-style" type="text" name="name" placeholder="Enter Name" required>
<hr>
</div>

<div class="right-div">
<h3>Enter Category</h3>
<input class="input-style" type="text" name="category" placeholder="Enter Category" required>
<hr>
</div>

<div class="left-div">
<h3>Enter Price</h3>
<input class="input-style" type="number" name="price" placeholder="Enter Price" required>
<hr>
</div>

<div class="right-div">
<h3>Enter Quantity</h3>
<input class="input-style" type="number" name="quantity" placeholder="Enter Quantity" required>

<hr>
</div>

<div class="right-div">
<h3>Enter image file</h3>
<input class="input-style" type="text" name="image" placeholder="Enter image file" required>

<hr>
</div>

<button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>
</form>
</body>
<br><br><br>
</body>
</html>
 