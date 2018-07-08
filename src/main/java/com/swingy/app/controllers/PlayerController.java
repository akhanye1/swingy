/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.PlayerView;
import com.swingy.app.views.Display;
import com.swingy.app.controllers.FileController;
import com.swingy.app.views.PlayerViewConsole;

public class PlayerController {
	private PlayerModel player;
	private FileController fileController;
	private PlayerView playerView;

	public PlayerController(Display display) {
		playerView = (PlayerView)display;
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

	public void newPlayer() {
		System.out.println("New Player to add");
	}

	public int	choosePlayer() {
		int choice;

		if ((choice = this.playerView.choosePlayer(this)) == 1) {
			this.newPlayer();
		}
		else if (choice == 2) {
		}
		else if (playerView instanceof PlayerViewConsole) {
			System.out.println("Goodbye");
		}
		return (3);
	}
}
