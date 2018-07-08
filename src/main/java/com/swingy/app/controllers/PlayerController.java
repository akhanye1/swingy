/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.PlayerView;
import com.swingy.app.views.Display;
import com.swingy.app.controllers.FileController;
import com.swingy.app.views.PlayerViewConsole;
import javax.validation.*;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.constraints.*;
import javax.validation.executable.*;
import java.util.Set;
import java.lang.reflect.Method;
import javax.*;
import org.hibernate.validator.*;
import org.hibernate.*;

public class PlayerController {
	private PlayerModel player;
	private FileController fileController;
	private PlayerView playerView;
	private ValidatorFactory validatorFactory;
	private Validator validator;

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

	private void runValidator() {
		System.out.println("Running validation start");
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Set<ConstraintViolation<PlayerModel>> validationErrors = validator.validate(this.player);
		System.out.println("Running validation");
		if (!validationErrors.isEmpty()) {
			for (ConstraintViolation<PlayerModel> error : validationErrors) {
				System.out.println(error.getMessageTemplate() + "::" +
						error.getPropertyPath()+"::"+error.getMessage());
			}
		}
	}

	public void validatePlayer() {
		this.player.setLevel(0);
		this.player.setHitPoints(100);
		switch (this.player.getPClass()) {
			case "Knight":
				this.player.setExperience(150);
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
		this.runValidator();
	}

	public void newPlayer() {
		this.player = new PlayerModel();
		this.playerView.createPlayer(player);
		this.validatePlayer();
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
