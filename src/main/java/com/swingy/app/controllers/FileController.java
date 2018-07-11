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
	Connection			conn;

	public FileController() {
		fileName = "jdbc:sqlite:swingy.db";
		conn = null;
		try {
			String driver = "org.sqlite.JDBC";
			Class.forName(driver);
			conn = DriverManager.getConnection(fileName);
			if (conn != null) {
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

	public List<PlayerModel> getHeros() {
		return (null);
	}

	public boolean saveHero(PlayerModel hero) {
		this.hero = hero;
		return (true);
	}

	public boolean updateHero(PlayerModel hero) {
		return (true);
	}
}
