/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import java.util.Scanner;
import com.swingy.app.controllers.PlayerController;

public class PlayerViewConsole extends PlayerView implements Display {

	private PlayerController playerController;

	public PlayerViewConsole() {
		super();
	}

	public int	choosePlayer(PlayerController controller) {
		Scanner sc = new Scanner(System.in);
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
