<%@page import= "com.Customer" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PAF Practical</title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Component/jQuery-3.2.1.min.js"></script>
<script src="Component/items.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Customer Management </h1>
<form id="formCustomer" name="formCustomer" method="post" action="customer.jsp">
 Customer Name: 
<input id="customerName" name="customerName" type="text" 
 class="form-control form-control-sm">
<br> Customer Address: 
<input id="customerAddress" name="customerAddress" type="text" 
 class="form-control form-control-sm">
<br> Customer PhoneNo: 
<input id="customerPhoneNo" name="customerPhoneNo" type="text" 
 class="form-control form-control-sm">
<br> Customer email: 
<input id="customeremail" name="customeremail" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divCustomersGrid">

<%
Customer customerObj = new Customer(); 
 out.print(customerObj.readCustomer()); 
%>
</div>

</div></div></div>

</body>
</html>