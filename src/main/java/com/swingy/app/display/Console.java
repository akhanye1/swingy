/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.display;

import com.swingy.app.controller.Controller;

public class Console extends Display {
	public Console() {
		super("console");
	}

	public void		updateFight(String description) {
	}

	public void		showHeros() {
	}

	public void		updatePositions() {
	}

	public int		chooseHero() {
		int	option;

		System.out.println("Welcome");
		System.out.println("=======");
		do {
			System.out.println("1 | Create Hero");
			System.out.println("2 | Choose Saved Hero");
			System.out.println("3 | Exit");
			System.out.print("Choice : ");
			option = Controller.getHeroOption();
			System.out.println("");
		} while (option < 1 || option > 3);
		return (option);
	}
}
