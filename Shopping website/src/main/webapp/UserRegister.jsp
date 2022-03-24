<%@ page import = "java.sql.*" %>
<%@ page import = "database.MySqlConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="Style.css">
<style type="text/css">

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
				
				<li><a href="Login.jsp">Login</a></li>
				
			</ul>
		</nav>
	</div>
	<div class="container">
		<form action ="RegisterServlet" method="post">
			<h1>Register</h1>
			<label for="usrname">Username</label> 
			<input type="text" id="usrname"	name="usrname" required> 
			<span id="msg1"></span>
			<br><br>
			<label for="email">Email</label> 
			<input type="text" id="email" name="email" required> 
			<span id="msg2"></span>
			<br><br>
			<label for="psw">Password</label>
		    <input type="password" id="psw" name="psw" required> 
		    <br><br>
		    <label for="c-psw"> Confirm Password</label> 
		    <input type="password" id="c-psw" name="c-psw" onchange="check()" required>
			<span id="msg"></span> 
			<br><br>
			<input id="submit" type="submit" value="Submit" >
		</form>
	</div>
	<div class="whyregister">
		<%
		String msg = request.getParameter("msg");
		if ("valid".equals(msg)) {
		%>
		<h1>Successfully Registered, Please <a href="UserLogin.jsp" style="color:red">Login</a>.</h1>
		
		<%
		}
		%>
		<%
		if ("invalid".equals(msg)) {
		%>
		<h1>Something went wrong,User already exists.</h1>
		<%
		}
		%>
	</div>



	<script src="UserRegister.js"></script>
</body>
</html>