/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import	com.swingy.app.controllers.ArenaController;
import	com.swingy.app.models.PlayerModel;
import	java.util.Scanner;

public class ArenaViewConsole extends ArenaView implements Display {
	private char			map[][];
	private ArenaController	arenaController;
	private PlayerModel		playerModel;

	private int		getNumber() {
		String	sChoice;
		int		choice;
		Scanner	sc = new Scanner(System.in);

		try {
			sChoice = sc.nextLine();
			choice = Integer.parseInt(sChoice);
			return (choice);
		}
		catch (Exception err) {
			System.out.println("Invalid choice");
			return (getNumber());
		}
	}

	private void	setMapVisible() {
		int		height;
		int		width;
		int		choice;
		String	sChoice;
		boolean	keyOkay;
		Scanner sc;

		height = this.map.length;
		width = this.map[0].length;
		System.out.print("Hero : " + this.playerModel.getName());
		System.out.print(" Health : " + this.playerModel.getHitPoints());
		System.out.println(" Exp : " + this.playerModel.getExperience());
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(map[y][x]);
			}
			System.out.println();
		}
		sc = new Scanner(System.in);
		do {
			System.out.println("Move Action");
			System.out.println("==========");
			System.out.println("1 > North");
			System.out.println("2 > East");
			System.out.println("3 > South");
			System.out.println("4 > West");
			System.out.println("========");
			System.out.println("10 > Change to GUI");
			System.out.println("========");
			System.out.println("0 > EXIT");
			System.out.print("Choice : ");
			choice = this.getNumber();
			keyOkay = ((choice >= 0 && choice <= 4) ||
					(choice == 10));
		} while (!keyOkay);
		this.arenaController.setSelection(choice);
	}

	public void updateMap(char map[][]) {
		this.map = map;
		System.out.print("\033[H\033[2J");
		this.setMapVisible();
	}

	public void showMap(char map[][], ArenaController arenaController, PlayerModel playerModel) {
		this.map = map;
		this.playerModel = playerModel;
		this.arenaController = arenaController;
		System.out.print("\033[H\033[2J");
		this.setMapVisible();
	}

	public void prepareFight(String prepareString) {
		System.out.print("\033[H\033[2J");
		System.out.println(prepareString);
	}

	public void updateFight(String attackMessage) {
		System.out.println(attackMessage);
	}

	public void showMessage(String message) {
		Scanner sc = new Scanner(System.in);

		System.out.println(message);
		sc.nextLine();
	}

	public void	makeChoice(PlayerModel enemy, ArenaController arenaControllerTemp) {
		int choice;

		do {
			System.out.print("Do you want to fight " + enemy.getName());
			System.out.print(" with XP " + enemy.getExperience());
			System.out.println(" and level " + enemy.getLevel() + " fighter");
			System.out.println("1 > Fight");
			System.out.println("2 > Run");
			choice = this.getNumber();
		} while (choice < 1 && choice > 2);
		if (choice == 1) {
			arenaControllerTemp.simulateFight(enemy);
			return ;
		}
		arenaControllerTemp.reverseChoice();
	}

	public void refresh() {
	}
}
