package com;

import java.sql.*; 
public class Customer
{ //A common method to connect to the DB

private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName,username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customerdb", "root", "1234"); 
 } 
    catch (Exception e) 
    {e.printStackTrace();} 
 
    return con; 
 } 


public String insertCustomer (String customerName, String customerAddress, String customerPhoneNo, String customeremail) 
{ 
  String output = ""; 

try
{ 
	 
   Connection con = connect(); 
   
   if (con == null) 
   {return "Error while connecting to the database for inserting."; } 

   // create a prepared statement
   String query = " insert into items values (?, ?, ?, ?, ?)"; 

   PreparedStatement preparedStmt = con.prepareStatement(query); 

   // binding values
   preparedStmt.setInt(1, 0); 
   preparedStmt.setString(2, customerName); 
   preparedStmt.setString(3, customerAddress); 
   preparedStmt.setString(4, customerPhoneNo); 
   preparedStmt.setString(5, customeremail); 

   // execute the statement
   preparedStmt.execute(); 
   con.close(); 
    String newCustomer = readCustomer();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newCustomer + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

public String readCustomer() 
{ 
   String output = ""; 
   
   try
  { 
     Connection con = connect(); 
 
     if (con == null) 
     {return "Error while connecting to the database for reading."; } 
 
     // Prepare the html table to be displayed
     output = "<table border='1'><tr><th>Customer Name</th><th>Customer Address</th>" +
              "<th>Customer Phone No</th>" + 
              "<th>Customer email</th>" +
              "<th>Update</th><th>Remove</th></tr>"; 
 
   String query = "select * from Customer"; 
   Statement stmt = con.createStatement(); 
   ResultSet rs = stmt.executeQuery(query); 
 
   // iterate through the rows in the result set
  while (rs.next()) 
 { 
      String customerID = Integer.toString(rs.getInt("customerID")); 
      String customerName = rs.getString("customerName"); 
      String customerAddress = rs.getString("customerAddress"); 
      String customerPhoneNo = rs.getString("customerPhoneNo"); 
      String customeremail = rs.getString("customeremail"); 
 
   // Add a row into the html table
		 output += "<tr><td><input id='hidCustomerIDUpdate' name='hidCustomerIDUpdate' type='hidden' value='" + customerID + "'>"
				 + customerName + "</td>";
		 output += "<td>" + customerAddress + "</td>"; 
		 output += "<td>" + customerPhoneNo + "</td>"; 
		 
		 output += "<td>" + customeremail + "</td>";
		
		 // buttons
		 output += "<td><input name='btnUpdate' " 
		 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-customerid='" + customerID + "'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' " 
		 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-customerid='" + customerID + "'>"
		 + "<input name='hidCustomerIDDelete' type='hidden' " 
		 + " value='" + customerID + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}

public String updateCustomer(String ID, String customerName, String customerAddress, String customerPhoneNo, String customeremail)
 { 
   
	String output = ""; 
 
	try
   { 
      Connection con = connect(); 
 
      if (con == null) 
      {return "Error while connecting to the database for updating."; } 
 
     // create a prepared statement
     String query = "UPDATE customer SET customerName=?,customerAddress=?,customerPhoneNo=?,customeremail=? WHERE customerID=?"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
 
 
     // binding values
     preparedStmt.setString(1, customerName); 
     preparedStmt.setString(2, customerAddress); 
     preparedStmt.setString(3, customerPhoneNo); 
     preparedStmt.setString(4, customeremail);
     preparedStmt.setInt(5, Integer.parseInt(ID)); 
    
    
    // execute the statement
    preparedStmt.execute(); 
    con.close();
	String newCustomer = readCustomer();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newCustomer + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the Customer.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


public String deleteCustomer(String customerID) 
 { 
 String output = ""; 
 
 try
 { 
    Connection con = connect(); 
    if (con == null) 
    {return "Error while connecting to the database for deleting."; } 
 
    // create a prepared statement
    String query = "delete from customers where customerID=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
 
    // binding values
    preparedStmt.setInt(1, Integer.parseInt(customerID)); 
 
 // execute the statement
 	 preparedStmt.execute(); 
 	 con.close(); 
 	 String newCustomer = readCustomer();
 	 output =  "{\"status\":\"success\", \"data\": \"" + 
 			 newCustomer + "\"}"; 
 	 } 

 	catch (Exception e) 
 	 { 
 		output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer.\"}";  
 	 System.err.println(e.getMessage()); 
 	 } 
 	return output; 
 		}








} 