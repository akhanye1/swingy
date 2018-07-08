/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import java.util.Scanner;

public class PlayerViewConsole extends PlayerView {
	public int	choosePlayer() {
		Scanner sc = new Scanner(System.in);
		String	temp;

		System.out.println("WELCOME");
		System.out.println("=======");
		do {
			System.out.println("1 > Create player");
			System.out.println("2 > Choose save player");
			System.out.println("3 > Exit");
			temp = sc.nextLine();
			if (!temp.equals("1") || !temp.equals("2") || !temp.equals("3"))
				System.out.println("\nInvalid choice, choose between 1 - 3");
		} while (!temp.equals("1") || !temp.equals("2") || !temp.equals("3"));
		return (Integer.parseInt(temp));
	}
}
