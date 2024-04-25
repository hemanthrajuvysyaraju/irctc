package com.pennant.irctc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUTILITIES.JdbcUtil;

public class TicketDAL {
	private static Connection con;
	private static PreparedStatement psmt;
	private static ResultSet rs;

	public static boolean bookticket(TicketModel ticket)
	{
		boolean status = false;
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement("insert into i213_tickets values(?,?,?,?,?,?,?,?,?)");
			psmt.setInt(1, ticket.getTicket_No());
			psmt.setInt(2, ticket.getPnr_No());
			psmt.setDate(3, ticket.getDoj());
			psmt.setString(4, ticket.getFrom());
			psmt.setString(5, ticket.getTo());
			psmt.setString(6, ticket.getClass_Index().toString());
			psmt.setDouble(7, ticket.getTotal_Fare());
			psmt.setInt(8, ticket.getPassenger_count());
			psmt.setInt(9, ticket.getTrain_No());
			psmt.execute();
			status=true;
			JdbcUtil.closeConnections(con, psmt, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	public static int get_Last_Ticket_No()
	{
		Integer ticket_No=0;
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement("select max(ticket_no) from i213_tickets");
			rs = psmt.executeQuery();
			if(rs.next())
				ticket_No=rs.getInt(1);
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
		}
		return ticket_No;
	}
	public static TicketModel get_Ticket_By_No(Integer ticket_No)
	{
		TicketModel ticket= new TicketModel();
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement("select * from i213_tickets where ticket_no=?");
			psmt.setInt(1,ticket_No);
			rs = psmt.executeQuery();
			if(rs.next())
			{
				ticket.setTicket_No(rs.getInt(1));
				ticket.setPnr_No(rs.getInt(2));
				ticket.setDoj(rs.getDate(3));
				ticket.setFrom(rs.getString(4));
				ticket.setTo(rs.getString(5));
				ticket.setClass_Index(Integer.parseInt(rs.getString(6)));
				ticket.setTotal_Fare(rs.getDouble(7));
				ticket.setPassenger_count(rs.getInt(8));
				ticket.setTrain_No(rs.getInt(9));
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
		}
		
		return ticket;
	}
}
