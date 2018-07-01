/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controller;

import java.util.Scanner;

public class Controller {
	public static int getHeroOption() {
		Scanner sc = new Scanner(System.in);
		int		optionVal;
		String	temp;

		System.out.println("1 | Create Hero");
		System.out.println("2 | Choose Saved Hero");
		System.out.print("Choice : ");
		temp = sc.nextLine();
		System.out.println("What you putin : " + temp);
		return (0);
	}
}
