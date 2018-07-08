/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import java.util.Scanner;
import com.swingy.app.controllers.PlayerController;
import com.swingy.app.models.PlayerModel;
import javax.validation.*;

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
			temp = sc.nextLine();
		} while (!temp.equals("1") && !temp.equals("2") && !temp.equals("3"));
		if (temp.equals("1"))
			return ("Knight");
		else if (temp.equals("2"))
			return ("Elf");
		return ("Viking");
	}

	public void	createPlayer(PlayerModel playerModel) {
		String	temp;

		this.playerModel = playerModel;
		System.out.print("Name : ");
		temp = sc.nextLine();
		playerModel.setName(temp);
		this.playerModel.setPClass(getPlayerClass());
	}

	public int	choosePlayer(PlayerController controller) {
		String	temp;

		this.playerController = controller;
		System.out.println("WELCOME");
		System.out.println("=======");
		do {
			System.out.println("1 > Create player");
			System.out.println("2 > Choose save player");
			System.out.println("3 > Exit");
			System.out.print("Choice : ");
			temp = sc.nextLine();
			if (!temp.equals("1") && !temp.equals("2") && !temp.equals("3"))
				System.out.println("\nInvalid choice, choose between 1 - 3");
		} while (!temp.equals("1") && !temp.equals("2") && !temp.equals("3"));
		return (Integer.parseInt(temp));
	}

	public void refresh() {
	}
}
