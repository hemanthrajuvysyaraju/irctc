package com.pennant.irctc.ticketbooking;

import jakarta.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class AllStationServlet
 */
@WebServlet(urlPatterns = {"/test"})
public class AllStationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllStationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization") ;
		response.addHeader("Access-Control-Allow-Origin", "*");
		Connection con;
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		try {
			con = JdbcUtilities.getConnection();
			PreparedStatement st=con.prepareStatement("select * from i213_stations");
			ResultSet rs=st.executeQuery();
			String stcode,stname;
			JSONObject obj=new JSONObject();
			JSONArray codearr=new JSONArray();
			JSONArray namearr=new JSONArray();
			while(rs.next())
			{
				stcode=rs.getString("station_id");
				stname=rs.getString("station_name");
				codearr.put(stcode.trim());
				namearr.put(stname);
			}
			obj.put("st_id",codearr);
			obj.put("st_name",namearr);
			System.out.println(obj);
			out.println(obj);
			out.close();
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
