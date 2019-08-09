package com.bdd.framework.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtility {
	
	public  static void OracleConnection(){  
		try{  
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:xe","system","Password01"); 
	
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement(); 
		
		System.out.println("Connected successfully.......");
		//step4 execute query  
		ResultSet rs=stmt.executeQuery("select * from emp");  
		while(rs.next())  
			System.out.println("Connected successfully.......");
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		  
		//step5 close the connection object  
		con.close();  
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		}  
	
	public static void MySqlconnection()
	{
		
		try
		{  
			
			// 1) Create a connection
			
			Class.forName("com.mysql.jdbc.Driver");  
			
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/classicmodels","root","Password01"); 
			
			
			// 2) Create statement/Query
	          Statement stmt = con.createStatement();
	          
	          //3.Prepare Query
	          String s = "select * from customerinfo  limit 1";
	          
	          
	       // 3,4) Execute statement/Query & Store data in resultset
	          ResultSet rs = stmt.executeQuery(s);
	          
	          
	          while (rs.next()) {
	              String bookname = rs.getString("BookName");
	              String purchasedate = rs.getString("PurchasedDate");
	              int amount = rs.getInt("Amount");
	              String location = rs.getString("Location");
	              System.out.println(bookname + "   " + purchasedate + "      " + amount+"    "+location);
	          }

	          // 5) close connection
	          con.close();

	          System.out.println("Query executed.....");
	          
	          
		}
		catch(Exception E) 
		{
			
		}
	}
			


public static void main(String args[])
{
	MySqlconnection();
	OracleConnection();
}
}