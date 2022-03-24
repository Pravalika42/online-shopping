<%@page errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style>
 .navbar{
    display: flex;
    align-items: center;
    padding: 20px;
    
}
nav{
    flex: 1;
    text-align: right;
}
nav ul{
    display: inline-block;
    list-style-type: none;
}
nav ul li{
    display: inline-block;
    margin-right: 20px;
} 

a{
    text-decoration: none;
    color: black;
}
</style>
</head>
    <!--Header-->
    <br>
    <div class="topnav sticky">
   <% String email=session.getAttribute("email").toString(); %>
            <center><h2>Foot wear store</h2></center>
            <div class="navbar">
		<div class="logo">
			<img src="..\images\shoe.png" width="125px">
		</div>
		<nav>
			<ul>
				
				<li><a href="AddNewProduct.jsp">Add New Product </a></li>
				<li><a href="AllProductEditProduct.jsp">All Products & Edit Products </a></li>
				<li><a href="OrderReceived.jsp">Orders Received </a></li>
				<li><a href="../logout.jsp">logout </a></li>
				
			</ul>
		</nav>
	</div>
          </div>
           <br>
           <!--table-->
           
            
            