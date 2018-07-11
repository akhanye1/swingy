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
			System.out.println("2 > Choose save player");
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
}
