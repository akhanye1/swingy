/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import com.swingy.app.models.PlayerModel;
import com.swingy.app.models.ValidationErrorModel;
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
			"rec, " +
			"name, " +
			"pClass, " +
			"level, " +
			"experience, " +
			"attack, " +
			"defence, " +
			"hitPoints " +
			"from hero;";
		return (readString);
	}

	public PlayerModel		getPlayer(int rec) {
		return (null);
	}

	public List<PlayerModel> getHeros() {
		Connection 					conn;
		List<PlayerModel>			players;
		List<ValidationErrorModel> 	errors;

		try {
			conn = this.getConnection();
			statement = conn.createStatement();
			ResultSet	rs = statement.executeQuery(getString());
			players = new ArrayList<PlayerModel>();
			errors = new ArrayList<ValidationErrorModel>();
			while (rs.next()) {
				PlayerModel tempModel = new PlayerModel();
				tempModel.setRec(rs.getInt("rec"));
				tempModel.setName(rs.getString("name"));
				tempModel.setPClass(rs.getString("pClass"));
				tempModel.setLevel(rs.getInt("level"));
				tempModel.setExperience(rs.getInt("experience"));
				tempModel.setAttack(rs.getInt("attack"));
				tempModel.setDefence(rs.getInt("defence"));
				tempModel.setHitPoints(rs.getInt("hitPoints"));
				if (ValidateController.runValidator(errors, tempModel)) {
					players.add(tempModel);
				}
			}
		}
		catch (SQLException err) {
			System.out.println("Error reading data : " + err.getMessage());
			return (null);
		}
		return (players);
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

	public boolean	updateHero(PlayerModel saveHero) {
		String		updateString;
		Connection	conn;

		updateString = "UPDATE hero set "+
			"level = ?, " +
			"experience = ?, " +
			"attack = ?, " +
			"defence = ? " +
			"where rec = ?";
		try {
			conn = getConnection();
			pStatement = conn.prepareStatement(updateString);
			pStatement.setInt(1, saveHero.getLevel());
			pStatement.setInt(2, saveHero.getExperience());
			pStatement.setInt(3, saveHero.getAttack());
			pStatement.setInt(4, saveHero.getDefence());
			pStatement.setInt(5, saveHero.getRec());
			pStatement.executeUpdate();
		}
		catch (SQLException err) {
			return (false);
		}
		return (true);

	}

	public boolean saveHero(PlayerModel hero) {
		this.hero = hero;
		Connection conn;

		try {
			conn = getConnection();
			pStatement = conn.prepareStatement(saveString());
			pStatement.setString(1, this.hero.getName());
			pStatement.setString(2, this.hero.getPClass());
			pStatement.setInt(3, this.hero.getLevel());
			pStatement.setInt(4, this.hero.getExperience());
			pStatement.setInt(5, this.hero.getAttack());
			pStatement.setInt(6, this.hero.getDefence());
			pStatement.setInt(7, this.hero.getHitPoints());
			pStatement.executeUpdate();
		}
		catch (SQLException err) {
			return (false);
		}
		return (true);
	}
}
