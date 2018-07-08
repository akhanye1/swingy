
package com.swingy.app.controllers;

import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.Display;
import java.util.List;
import java.util.ArrayList;

public class FileController {
	private String fileName;
	private PlayerModel hero;

	public FileController() {
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
