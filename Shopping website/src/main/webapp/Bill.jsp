<%@page import="dao.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="database.MySqlConnection"%>
<%@ page import = "model.*" %>
<%@ page import="java.util.*"%>
<html>
<head>

<title>Bill</title>
<style>
.left-div
{
	width: 50%;
	display:inline-block;
}

.right-div
{
	float:right;
	width: 50%;
	display:inline-block;
}
.title{
	text-align: center;
}
hr
{
	width: 100%
}
table {
  
  width: 100%;
}

td,th {
  border: 0.5px solid #ddd;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2;}

tr:hover {background-color: #ddd;}

th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color:#ccc;
  color: black;
}
.left-button
{
	width: 48%;
	display:inline-block;
}

.right-button
{
	float:right;
	width: 48%;
	display:inline-block;
}
.button {
  padding: 12px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin: 4px 2px;
  width: 40%;
   border-radius: 25px;
}
</style>
</head>
<body>
<%
String email = session.getAttribute("email").toString();
String transactId=request.getParameter("id");
OrderDao oDao = new OrderDao(MySqlConnection.getConnection());

	int total= oDao.getBillTotal(email,transactId);
	
	Order userDetails = oDao.getOrderUserDetails(email,transactId);
		
	
%>
<h3 class="title">Foot wear Store</h3>
<hr>
<div class="left-div"><h4>Name: <%=userDetails.getUserName() %> </h4></div>
<div class="right-div"><h4>Email:<%=userDetails.getEmail() %>  </h4></div>
<div class="left-div"><h4>Mobile Number:<%=userDetails.getMobile() %>  </h4></div>  

<div class="right-div"><h4>Order Date: <%=userDetails.getOrderDate() %> </h4></div>
<div class="left-div"><h4>Payment Method: <%=userDetails.getPaymentMethod() %> </h4></div>
<div class="right-div"><h4>Expected Delivery:<%=userDetails.getDeliveryDate() %> </h4></div> 

<div class="left-div"><h4>Order Id: <%=userDetails.getTransactId() %> </h4></div>
<div class="right-div"><h4>City: <%=userDetails.getCity() %> </h4></div> 
<div class="left-div"><h4>Address:<%=userDetails.getAddress() %>  </h4></div> 

<div class="right-div"><h4>State: <%=userDetails.getState() %> </h4></div>
<div class="left-div"><h4>Country: <%=userDetails.getCountry() %> </h4></div>  

<hr>


	
	<br>
<h3 class="title">Product Details</h3>	
<table id="customers">

  <tr>
    <th>S.No</th>
    <th>Product Name</th>
    <th>category</th>
    <th>Price</th>
    <th>Quantity</th>
     <th>Sub Total</th>
  </tr>
  <%
  int sno=0;
  try
  {
  	
  	List<Order> orders =oDao.getOrderProductDetails(email,transactId);
  	if(!orders.isEmpty()){
  		for(Order order : orders ){
  			sno=sno+1;
  
  %>
  <tr>
    <td><%out.println(sno); %></td>
    <td><%=order.getProductName() %></td>
    <td><%=order.getCategory() %></td>
    <td><%=order.getPrice() %></td>
    <td><%=order.getQuantity() %></td>
     <td><%=order.getTotal() %></td>
  </tr>
  <tr>
<%
  		}
  	}
  }
  	catch(Exception e)
  	{
  		System.out.println(e);	 
  	}%>
</table>
<h3>Total: <%out.println(total); %></h3>
<a href="continueShopping.jsp"><button class="button left-button">Continue Shopping</button></a>
<a onclick="window.print();"><button class="button right-button">Print</button></a>
<br><br><br><br>

</body>
</html>


