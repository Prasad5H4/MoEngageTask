package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.DBConnection;

/**
 * Servlet implementation class lists
 */
@WebServlet("/lists")
public class lists extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public lists() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = (int) request.getSession().getAttribute("userId");

		try (Connection conn = DBConnection.getConnection()) {
			String query = "SELECT id, name, creation_date FROM lists WHERE user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			List<Map<String, Object>> lists = new ArrayList<>();
			while (rs.next()) {
				Map<String, Object> list = new HashMap<>();
				list.put("id", rs.getInt("id"));
				list.put("name", rs.getString("name"));
				list.put("creation_date", rs.getTimestamp("creation_date"));
				lists.add(list);
			}

			request.setAttribute("lists", lists);
			request.getRequestDispatcher("/lists.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error occurred while fetching the lists.");
		}
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
