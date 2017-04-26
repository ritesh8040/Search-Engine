package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionPool{
	static 	Connection con ;
	static 	Statement stmt;
	static{
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		try {
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "system");
		stmt=con.createStatement();
		} catch (SQLException e) {
		e.printStackTrace();
		}

		}

}
