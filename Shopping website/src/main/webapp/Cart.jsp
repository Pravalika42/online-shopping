<%@page import="dao.CartDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="database.MySqlConnection"%>
<%@ page import = "model.*" %>
<%@ page import="java.util.*"%>
<%
String email = session.getAttribute("email").toString();
CartDao cDao = new CartDao(MySqlConnection.getConnection());
%>

<!DOCTYPE html>
<html>
<head>
	<title>Shopping Cart</title>
	<link rel="stylesheet" type="text/css" href="StyleCart.css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,900" rel="stylesheet">
</head>
<body>





	<div class="navbar">
		<div class="logo">
			<img src="images\shoe.png" width="125px">
		</div>
		<nav>
			<ul>
				<li><a href="UserHome.jsp">Home</a></li>
				<li><a href="MyOrders.jsp">My orders</a></li>
				<li><a href="logout.jsp">Logout</a></li>				
			</ul>
		</nav>
	</div>
	
	<%
String msg=request.getParameter("msg");
if("notpossible".equals(msg)){
	%>
	<h3 class="alert">There is only one Quantity! So click on remove!</h3>
	<% 
}
%>
<%
if("inc".equals(msg)){
	%>
	<h3 class="alert">Quantity  Increased Successfully!</h3>
	<% 
}
%>
<%
if("dec".equals(msg)){
	%>
	<h3 class="alert">Quantity  Decreased Successfully!</h3>
	<% 
}
%>
<%
if("removed".equals(msg)){
	%>
	<h3 class="alert">Product Successfully Removed!</h3>
	<% 
}
%>



   
   <div class="CartContainer">
   	   <div class="Header">
   	   	<h3 class="Heading">Shopping Cart</h3>
   	   	
   	   </div>
   	   <%
   	    int items = 0; 
   	    int total = cDao.getCartTotalPrice(email);
		List<Cart> products =cDao.getCartProducts(email);
		if(!products.isEmpty()){
			for(Cart product : products ){
				items = items + product.getQuantity() ;
		%>

   	   <div class="Cart-Items">
   	   	 
   	   	  <div class="about">
   	   	  	<p class="title"><%=product.getProductName() %></p>
   	   	  	<p class="category">category : <%=product.getCategory() %></p>
   	   	  	<p class="product-id">product id : <%=product.getProductId() %></p>
   	   	  	
   	   	  </div>
   	   	  <div class="counter">
   	   	  	<div class="btn"><a href="quantity-inc-dec?id=<%=product.getProductId() %>&quantity=inc">+</a></div>
   	   	  	<div class="count"><%=product.getQuantity() %></div>
   	   	  	<div class="btn"><a href="quantity-inc-dec?id=<%=product.getProductId() %>&quantity=dec">-</a></div>
   	   	  </div>
   	   	  <div class="prices">
   	   	  	<div class="amount">Price: <%=product.getPrice() %></div>
   	   	  	<div class="remove"><a href="remove-from-cart?id=<%=product.getProductId() %>"><u>Remove</u></a></div>
   	   	  </div>
   	   </div>

   	   <%
   }
   
   	   %>
   	   
   	 <hr> 
   	 <div class="checkout">
   	 <div class="total">
   	 	<div>
   	 		<div class="Subtotal">Sub-Total</div>
   	 		<div class="items"> <%=items%>items</div>
   	 	</div>
   	 	<div class="total-amount"><%out.println(total); %></div>
   	 </div>
   	 <%if(total>0){%>
   	 <a href="Payment.jsp"><button class="button">Checkout</button></a></div>
   	 </div>
   	 <% }%>
   	<%
		} else {%>
		<div style ="text-align:center;">
		<% 
		out.println("Cart is empty");
		}
		%>
		<br>
		<br>
		</div>
   
</body>
</html>