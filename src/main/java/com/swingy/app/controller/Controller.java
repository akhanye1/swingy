/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controller;

import java.util.Scanner;

public class Controller {
	public static int getHeroOption() {
		Scanner sc = new Scanner(System.in);
		String	temp;

		if (((temp = sc.nextLine()).equals("1")) || (temp.equals("2")) ||
				(temp.equals("3"))) {
			if (temp.equals("1"))
				return (1);
			else if (temp.equals("2"))
				return (2);
			else
				return (3);
		}
		else {
			System.out.println("Invalid option : Please try again");
		}
		return (0);
	}
}
