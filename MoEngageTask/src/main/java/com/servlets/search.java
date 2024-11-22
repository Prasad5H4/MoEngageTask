package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public search() {
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
		String filter = request.getParameter("filter");
		List<String> responseCodes = getResponseCodes(filter);

		List<String> imageUrls = new ArrayList<>();
		for (String code : responseCodes) {
			imageUrls.add("https://http.dog/" + code + ".jpg");
		}

		request.setAttribute("imageUrls", imageUrls);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	private List<String> getResponseCodes(String filter) {
		List<String> codes = new ArrayList<>();
		if (filter.matches("\\d+x")) {
			String prefix = filter.substring(0, filter.length() - 1);
			for (int i = 0; i <= 9; i++) {
				codes.add(prefix + i);
			}
		} else {
			codes.add(filter);
		}
		return codes;
		
	}

}
