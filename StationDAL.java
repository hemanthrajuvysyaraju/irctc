package com.pennant.irctc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUTILITIES.JdbcUtil;

public class StationDAL {

	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;

	public StationListModel getStations() {
		con = JdbcUtil.getConnection();
		StationListModel stations = new StationListModel();
		try {
			psmt = con.prepareStatement("select * from i213_stations");
			rs = psmt.executeQuery();
			while (rs.next()) {
				StationModel station = new StationModel();
				station.setStation_code(rs.getString("station_id"));
				station.setStation_name(rs.getString("station_name"));
				stations.add(station);

			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stations;
	}
}
