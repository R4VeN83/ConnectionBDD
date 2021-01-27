package com.acar.ConnectionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connexion {
	
	static Connection con = null;
	static PreparedStatement prepareStat = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/universite?serverTimezone=UTC", "root", "");
			if (con != null) {
				System.out.println("Connexion réussi");
			} else {
				System.out.println("Connexion échouée");
				System.out.println("Test d'un pull");
			}
			
			String insertQueryStatement = "INSERT  INTO  etudiant  VALUES  (?,?,?,?,?,?,?)";
			
			prepareStat = con.prepareStatement(insertQueryStatement);
			prepareStat.setInt(1, 128);
			prepareStat.setString(2, "Pierre");
			prepareStat.setString(3, "Paul");
			prepareStat.setString(4, "1990-08-19");
			prepareStat.setString(5, "rue de la victoire");
			prepareStat.setString(6, "69000");
			prepareStat.setString(7, "Lyon");
			
			prepareStat.executeUpdate();
			
			
			String getQueryStatement = "SELECT * FROM etudiant";
			prepareStat = con.prepareStatement(getQueryStatement);
			ResultSet rs = prepareStat.executeQuery();
			while (rs.next()) {
				int numetu = rs.getInt("numetu");
				String nom = rs.getString("nom");
				
				System.out.println("numetu : " + numetu + ", nom : " + nom);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		

	}

}
