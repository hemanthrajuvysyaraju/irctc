package com.pennant.irctc.ticketbooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class TrainServlet
 */
public class TrainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization") ;
		response.addHeader("Access-Control-Allow-Origin", "*");
		String from=request.getParameter("from");
		String to=request.getParameter("to");
		Connection con=null;
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		try {
			con = JdbcUtilities.getConnection();
			PreparedStatement st=con.prepareStatement("select * from i213_trains where train_no in(select ts.train_no from i213_trainstations ts,i213_trainstations tr where ts.station_id=? and tr.station_id=? and tr.train_no=ts.train_no)");
			st.setString(1, from);
			st.setString(2, to);
			ResultSet rs=st.executeQuery();
			String train_no,train_name;
			JSONObject train_data=new JSONObject();
			JSONArray train_no_arr=new JSONArray();
			JSONArray train_name_arr=new JSONArray();
			while(rs.next())
			{
				train_no=rs.getString("train_no");
				train_name=rs.getString("train_name");
				train_no_arr.put(train_no.trim());
				train_name_arr.put(train_name.trim());
			}
			train_data.put("train_nos",train_no_arr);
			train_data.put("train_names",train_name_arr);
			out.println(train_data);
			JdbcUtilities.closeConnections(con, rs, st);
			out.close();
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}
}
