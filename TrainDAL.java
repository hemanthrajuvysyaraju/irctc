package com.pennant.irctc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUTILITIES.JdbcUtil;

public class TrainDAL {
	private TrainListModel trains = new TrainListModel();
	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;

	public TrainListModel getTrains(FromToStationModel data) {
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement(
					"select * from i213_trains where train_no in(select fos.train_no from i213_trainstations fos,i213_trainstations tos where fos.station_id=? and tos.station_id=? and tos.train_no=fos.train_no and fos.trst_index<tos.trst_index)");
			psmt.setString(1, data.getFrom());
			psmt.setString(2, data.getTo());
			rs = psmt.executeQuery();
			while (rs.next()) {
				TrainModel train = new TrainModel();
				train.setTrain_no(rs.getString("train_no"));
				train.setTrain_name(rs.getString("train_name"));
				trains.add(train);
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trains;
	}
}
