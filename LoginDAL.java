package com.pennant.irctc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUTILITIES.JdbcUtil;

public class LoginDAL {
	public boolean isvaliduser(UserModel user) {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement("select pswd from i213_irctcusers where userid=?");
			psmt.setString(1, user.getUsername());
			rs = psmt.executeQuery();
			if (rs.next()) {
				String dbpwd = rs.getString("pswd");
				if (dbpwd.equals(user.getPassword())) {
					JdbcUtil.closeConnections(con, psmt, rs);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
