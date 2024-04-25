package com.pennant.irctc.mvc;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticatorFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession hs = req.getSession(true);
		RequestDispatcher rd = null;

		String status = (String) hs.getAttribute("LoggedIn");
		if (status == null || status.equals("false")) {
			rd = req.getRequestDispatcher("forbidden.html");
			rd.forward(req, res);
			System.out.println(status + "inside false");
		} else {
			chain.doFilter(request, response);
			System.out.println(status);
		}
	}

}
