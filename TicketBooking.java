package com.pennant.irctc.mvc;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TicketBooking
 */
@WebServlet(urlPatterns = { "/bookticket" }, loadOnStartup = 1)
public class TicketBooking extends HttpServlet {
	private Integer ticket_no;
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ticket_no =TicketDAL.get_Last_Ticket_No();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getParameterNames().hasMoreElements())
			doPost(request, response);
		else {
			response.setContentType("text/html");
			response.getWriter().println("<h1 style='color:red'>sorry boss you need send parameters</h1>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration<String> params = request.getParameterNames();
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String train_no = request.getParameter("trains");
		Date doj = Date.valueOf(request.getParameter("doj"));
		String class_selected = request.getParameter("class");
		ArrayList<String> name_arr = new ArrayList<>();
		ArrayList<String> gender_arr = new ArrayList<>();
		ArrayList<String> age_arr = new ArrayList<>();
		ArrayList<String> preference_arr = new ArrayList<>();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			if (param.startsWith("name")) {
				name_arr.add(request.getParameter(param));
			} else if (param.startsWith("gender")) {
				gender_arr.add(request.getParameter(param));
			} else if (param.startsWith("age")) {
				age_arr.add(request.getParameter(param));
			} else if (param.startsWith("preference")) {
				preference_arr.add(request.getParameter(param));
			}
		}
		Random r = new Random();
		int pnr_no = r.nextInt(100000, 999999);
		// response headers cors error setting
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.addHeader("Access-Control-Allow-Origin", "*");
		++ticket_no;
		PassengerListModel psngrs = new PassengerListModel();
		for (int i = 0; i < name_arr.size(); i++) {
			PassengerModel psngr = new PassengerModel();
			psngr.setTicket_No(ticket_no);
			psngr.setName(name_arr.get(i));
			psngr.setAge(Integer.parseInt(age_arr.get(i)));
			psngr.setGender(gender_arr.get(i));
			psngr.setPreference(preference_arr.get(i));
			psngrs.add(psngr);
		}
		TicketModel ticket = new TicketModel();
		ticket.setTicket_No(ticket_no);
		ticket.setPnr_No(pnr_no);
		ticket.setFrom(from);
		ticket.setTo(to);
		ticket.setDoj(doj);
		ticket.setClass_Index(Integer.parseInt(class_selected));
		ticket.setPassenger_count(psngrs.size());
		ticket.setTrain_No(Integer.parseInt(train_no));
		ticket.setTotal_Fare(TicketFareGenerator.getfare(psngrs, ticket));
		boolean ticket_Status = TicketDAL.bookticket(ticket);
		boolean passengers_Status = AddPassengersDAL.addpassengers(psngrs);
		if (ticket_Status && passengers_Status) {
			request.getServletContext().setAttribute("ticket_No", ticket_no.toString());
			rd = request.getRequestDispatcher("ticketsuccess.jsp");
			request.setAttribute("ticket", ticket);
			request.setAttribute("passengers", psngrs);
			rd.forward(request, response);
		} else {
			--ticket_no;
			rd = request.getRequestDispatcher("ticketfailure.jsp");
			rd.forward(request, response);
		}

	}

}