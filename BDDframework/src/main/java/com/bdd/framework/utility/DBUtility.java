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
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/TestDB","root","Password01");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			System.out.println("Connected successfully.......");
			ResultSet rs=stmt.executeQuery("select * from emp");  
			while(rs.next())  
				System.out.println("Connected successfully.......");
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}  


public static void main(String args[])
{
	MySqlconnection();
	OracleConnection();
}
}