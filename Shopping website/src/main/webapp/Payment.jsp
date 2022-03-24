<%@page import="dao.CartDao"%>
<%@page import="dao.UserDao"%>
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

<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Payment page</title>
<script type="text/javascript">
if(window.history.forward(1)!=null)
	window.history.forward(1);
</script>
<style>
.redtext{
color:red;
font-size: 12px;
}
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



td,th {
  border: 0.5px solid #ddd;
  padding: 8px;
}

table{
width: 100%;
}

a
{
	text-decoration: none;
}

th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  
  color: black;
}

.alert
{

	text-align: center;
}

.input-style {
  padding: 8px 20px;
  font-size: 16px;
}

.button {
  padding: 8px 20px;
  font-size: 16px;
}
</style>
</head>
<body>
<br>
<table>
<thead>

<%
int items = 0; 
int total = cDao.getCartTotalPrice(email);
%>
	

          <tr>
          <th scope="col"><a href="Cart.jsp"><i class='fas fa-arrow-circle-left'> Back</i></a></th>
            <th scope="col">Total: <i class="fa fa-inr"></i> <%out.println(total); %></th>
          </tr>
        </thead>
        <thead>
          <tr>
          
          <th scope="col">s.no</th>
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Sub Total</th>
          </tr>
        </thead>
        <tbody>
<% 
List<Cart> products =cDao.getCartProducts(email);
int sno=0;
if(!products.isEmpty()){
	for(Cart product : products ){
		
%>     
          <tr>
          <%sno=sno+1; %>
           <td><%out.println(sno); %></td>
            <td><%=product.getProductName() %></td>
            <td><%=product.getCategory() %></td>
            <td><i class="fa fa-inr"></i><%=product.getPrice()%> </td>
            <td><%=product.getQuantity() %> </td>
            <td><i class="fa fa-inr"></i> <%=product.getTotal() %></td>
            </tr>
   <%}
}
	UserDao uDao = new UserDao(MySqlConnection.getConnection());
	User user = uDao.getUserDetails(email);
	   %>
  
        </tbody>
      </table>
      
<hr style="width: 100%">
<span id = "msg1"></span>
<form action="check-out" method="post">

 <div class="left-div">
 <h3>Enter Address</h3>
<input class="input-style" type="text" name="address" value="<%=user.getAddress()%>" placeholder="Enter address" onChange="validate(this.value);" required>

<br>
      <br>
 </div>

<div class="right-div">
<h3>Enter city</h3>
<input class="input-style" type="text" name="city" value="<%=user.getCity()%>" placeholder="Enter city" onChange="validate(this.value);" required>
<br>
      <br>
</div> 

<div class="left-div">
<h3>Enter State</h3>
<input class="input-style" type="text" name="state" value="<%=user.getState()%>" placeholder="Enter state" onChange="validate(this.value);" required>
<br>
      <br>
</div>

<div class="right-div">
<h3>Enter country</h3>
<input class="input-style" type="text" name="country" value="<%=user.getCountry()%>" placeholder="Enter country" onChange="validate(this.value);"required>
<br>
      <br>
</div>

<hr style="width: 100%">
<div class="left-div">
<h3>Select way of Payment</h3>
<select class="input-style" name="paymentMethod">
<option value="cash on delivery(COD)">cash on delivery(COD)</option>
<option value="online payment">online payment</option>
</select>
<br>
      <br>
</div>

<div class="right-div">
<h3>Credit card Number</h3>
<input class="input-style" type="text" name="creditcardnumber" placeholder="Enter credit card number">
<br>
      <br>
</div>
<hr style="width: 100%">

<div class="left-div">
<h3>Mobile Number</h3>
<input class="input-style" type="text" name="mobilenumber" value="<%=user.getMobile()%>" placeholder="Enter mobile number" onChange="validate(this.value);"required>

</div>
<div class="right-div">
<br>
      <br>
      <br>
<button class="button" type="submit" name="submit">Proceed to generate bill & save<i class='far fa-arrow-alt-circle-right'></i></button>

</div>
</form>
<script src="payment.js"></script>


      <br>
      <br>
      <br>

</body>
</html>

