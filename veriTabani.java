package üys;

import java.sql.Statement;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class veriTabani {
	
	private Connection connection;
	
	
	private veriTabani() {
		try {
		String url = "jdbc:sqlite:uys.db";
		connection = DriverManager.getConnection(url);
		createTables();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	private static class Holder {
		private static final veriTabani INSTANCE = new veriTabani();
	}
	
	
	public static veriTabani getInstance() {
		return Holder.INSTANCE;
	}
	
	public Connection getConnection() {
		try {
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:sqlite:uys.db");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection() {
		if(connection != null) {
			try{
				connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTables() {
		
		String sql = "CREATE TABLE IF NOT EXISTS user("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "username TEXT NOT NULL UNIQUE, "
				+ "password TEXT NOT NULL, "
				+ "role TEXT NOT NULL)";
		
		try(Statement stmt = connection.createStatement()){
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String hashPassword (String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final String ANAHTAR ="1234567890123456";
	
	public static String encrypt(String veri) throws Exception{
		SecretKeySpec secretKey = new SecretKeySpec(ANAHTAR.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrypted = cipher.doFinal(veri.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	public static String decrypt(String sifreliVeri) throws Exception{
		SecretKeySpec secretKey = new SecretKeySpec(ANAHTAR.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(sifreliVeri));
		return new String(decrypted);
	}
}
