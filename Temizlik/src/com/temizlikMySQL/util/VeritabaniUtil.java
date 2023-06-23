package com.temizlikMySQL.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class VeritabaniUtil {
	
	static Connection conn = null;
	static String db ="jdbc:mysql://localhost/temizlikdb";
	static String kullanici ="root";
	static String sifre ="";
	
	public static Connection Baglan() {
		try {
			
			//"jdbc:mysql://ServerIPAdresi/db_ismi , kullanici , şifre"
			conn=DriverManager.getConnection(db,kullanici,sifre);
			
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
	
	public static String MD5Sifrele(String icerik) {
		
		try {
			MessageDigest md= MessageDigest.getInstance("MD5");
			//Byte Olarak Oku
			byte[] sifrelenmis = md.digest(icerik.getBytes());
			
			BigInteger no= new BigInteger(1, sifrelenmis);
			//Hex Hesapla
			String hashicerik=no.toString(16);
			while (hashicerik.length()<32) {
				hashicerik = "0"+hashicerik;
			}
			return hashicerik;
			
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
	}
	

	
}
