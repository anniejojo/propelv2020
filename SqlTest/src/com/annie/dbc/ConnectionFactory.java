package com.annie.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionFactory {
	//Declaring the connection
		static Connection connection=null;
		
		public static Connection getconnection()throws Exception
		{   //try catch block
			try
			{    //loading the connection
				Properties prop=loadPropertiesFile();
				//storing properties to variables 
				String driverClass=prop.getProperty("ORACLEJDBC.JDBC_DRIVER");
				System.out.println(driverClass);
				String url=prop.getProperty("ORACLEJDBC.DB_URL");
				System.out.println(url);
				String username=prop.getProperty("ORACLEJDBC.USER");
				System.out.println(username);
				String password=prop.getProperty("ORACLEJDBC.PASS");
				System.out.println(password);
				//pass driverclass to all classes
				Class.forName(driverClass);
				return DriverManager.getConnection(url,username,password);
				
			}catch(SQLException e)
			{
				throw new RuntimeException("Error in connection"+e);
			}
			
		}
		
		 private static Properties loadPropertiesFile()throws Exception{
			//creating object for properties
				Properties prop=new Properties();
				prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
				return prop;
			
		}

		public static void main(String[] args) throws Exception {
			connection=ConnectionFactory.getconnection();
			//to display the connection
			System.out.println(connection);
			//to display the connected to the database
			System.out.println("connected to database");
			

		}


}
