package fr.click;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class OnlineManager {
	
	private static String token;
	
	public static String[][] getRank() {
		try {
			String[][] ranks = new String[10][2];
			URL url = new URL("http://tc-server.redirectme.net:8080/classement.php");
			InputStreamReader ipsr;
			ipsr = new InputStreamReader(url.openStream());
			BufferedReader br = new BufferedReader(ipsr);
			String line = null;
			StringBuffer buffer = new StringBuffer();
			
			while ((line = br.readLine()) != null) {
				buffer.append(line).append('\n');
			}
			
			br.close();
			
			String[] scores = buffer.toString().split("<br />");
			
			for (int j=0; j<scores.length; j++) {
				String[] buf = scores[j].split("-");
				if (buf.length == 2) {
					ranks[j][0] = buf[0];
					ranks[j][1] = buf[1];
				}
			}
			
			return ranks;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean login(String username, String password) {
		String salt = "Jesuisuncanard75";
		
		String passHashed = MD5(salt + password);
		
		URL url = null;
		
		try {
			url = new URL("http://tc-server.redirectme.net:8080/gameLogin.php?user=" + username + "&pass=" + passHashed);
			InputStreamReader ipsr = null;
			
			ipsr = new InputStreamReader(url.openStream());
			
			BufferedReader br = new BufferedReader(ipsr);
			String line = null;
			StringBuffer buffer = new StringBuffer();
			
			while ((line = br.readLine()) != null) {
				buffer.append(line).append('\n');
			}
			
			br.close();
			
			String page = buffer.toString();
			
			page = page.split("\"hey\">")[1];
			
			page = page.split("</p")[0];
			
			token = page;
			
			if(!token.equalsIgnoreCase("0")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return false;
		
	}
	
	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void setScore(String username, int score) {
		try {
			URL url = new URL("http://tc-server.redirectme.net:8080/newScore.php?user=" + username + "&token=" + token + "&score=" + score);
			
			InputStreamReader ipsr = null;
			
			ipsr = new InputStreamReader(url.openStream());
			
			BufferedReader br = new BufferedReader(ipsr);
			String line = null;
			StringBuffer buffer = new StringBuffer();
			
			while ((line = br.readLine()) != null) {
				buffer.append(line).append('\n');
			}
			
			br.close();
		} catch(IOException e1) {
			e1.printStackTrace();
		}
	}
}
