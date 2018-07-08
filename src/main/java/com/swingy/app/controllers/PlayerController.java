/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.PlayerView;
import com.swingy.app.views.Display;
import com.swingy.app.controllers.FileController;

public class PlayerController {
	private PlayerModel player;
	private FileController fileController;
	private PlayerView playerView;

	public PlayerController(PlayerView display) {
		/*this.view = display;
		playerView = (PlayerView)display;*/
		playerView = display;
	}

	public void	takeHit() { 
	}

	public void movePlayer() {
	}

	public void setDirection() {
	}

	public void selectArtifact() {
	}

	public void	savePlayer() {
	}

	public void	updatePlayer() {
	}

	public void	choosePlayer() {
		int choice;
		if (((choice = this.playerView.choosePlayer()) >= 0) &&
			(choice <= 3)) {

		}
		else {
		}
	}
}
