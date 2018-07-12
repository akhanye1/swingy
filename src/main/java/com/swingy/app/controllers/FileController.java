/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.Display;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class FileController {
	private String 		fileName;
	private PlayerModel	hero;
	Statement			statement;
	PreparedStatement	pStatement;

	public String createPlayerTable() {
		String	initString;

		initString = "CREATE TABLE IF NOT EXISTS hero " +
			"(rec INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"name Varchar(30), " +
			"pClass Varchar(30), " +
			"level int, " +
			"experience int, " +
			"attack int, " +
			"defence int, " +
			"hitPoints int " +
			")";
		return (initString);
	}

	/*public String createArtifactsTable() {
		String initString;

		initString = "CREATE TABLE IF NOT EXISTS artifacts " +
			"(rec INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"" +
			")";
	}*/

	private Connection	getConnection() {
		try {
			return (DriverManager.getConnection(fileName));
		}
		catch (SQLException err) {
			return (null);
		}
	}

	public FileController() {
		fileName = "jdbc:sqlite:swingy.db";
		Connection conn = null;
		try {
			String driver = "org.sqlite.JDBC";
			Class.forName(driver);
			conn = getConnection();
			if (conn != null) {
				statement = conn.createStatement();
				statement.execute(createPlayerTable());
				DatabaseMetaData meta = conn.getMetaData();
				this.getHeros();
			}
		}
		catch (SQLException err) {
			System.out.println("Error connecting to database :: " + err.getMessage());
		}
		catch (ClassNotFoundException err) {
			System.out.println("Class not found : " + err.getMessage());
		}
	}

	private String getString() {
		String readString;

		readString = "SELECT "+
			"name, " +
			"pClass " +
			"from hero;";
		return (readString);
	}

	public List<PlayerModel> getHeros() {
		Connection conn;
		try {
			conn = this.getConnection();
			statement = conn.createStatement();
			ResultSet	rs = statement.executeQuery(getString());
			while (rs.next()) {
				System.out.print("Hero : " + rs.getString("name") + " - ");
				System.out.println(rs.getString("pClass"));
			}
		}
		catch (SQLException err) {
			System.out.println("Error reading data : " + err.getMessage());
		}
		return (null);
	}

	private String saveString() {
		String saveStr;

		saveStr = "INSERT INTO hero " +
			"(name, " +
			"pClass, " +
			"level, " +
			"experience, " +
			"attack, " +
			"defence, " +
			"hitPoints) VALUES " +
			"(?, ?, ?, ?, ?, ?, ?)";
		return (saveStr);
	}

	public boolean saveHero(PlayerModel hero) {
		this.hero = hero;
		Connection conn;

		try {
			conn = getConnection();
			pStatement = conn.prepareStatement(saveString());
			pStatement.setString(1, this.hero.getName());
			pStatement.setString(2, this.hero.getPClass());
			pStatement.setInt(3, this.hero.getExperience());
			pStatement.setInt(4, this.hero.getAttack());
			pStatement.setInt(5, this.hero.getDefence());
			pStatement.setInt(6, this.hero.getHitPoints());
			pStatement.executeUpdate();
		}
		catch (SQLException err) {
			System.out.println("Error creating hero : " + err.getMessage());
		}
		return (true);
	}

	public boolean updateHero(PlayerModel hero) {
		return (true);
	}
}
