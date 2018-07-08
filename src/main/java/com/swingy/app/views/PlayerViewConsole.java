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

	private void	setName() {
		boolean ok = false;
		String temp;
		do {
			ok = false;
			System.out.print("Hero Name : ");
			try {
				temp = sc.nextLine();
				System.out.println("Storing to player model");
				this.playerModel.setName(temp);
				System.out.println("Stored value : " + this.playerModel.getName());
				ok = true;
			}
			/*catch (ValidationException err) {
				System.out.println("Validation Exception : " + err.getMessage());
			}*/
			catch (Exception err) {
				System.out.println("Validation Exception : " + err.getMessage());
			}
		} while (!ok);
	}

	public void	createPlayer(PlayerModel playerModel) {
		this.playerModel = playerModel;
		this.setName();
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
