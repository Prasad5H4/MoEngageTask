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

import com.utils.DBConnection;

/**
 * Servlet implementation class editlist
 */
@WebServlet("/editlist")
public class editlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
		int listId = Integer.parseInt(request.getParameter("id"));

		try (Connection conn = DBConnection.getConnection()) {
			String query = "SELECT name, response_codes, image_links FROM lists WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, listId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				request.setAttribute("id", listId);
				request.setAttribute("name", rs.getString("name"));
				request.setAttribute("response_codes", rs.getString("response_codes"));
				request.setAttribute("image_links", rs.getString("image_links"));
				request.getRequestDispatcher("/edit.jsp").forward(request, response);
			} else {
				response.getWriter().write("List not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error occurred while fetching the list.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int listId = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String responseCodes = req.getParameter("response_codes");
		String imageLinks = req.getParameter("image_links");

		try (Connection conn = DBConnection.getConnection()) {
			String query = "UPDATE lists SET name = ?, response_codes = ?, image_links = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, responseCodes);
			stmt.setString(3, imageLinks);
			stmt.setInt(4, listId);
			stmt.executeUpdate();

			resp.sendRedirect("lists.html");
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("Error occurred while updating the list.");
		}
		
	}

}
