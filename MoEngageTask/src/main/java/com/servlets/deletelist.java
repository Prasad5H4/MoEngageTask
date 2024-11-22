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
 * Servlet implementation class deletelist
 */
@WebServlet("/deletelist")
public class deletelist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deletelist() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int listId = Integer.parseInt(request.getParameter("id"));

		try (Connection conn = DBConnection.getConnection()) {
			String query = "DELETE FROM lists WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, listId);
			stmt.executeUpdate();

			response.sendRedirect("lists.html");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error occurred while deleting the list.");
		}
		// TODO Auto-generated method stub
	}

}
