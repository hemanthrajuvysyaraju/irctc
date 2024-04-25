package com.pennant.irctc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBCUTILITIES.JdbcUtil;

public class AddPassengersDAL {
	private static boolean status = false;
	private static Connection con;
	private static PreparedStatement psmt;

	public static boolean addpassengers(PassengerListModel psngrs) {
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement("insert into i213_passengers values(?,?,?,?,?)");
			psngrs.forEach((psngr) -> {
				try {
					psmt.setInt(1, psngr.getTicket_No());
					psmt.setString(2, psngr.getName());
					psmt.setInt(3, psngr.getAge());
					psmt.setString(4, psngr.getGender());
					psmt.setString(5, psngr.getPreference());
					psmt.addBatch();
				} catch (SQLException e) {
				}
			});
			int arr[] = psmt.executeBatch();
			if (arr.length > 0)
				status = true;
			JdbcUtil.closeConnections(con, psmt, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}
}
