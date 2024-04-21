package com.pennant.irctc.ticketbooking;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import org.json.JSONObject;

/**
 * Servlet implementation class TicketBooking
 */
public class TicketBooking extends HttpServlet {
	private int ticket_no;
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ticket_no = 0;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameterNames().hasMoreElements())
			doPost(request, response);
		else
		{
			response.setContentType("text/html");
			response.getWriter().println("<h1 style='color:red'>sorry boss you need send parameters</h1>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> params=request.getParameterNames();
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String train_no = request.getParameter("trains");
		Date doj = Date.valueOf(request.getParameter("doj"));
		String class_selected = request.getParameter("class");
		ArrayList<String> name_arr = new ArrayList<>();
		ArrayList<String> gender_arr = new ArrayList<>();
		ArrayList<String> age_arr = new ArrayList<>();
		ArrayList<String> preference_arr = new ArrayList<>();
		while(params.hasMoreElements())
		{
			String param=params.nextElement();
			if(param.startsWith("name"))
			{
				name_arr.add(request.getParameter(param));
			}
			else if(param.startsWith("gender"))
			{
				gender_arr.add(request.getParameter(param));
			}
			else if(param.startsWith("age"))
			{
				age_arr.add(request.getParameter(param));
			}
			else if(param.startsWith("preference"))
			{
				preference_arr.add(request.getParameter(param));
			}
		}
		Random r = new Random();
		int pnr_no = r.nextInt(100000, 999999);
		// response headers cors error setting
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.addHeader("Access-Control-Allow-Origin", "*");
		Connection con = null;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		++ticket_no;
		try {
			con = JdbcUtilities.getConnection();
			PreparedStatement ticktst = con.prepareStatement("insert into i213_tickets values(?,?,?,?,?,?,?,?,?)");
			ticktst.setInt(1, ticket_no);
			ticktst.setInt(2, pnr_no);
			ticktst.setDate(3, doj);
			ticktst.setString(4, from);
			ticktst.setString(5, to);
			ticktst.setString(6, class_selected);
			ticktst.setInt(7, name_arr.size() * 500);
			ticktst.setInt(8, name_arr.size());
			ticktst.setInt(9, Integer.parseInt(train_no));
			ticktst.execute();
			PreparedStatement psgrst = con.prepareStatement("insert into i213_passengers values(?,?,?,?,?)");
			for (int i = 0; i < name_arr.size(); i++) {
				psgrst.setInt(1, ticket_no);
				psgrst.setString(2, name_arr.get(i));
				psgrst.setInt(3, Integer.parseInt(age_arr.get(i)));
				psgrst.setString(4, gender_arr.get(i));
				psgrst.setString(5, preference_arr.get(i));
				psgrst.addBatch();
			}
			psgrst.executeBatch();
			out.println("<body>"
					+ "<table style="+"text-align:'center'><caption>ticket details</caption>"
					+ "<thead>"
					+ "                <th>"
					+ "                    train no:"+train_no
					+ "                </th>"
					+ "                <th>"
					+ "                    pnr no:"+pnr_no
					+ "                </th>"
					+ "                <th>"
					+ "                    ticketno:"+ticket_no
					+ "                </th>"
					+ "                <th>"
					+ "                    from:"+from
					+ "                </th>"
					+ "                <th>"
					+ "                </th>"
					+ "                <th> to:"+to
					+ "</th>"
					+ "            </thead>"
					+ "            <tbody>"
					+ "                <tr>"
					+ "                    <th>"
					+ "                        name"
					+ "                    </th>"
					+ "                    <th>"
					+ "                        age"
					+ "                    </th>"
					+ "                    <th>"
					+ "                        gender"
					+ "                    </th>"
					+ "                </tr>");
			for (int i = 0; i < name_arr.size(); i++) {
				out.println("<tr><td>"
						+name_arr.get(i)+
						"</td>"+
						"<td>"+age_arr.get(i)+"</td>"+
						"<td>"+gender_arr.get(i)+"</td>"+
						"</tr>"
						);
			}
					out.println("</tbody>"
					+ "        </table>"
					+ "    </body>");
			
		} catch (SQLException e) {

		}
	}

}
