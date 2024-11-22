package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String URL = "jdbc:postgresql://localhost:5432/MoEngageTask";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Prasad@2001";

	public static Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
