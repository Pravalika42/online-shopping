<%@page import="dao.ProductDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="database.MySqlConnection"%>
<%@ page import = "model.*" %>
<%@ page import="java.util.*"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style>
h3
{
	
	text-align: center;
}
table {
  
  width: 100%;
}

td,th {
  border: 0.5px solid #ddd;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2;}



th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color:#ccc;
  color: black;
}

.alert
{

	text-align: center;
}
</style>
</head>
<body>
<div style="text-align: center; font-size: 30px;">All Products & Edit Products <i class='fab fa-elementor'></i></div>
<%
String msg=request.getParameter("msg");
if("done".equals(msg))
{
%>
<h3 class="alert">Product Successfully Updated!</h3>
<%} %>
<%
if("wrong".equals(msg))
{
%>
<h3 class="alert">Some thing went wrong! Try again!</h3>
<%} %>
<br>
<table>
<thead>
<tr>
<th scope="col">ID</th>
<th scope="col">Name</th>
<th scope="col">Category</th>
<th scope="col"><i class="fa fa-inr"></i> Price</th>
<th scope="col">Quantity</th>
<th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
</tr>
</thead>
<tbody>
<%
ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
List<Product> products =pDao.getAllProductsAdmin();
if(!products.isEmpty()){
	for(Product product : products ){
	   %>
<tr>
<td><%=product.getId()%></td>
<td><%=product.getName()%></td>
<td><%=product.getCategory()%></td>
<td><i class="fa fa-inr"></i><%=product.getPrice()%></td>
<td><%=product.getQuantity()%></td>
<td><a href="EditProduct.jsp?id=<%=product.getId()%>">Edit <i class='fas fa-pen-fancy'></i></a></td>
</tr>
<%
		   }
	   }
else {
	out.println("No products");
	}
	   %>
</tbody>
</table>
<br>
<br>
<br>

</body>
</html>
