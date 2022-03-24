<%@page import="dao.ProductDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="database.MySqlConnection"%>
<%@ page import = "model.*" %>
<%@ page import="java.util.*"%>
<%@include file="AdminHeader.jsp"%>


<html>
<head>
<link rel="stylesheet" href="../css/addNewProduct-style.css">
<title>Add New Product</title>
<style>
.back
{
  color: white;
  margin-left: 2.5%
}
</style>
</head>
<body>
<h2><a class="back" href="EditAllProduct.jsp"><i class='fas fa-arrow-circle-left'> Back</i></a></h2>
<%  
int id=Integer.parseInt(request.getParameter("id"));
ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
Product product = pDao.getSingleProduct(id);

%>
<form action="edit-product" method="post">
<input type="hidden" name="id" value="<%out.println(id);%>">
<div class="left-div">
<h3>Enter Name</h3>
<input class="input-style" type="text" name="name" value="<%=product.getName()%>" required>
<hr>
</div>

<div class="right-div">
<h3>Enter Category</h3>
<input class="input-style" type="text" name="category" value="<%=product.getCategory()%>" required>

<hr>
</div>

<div class="left-div">
<h3>Enter Price</h3>
<input class="input-style" type="number" name="price" value="<%=product.getPrice()%>" required>
<hr>
</div>

<div class="right-div">
<h3>Enter Quantity</h3>
<input class="input-style" type="number" name="quantity" value="<%=product.getQuantity()%>" required>
<hr>
</div>
<button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>
</form>


</body>
<br><br><br>
</body>
</html>
