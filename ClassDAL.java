package com.pennant.irctc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUTILITIES.JdbcUtil;

public class ClassDAL {
	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;

	public ClassListModel getclasses() {
		con = JdbcUtil.getConnection();
		ClassListModel classes = new ClassListModel();
		try {
			psmt = con.prepareStatement("select * from i213_trainclass");
			rs = psmt.executeQuery();
			while (rs.next()) {
				ClassModel clsmdl= new ClassModel();
				clsmdl.setIndex(rs.getInt(1));
				clsmdl.setClass_Name(rs.getString(2));
				classes.add(clsmdl);
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
}
