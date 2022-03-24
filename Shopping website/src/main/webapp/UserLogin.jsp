<%@ page import = "java.sql.*" %>
<%@ page import = "database.MySqlConnection" %>
<%@ page import = "model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login-Foot wear store</title>
<link rel="stylesheet" href="Style.css">
<style type="text/css">
body{
background: url(images/login5.jpg);
background-size: cover;
height:100%;
}
.redtext{
color:red;
font-size: 12px;
}
</style>

</head>
<body>


	<div class="navbar">
		<div class="logo">
			<img src="images\shoe.png" width="125px">
		</div>
		<nav>
			<ul>
				
				<li><a href="UserRegister.jsp">Register</a></li>
				
			</ul>
		</nav>
	</div>
	<h2 style="text-align: center">FOOTWEAR STORE</h2>
	<div class="container">
		<form action="user-login" method="post">
		
			<h1>Login</h1>
			<label for="email">Email</label> 
			<input type="text" id="email" name="email" title = "enter valid email" required> 
			<span id = "msg"></span>
			<br>
			<br>
			<label for="psw">Password</label>
			<input type="password" id="psw" name="psw" required> 
			<input id = "submit" type="submit" value="Login">
		</form>
	</div>
	<div class="whysignlogin">
		<%
    String msg=request.getParameter("msg");
    if("notexist".equals(msg)){
    	%>
		<h1>Incorrect Username and password.</h1>
		<% }
    %>
		<%if("invalid".equals(msg)){
    	%>
		<h1>Something went wrong.</h1>
		<%} %>

	</div>
	<script type="text/javascript" src = "UserLogin.js"></script>
</body>
</html>