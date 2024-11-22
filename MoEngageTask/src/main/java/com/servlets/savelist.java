package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.DBConnection;

/**
 * Servlet implementation class savelist
 */
@WebServlet("/savelist")
public class savelist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public savelist() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = (int) request.getSession().getAttribute("userId");
		String name = request.getParameter("name");
		String responseCodes = request.getParameter("responseCodes");
		String imageLinks = request.getParameter("imageLinks");

		try (Connection conn = DBConnection.getConnection()) {
			String query = "INSERT INTO lists (user_id, name, response_codes, image_links) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			stmt.setString(2, name);
			stmt.setString(3, responseCodes);
			stmt.setString(4, imageLinks);
			stmt.executeUpdate();
			response.sendRedirect("lists.html");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error occurred while saving the list.");
		}
		// TODO Auto-generated method stub
	}

}
