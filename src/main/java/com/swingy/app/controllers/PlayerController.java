/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

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

public class PlayerController {
	private PlayerModel 		player;
	private FileController 		fileController;
	private PlayerView 			playerView;
	private final Validator 			validator;

	public PlayerController(Display display) {
		fileController = new FileController();
		playerView = (PlayerView)display;
		final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
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
		fileController.saveHero(this.player);
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
		this.player.setLevel(0);
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
