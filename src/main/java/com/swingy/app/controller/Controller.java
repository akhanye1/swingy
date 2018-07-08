/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controller;

import java.util.Scanner;
import com.swingy.app.players.Players;
import com.swingy.app.players.Hero;

public class Controller {
	public static int getHeroOption() {
		Scanner sc = new Scanner(System.in);
		String	temp;

		if (((temp = sc.nextLine()).equals("1")) || (temp.equals("2")) ||
				(temp.equals("3"))) {
			return (Integer.parseInt(temp));
		}
		else {
			System.out.println("Invalid option : Please try again");
		}
		return (0);
	}
}
