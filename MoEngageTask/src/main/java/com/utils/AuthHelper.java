package com.utils;

import java.security.MessageDigest;

public class AuthHelper {
	public static String hashPassword(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(password.getBytes());
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			hexString.append(String.format("%02x", b));
		}
		return hexString.toString();
	}
}
