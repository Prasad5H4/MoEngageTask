package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utils.AuthHelper;
import com.utils.DBConnection;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try (Connection conn = DBConnection.getConnection()) {
			String query = "SELECT id, password FROM users WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next() && AuthHelper.hashPassword(password).equals(rs.getString("password"))) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", rs.getInt("id"));
				response.sendRedirect("search.html");
			} else {
				response.getWriter().write("Invalid username or password.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error occurred.");
		}
		// TODO Auto-generated method stub
	}

}
