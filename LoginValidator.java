package com.pennant.irctc.mvc;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(loadOnStartup = 1)
public class LoginValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession hs = request.getSession(true);
		UserModel user = new UserModel();
		user.setUsername(username);
		user.setPassword(password);

		boolean status = new LoginDAL().isvaliduser(user);
		if (status) {
			hs.setAttribute("LoggedIn", "true");
			rd = request.getRequestDispatcher("home.html");
			rd.forward(request, response);
		} else {
			hs.setAttribute("LoggedIn", "false");
			rd = request.getRequestDispatcher("loginpage.html");
			rd.forward(request, response);
		}
	}

}
