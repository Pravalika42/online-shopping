<%@page import="dao.OrderDao"%>
<%@ page import="java.sql.*"%>
<%@ page import="database.MySqlConnection"%>
<%@ page import = "model.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>my orders</title>
<style>

table {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
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
<body>


<%
String msg = request.getParameter("msg");
if("cancel".equals(msg)){
	%>
	<h3 class="alert">Order Cancel Successfully!</h3>
	<%
}%>

<% 
if("invalid".equals(msg)){
	%>
	<h3 class="alert">Some thing went wrong! Try Again!</h3>
	<%
}
%>
<div class="navbar">
		<div class="logo">
			<img src="images\shoe.png" width="125px">
		</div>
		<nav>
			<ul>
				<li><a href="UserHome.jsp">Home</a></li>
				<li><a href="MyCart.jsp">My cart</a></li>
				<li><a href="logout.jsp">Logout</a></li>				
			</ul>
		</nav>
	</div>
	<div style="text-align: center; font-size: 30px;">My Orders <i class='fab fa-elementor'></i></div>
	<br>
<table>
        <thead>
          <tr>
            <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">category</th>
            <th scope="col"><i class="fa fa-inr"></i>  Price</th>
            <th scope="col">Quantity</th>
            <th scope="col"><i class="fa fa-inr"></i> Sub Total</th>
            <th scope="col">Order Date</th>
             <th scope="col">Expected Delivery Date</th>
             <th scope="col">Payment Method</th>
             <th scope="col">Order Id</th>
              <th scope="col">Status</th>
              <th scope="col">Cancel order <i class='fas fa-window-close'></i></th>
              
          </tr>
        </thead>
        <tbody>
<%
String email = request.getSession().getAttribute("email").toString();
OrderDao oDao = new OrderDao(MySqlConnection.getConnection());
int sno=0;
try
{
	
	List<Order> orders =oDao.getOrders(email);
	if(!orders.isEmpty()){
		for(Order order : orders ){
			sno=sno+1;
%>		
          <tr>
            <td><%out.println(sno); %></td>
            <td><%=order.getProductName() %></td>
            <td><%=order.getCategory() %></td>
            <td><i class="fa fa-inr"></i><%=order.getPrice()%></td>
            <td><%=order.getQuantity() %></td>
            <td><i class="fa fa-inr"></i><%=order.getTotal() %></td>
            <td><%=order.getOrderDate()%></td>
            <td><%=order.getDeliveryDate()%></td>
            <td><%=order.getPaymentMethod()%></td>
            <td><%=order.getTransactId()%></td>
            <td><%=order.getStatus()%></td>
            <td><a href="cancel-order?id=<%=order.getTransactId()%>&email=<%=order.getEmail()%>&productid=<%=order.getProductId()%>"> Cancel<i class='fas fa-window-close'></i></a></td>
            </tr>
<%
}
}
}
 catch(Exception e)
{
	System.out.println(e);	 
}%>
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>