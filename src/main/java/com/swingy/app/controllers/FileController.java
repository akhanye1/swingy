
package com.swingy.app.controllers;

import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.Display;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class FileController {
	private String fileName;
	private PlayerModel hero;

	public FileController() {
		System.out.println("Checking database");
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:test.sql");
			System.out.println("Connection established");
		}
		catch (SQLException err) {
			System.out.println("SQL Error : " + err.getMessage());
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
