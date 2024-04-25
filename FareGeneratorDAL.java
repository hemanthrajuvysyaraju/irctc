package com.pennant.irctc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUTILITIES.JdbcUtil;

public class FareGeneratorDAL {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public static Double getbasefare(Integer class_Index, String from_Station, String to_Station) {
		con = JdbcUtil.getConnection();
		Double base_Fare=null;
		try {
			pstmt = con.prepareStatement("select i213_getfare(?,?,?)");
			pstmt.setInt(1, class_Index);
			pstmt.setString(2, from_Station);
			pstmt.setString(3, to_Station);
			rs = pstmt.executeQuery();
			if(rs.next())
				base_Fare=Double.valueOf(rs.getDouble(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return base_Fare;
	}
}
