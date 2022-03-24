<%@page import="dao.ProductDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="database.MySqlConnection"%>
<%@ page import = "model.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Foot wear store</title>
<link rel="stylesheet" href="Style.css">
</head>
<body>
	<%
	String msg = request.getParameter("msg");
	if ("added".equals(msg)) {
	%>
	<h3 class="alert">Product added successfully.</h3>

	<%
	}
	%>
	<%
	if ("exist".equals(msg)) {
	%>
	<h3 class="alert">Product already exist in the cart.go to cart</h3>

	<%
	}
	%>

	<%
	if ("invalid".equals(msg)) {
	%>
	<h3 class="alert">Something went wrong.Try again.</h3>

	<%
	}
	%>
<% 
String email = request.getSession().getAttribute("email").toString();
ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
List<String> categories = pDao.getAllCategories();

%>

	<div>
		<div class="navbar">
			
			<div class="logo">
				<img src="images/shoe.png" width="125px">
			</div>
			<nav>
				<ul>
					<li><a href="MyCart.jsp">Cart</a></li>
					
					<li>
						<%
						out.println(email);
						%>
					</li>
					<li><a href="logout.jsp">Logout</a></li>
				</ul>
			</nav>
			
		</div>
		<div class="row">
			<div class="col">
				<h1>Craft your own footprints.</h1>
				<br>
				<br>
			</div>
			<div>
				<img src="images/home.jpg" alt="">
			</div>
		</div>
	</div>
	<!-------catagories--------->

	<div class="catagories">
	
		<h2>Categories</h2>
		<br>
		<br>
		<%for(String category : categories) 
		{%>
		
		<ul>
		<li><a href="Categories.jsp?id=<%=category %>"><% out.println(category);%></a></li>
			<hr>
	
			</ul>
			<%} %>
		<div class="smallcontainer">
			<div class="row">
				<div class="col-c">
					<a href="#"> <img src="images/men.png" alt="">
						<p>formal wear</p>
					</a>

				</div>

				<div class="col-c">
					<a href="#"> <img src="images/kids.png" alt="">
						<p>casual wear</p>
					</a>
				</div>
				<div class="col-c">
					<a href="#"> <img src="images/sports.png" alt="">
						<p>sports wear</p>
					</a>
				</div>

			</div>
		</div>

	</div>
	<!-------popular products--------->
	<div class="pop-products">
		<h2>All products</h2>
		<br>
		<br>
		<%
		List<Product> products =pDao.getAllProducts();
		if(!products.isEmpty()){
			for(Product product : products ){
		%>
		<div class="box">
			<div class="image">
				<img src="images/<%=product.getImage() %>" alt="">
				<div class="icons">
					<a href="#" class="fas fa-heart"></a> <a
						href="add-to-cart?id=<%=product.getId()%>"
						class="cart-btn">add to cart</a> <a href="#" class="fas fa-share"></a>
				</div>

			</div>
			<div class="content">
				<h3><%=product.getName()%></h3>
				<div class="price">
					price: Rs-<%=product.getPrice()%>
				</div>
				<div class="catagory">
					category:<%=product.getCategory()%></div>
				<div class="product-id">
					product id:
					<%=product.getId()%></div>

			</div>
		</div>
		<%
		}
		} else {
		out.println("No products");
		}
		%>


	</div>

</body>
</html>