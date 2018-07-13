/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import java.util.Scanner;
import com.swingy.app.controllers.PlayerController;
import com.swingy.app.models.PlayerModel;
import com.swingy.app.models.ValidationErrorModel;
import javax.validation.*;
import java.util.List;
import java.util.ArrayList;

public class PlayerViewConsole extends PlayerView implements Display {

	private PlayerController	playerController;
	private PlayerModel			playerModel;
	private String				name;
	private String				pClass;
	private int					level;
	private	int					experience;
	private int					attack;
	private Scanner				sc;

	public PlayerViewConsole() {
		super();
		sc = new Scanner(System.in);
	}

	private String	getPlayerClass() {
		int		tries;
		String	temp;

		tries = 0;
		do {
			if (tries > 0)
				System.out.println("Error encountered");
			System.out.println("Player Class Type");
			System.out.println("1 > Knight");
			System.out.println("2 > Elf");
			System.out.println("3 > Viking");
			System.out.print("Choice : ");
			temp = sc.nextLine();
		} while (!temp.equals("1") && !temp.equals("2") && !temp.equals("3"));
		if (temp.equals("1"))
			return ("Knight");
		else if (temp.equals("2"))
			return ("Elf");
		return ("Viking");
	}

	public void	createPlayer(PlayerModel playerModel) {
		String						temp;
		List<ValidationErrorModel>	errors;

		this.playerModel = playerModel;
		System.out.print("Hero Name : ");
		temp = sc.nextLine();
		playerModel.setName(temp);
		this.playerModel.setPClass(getPlayerClass());
		errors = new ArrayList<ValidationErrorModel>();
		if (!this.playerController.validatePlayer(errors)) {
			System.out.println("\nERRORS:");
			for (ValidationErrorModel validate : errors) {
				System.out.println(validate.getField() + " : " +
						validate.getErrorMessage());
			}
			System.out.println("Please try again\nEnter new Player: ");
			createPlayer(playerModel);
			return ;
		}
		this.playerController.savePlayer();
	}

	public int	choosePlayer(PlayerController controller) {
		String	temp;
		boolean	withinRange;

		this.playerController = controller;
		System.out.println("WELCOME");
		System.out.println("=======");
		do {
			withinRange = false;
			System.out.println("1 > Create player");
			System.out.println("2 > Choose saved player");
			System.out.println("3 > Exit");
			System.out.print("Choice : ");
			temp = sc.nextLine();
			withinRange = (temp.equals("1") || temp.equals("2") || temp.equals("3"));
			if (!withinRange)
				System.out.println("\nInvalid choice, choose between 1 - 3");
		} while (!withinRange);
		return (Integer.parseInt(temp));
	}

	public void refresh() {
	}

	private void	printHeadings() {
		System.out.format("%-5s", "Rec");
		System.out.format("%-20s", "Name");
		System.out.format("%-20s", "Class");
		System.out.format("%-5s", "lvl");
		System.out.format("%-5s", "exp");
		System.out.format("%-5s", "Atck");
		System.out.format("%-5s", "Df");
		System.out.format("%-5s", "Hp");
		System.out.println();
		System.out.format("%-5s", "===");
		System.out.format("%-20s", "====");
		System.out.format("%-20s", "=====");
		System.out.format("%-5s", "===");
		System.out.format("%-5s", "===");
		System.out.format("%-5s", "====");
		System.out.format("%-5s", "==");
		System.out.format("%-5s", "==");
		System.out.println();
	}

	public void	selectPlayer(List<PlayerModel> players) {
		boolean validInput;
		int		choice;
		int		maxNum;
		int		index;

		if (players.size() == 0) {
			System.out.println("Database is empty");
			return ;
		}
		do {
			choice = -1;
			maxNum = 0;
			index = 1;
			System.out.println("\nCHOOSE PLAYER");
			System.out.println("===============");
			printHeadings();
			validInput = false;
			for (PlayerModel tempPlayer : players) {
				maxNum = tempPlayer.getRec();
				System.out.format("%-5d", index++);
				System.out.format("%-20s", tempPlayer.getName());
				System.out.format("%-20s", tempPlayer.getPClass());
				System.out.format("%-5d", tempPlayer.getLevel());
				System.out.format("%-5d", tempPlayer.getExperience());
				System.out.format("%-5d", tempPlayer.getAttack());
				System.out.format("%-5d", tempPlayer.getDefence());
				System.out.format("%-5d", tempPlayer.getHitPoints());
				System.out.println();
			}
			System.out.println("0 (EXIT)");
			System.out.print("Choice : ");
			if (sc.hasNextInt())
				choice = sc.nextInt();
			validInput = (choice >= 0 && choice <= maxNum);
			if (!validInput)
				System.out.println("Invalid option, please try again");
		} while (!validInput);
		if (choice > 0) {
			playerController.setPlayer(players.get(choice - 1));
		}
	}
}
