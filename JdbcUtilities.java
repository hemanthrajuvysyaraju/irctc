package com.pennant.irctc.ticketbooking;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class JdbcUtilities {

	public static Connection getConnection() throws SQLException, IOException
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f= new File("C:\\Users\\heman\\eclipse-workspace\\servlets\\src\\main\\java\\com\\pennant\\jdbc\\servlet\\connection.properties");
		FileInputStream fis=new FileInputStream(f);
		Properties p = new Properties();
		p.load(fis);
		return DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
	}
	public static void closeConnections(Connection con,ResultSet rs,Statement st) throws SQLException
	{
		if(con!=null)
			con.close();
		if(rs!=null)
			rs.close();
		if(st!=null)
			st.close();
	}
}
