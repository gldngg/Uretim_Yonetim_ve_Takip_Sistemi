package database;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Veritabani {

	private static final String URL = "jdbc:sqlite:uys.db";
	private Connection connection;

	private Veritabani() {
		try {
			this.connection = DriverManager.getConnection(URL);
			System.out.println("Veritabanına başarıyla bağlanıldı.");
			createTables();
			System.out.println("Veritabanı ve tablolar hazır.");
		} catch (SQLException e) {
			System.err.println("Bağlantı hatası: " + e.getMessage());
		}
	}

	private static class Holder {
		private static final Veritabani INSTANCE = new Veritabani();
	}

	public static Veritabani getInstance() {
		return Holder.INSTANCE;
	}

	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(URL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void createTables() {

		String usersSql = "CREATE TABLE IF NOT EXISTS users ("
				+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ " username TEXT NOT NULL UNIQUE, "
				+ " password TEXT NOT NULL, "
				+ " role TEXT NOT NULL "
				+ ");";

		String machinesSql = "CREATE TABLE IF NOT EXISTS machines ("
				+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ " makine_tipi TEXT NOT NULL, "
				+ " makine_kodu TEXT NOT NULL UNIQUE, "
				+ " bolum TEXT NOT NULL, "
				+ " kapasite TEXT NOT NULL, "
				+ " durum TEXT NOT NULL, "
				+ " bakim_periyodu TEXT NOT NULL, "
				+ " lokasyon TEXT NOT NULL "
				+ ");";

		// 🔥 Duruş/Kayıp tablosu
		String downtimesSql = "CREATE TABLE IF NOT EXISTS downtimes ("
				+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ " makine_tipi TEXT NOT NULL, "
				+ " makine_kodu TEXT NOT NULL, "
				+ " baslangic TEXT NOT NULL, "
				+ " bitis TEXT NOT NULL, "
				+ " sure TEXT NOT NULL, "
				+ " durus_turu TEXT NOT NULL, "
				+ " durus_nedeni TEXT NOT NULL, "
				+ " aciklama TEXT NOT NULL "
				+ ");";
		
		String siparisSql = "CREATE TABLE IF NOT EXISTS siparisler ("
                   + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + " siparis_adi TEXT NOT NULL, "
                   + " siparis_kodu TEXT NOT NULL UNIQUE, "
                   + " musteri TEXT NOT NULL, "
                   + " urun_adi TEXT NOT NULL, "
                   + " miktar INTEGER DEFAULT 0, "
                   + " termin_tarihi TEXT "
                   + ");";
		
       String isEmriSql = "CREATE TABLE IF NOT EXISTS is_emirleri ("
                   + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + " siparis_id INTEGER, "
                   + " gorev TEXT, "
                   + " makine TEXT, "
                   + " FOREIGN KEY (siparis_id) REFERENCES siparisler(id) "
                   + ");";
		
		try (Statement stmt = connection.createStatement()) {
			stmt.execute(usersSql);
			stmt.execute(machinesSql);
			stmt.execute(downtimesSql);

			System.out.println("Tablolar kontrol edildi/oluşturuldu.");
		} catch (SQLException e) {
			System.err.println("Tablo oluşturma hatası: " + e.getMessage());
		}
	}

	public String hashPassword(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private static final String ANAHTAR = "1234567890123456";

	public static String sifrele(String veri) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(ANAHTAR.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] sifrelenmis = cipher.doFinal(veri.getBytes());
		return Base64.getEncoder().encodeToString(sifrelenmis);
	}

	public static String coz(String sifreliVeri) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(ANAHTAR.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] cozulmus = cipher.doFinal(Base64.getDecoder().decode(sifreliVeri));
		return new String(cozulmus);
	}
}
