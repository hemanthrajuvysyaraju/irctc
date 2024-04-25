package com.pennant.irctc.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TrainServlet
 */
@WebServlet(urlPatterns = { "/gettrains" }, loadOnStartup = 1, asyncSupported = true)
public class TrainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrainListModel trains;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String from = request.getParameter("from");
		String to = request.getParameter("to");

		FromToStationModel data = new FromToStationModel();
		data.setFrom(from);
		data.setTo(to);
		trains = new TrainDAL().getTrains(data);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");

		JSONObject train_data = new JSONObject();
		JSONArray train_no_arr = new JSONArray();
		JSONArray train_name_arr = new JSONArray();

		trains.forEach((train) -> {
			train_no_arr.put(train.getTrain_no().trim());
			train_name_arr.put(train.getTrain_name().trim());
		});
		train_data.put("train_nos", train_no_arr);
		train_data.put("train_names", train_name_arr);
		out.println(train_data);
		out.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}