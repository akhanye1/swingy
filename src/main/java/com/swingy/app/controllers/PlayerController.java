/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import com.swingy.app.views.ArenaView;
import com.swingy.app.models.PlayerModel;
import com.swingy.app.models.ValidationErrorModel;
import com.swingy.app.views.PlayerView;
import com.swingy.app.views.Display;
import com.swingy.app.controllers.FileController;
import com.swingy.app.views.PlayerViewConsole;
import javax.validation.*;
import javax.validation.constraints.*;
import javax.validation.executable.*;
import java.util.Set;
import java.lang.reflect.Method;
import javax.*;
import org.hibernate.validator.*;
import org.hibernate.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.lang.*;

public class PlayerController {
	private PlayerModel 		player;
	private PlayerModel			p1;
	private PlayerModel			p2;
	private ArenaView			arenaView;
	private FileController 		fileController;
	private PlayerView 			playerView;
	private final Validator 	validator;
	private ArenaController		controller;
	private Display				mainDisplay;

	public PlayerController(Display display) {
		fileController = new FileController();
		playerView = (PlayerView)display;
		this.mainDisplay = display;
		final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	public PlayerController(PlayerModel player1, PlayerModel player2,
			ArenaView arenaView, ArenaController arenaController) {
		Random rand = new Random();
		fileController = new FileController();

		if (rand.nextInt(2) == 0) {
			p1 = player1;
			p2 = player2;
		}
		else {
			p1 = player2;
			p2 = player1;
		}
		this.arenaView = arenaView;
		this.mainDisplay = null;
		validator = null;
		controller = arenaController;
	}

	public void	simulateFight() {
		int playerNumber;
		int attackValue;

		playerNumber = 1;
		attackValue = 0;
		do {
			if (playerNumber == 1) {
				attackValue = attack(p1);
				takeHit(p2, attackValue);
				playerNumber = 2;
			}
			else {
				attackValue = attack(p2);
				takeHit(p1, attackValue);
				playerNumber = 1;
			}
		} while (p1.getHitPoints() != 0 && p2.getHitPoints() != 0);
		controller.fightOver();
	}

	private int attack(PlayerModel tempPlayer) {
		int		attackValue;
		String	attackMessage;

		attackValue = tempPlayer.getAttack();
		attackMessage = tempPlayer.getName() + " attacks with " + tempPlayer.getAttack() + " points";
		this.arenaView.updateFight(attackMessage);
		return (attackValue);
	}

	public void	addExperience(PlayerModel playerWon, PlayerModel enemyDefeated) {
		int totalExperience;
		int totalAttack;
		int totalDefence;
		int	nextLevel;

		totalExperience = enemyDefeated.getExperience() * 2;
		totalAttack = enemyDefeated.getAttack() / 2;
		playerWon.setExperience(playerWon.getExperience() + totalExperience);
		playerWon.setAttack(playerWon.getAttack() + totalAttack);
		nextLevel = ((playerWon.getLevel() + 1) * 1000);
	 	nextLevel += (Math.pow((double)playerWon.getLevel(), 2.0) * 450);
		if (playerWon.getExperience() >= nextLevel) {
			playerWon.setLevel(playerWon.getLevel() + 1);
		}
		fileController.updateHero(playerWon);
	}

	public void	takeHit(PlayerModel tempPlayer, int attackValue) {
		int		hPoint;
		int		defence;
		String	defenceMessage;

		hPoint = tempPlayer.getHitPoints();
		defence = tempPlayer.getDefence();
		defenceMessage = tempPlayer.getName() + " ";
		if (defence > 0) {
			defenceMessage += "deflects some of the hit by " + defence;
			attackValue -= defence;
		}
		if (attackValue < 0) {
			defenceMessage += " is not hurt by the attack";
			this.arenaView.updateFight(defenceMessage);
			return ;
		}
		hPoint -= attackValue;
		if (hPoint <= 0) {
			defenceMessage += " <<" + tempPlayer.getName() + " is DEAD>>";
			tempPlayer.setHitPoints(0);
			this.arenaView.updateFight(defenceMessage);
			return ;
		}
		defenceMessage += " takes " + attackValue + " hit";
		this.arenaView.updateFight(defenceMessage);
		tempPlayer.setHitPoints( tempPlayer.getHitPoints() - attackValue);
	}

	public void movePlayer() {

	}

	public void setDirection() {

	}

	public void selectArtifact() {
	}

	public PlayerModel	getLastPlayer() {
		int	last;

		List<PlayerModel> localPlayers = fileController.getHeros();
		return (localPlayers.get(localPlayers.size()  - 1));
	}

	public boolean	savePlayer() {
		return (fileController.saveHero(this.player));
	}

	public void	updatePlayer() {
	}

	private boolean runValidator(List<ValidationErrorModel> errors) {
		boolean ok;

		ok = true;
		Set<ConstraintViolation<PlayerModel>> validationErrors = validator.validate(player);
		if (!validationErrors.isEmpty()) {
			ok = false;
			for (ConstraintViolation<PlayerModel> error : validationErrors) {
				ValidationErrorModel tempModel = new ValidationErrorModel(error.getPropertyPath().toString(), error.getMessage());
				errors.add(tempModel);
			}
		}
		return (ok);
	}

	public boolean validatePlayer(List<ValidationErrorModel> errors) {
		this.player.setLevel(1);
		this.player.setHitPoints(100);
		switch (this.player.getPClass()) {
			case "Knight":
				this.player.setExperience(250);
				this.player.setAttack(15);
				this.player.setDefence(10);
				break ;
			case "Elf":
				this.player.setExperience(150);
				this.player.setAttack(5);
				this.player.setDefence(5);
				break ;
			case "Viking":
				this.player.setExperience(300);
				this.player.setAttack(25);
				this.player.setDefence(25);
				break ;
		}
		return (this.runValidator(errors));
	}

	public void newPlayer() {
		this.player = new PlayerModel();
		this.playerView.createPlayer(player);
	}

	public void setPlayer(PlayerModel playerModel) {
		this.player = playerModel;
		this.controller = new ArenaController(this, this.mainDisplay);
		this.controller.initPlay();
	}

	public void	selectPlayer() {
		List<PlayerModel> players = fileController.getHeros();
		this.playerView.selectPlayer(players);
	}

	public int	choosePlayer() {
		int choice;

		if ((choice = this.playerView.choosePlayer(this)) == 1) {
			this.newPlayer();
		}
		else if (choice == 2) {
			this.selectPlayer();
		}
		else if (playerView instanceof PlayerViewConsole) {
			System.out.println("Goodbye");
		}
		return (3);
	}

	public PlayerModel	getPlayer() {
		return (this.player);
	}
}
