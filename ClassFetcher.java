package com.pennant.irctc.mvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;
@WebServlet(loadOnStartup = 1)
public class ClassFetcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		JSONObject obj = new JSONObject();
		JSONArray class_Id_Arr = new JSONArray();
		JSONArray class_Name_Arr = new JSONArray();
		ClassListModel classes=new ClassDAL().getclasses(); 
		classes.forEach((clsmdl) -> {
			class_Id_Arr.put(clsmdl.getIndex());
			class_Name_Arr.put(clsmdl.getClass_Name().trim());
		});
		obj.put("class_id", class_Id_Arr);
		obj.put("class_name", class_Name_Arr);
		out.println(obj);
		out.close();
	}

}
