/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.display;

import com.swingy.app.players.Players;
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

	public Players	chooseHero() {
		int	option;
		System.out.println("Welcome");
		System.out.println("=======");
		if ((option = Controller.getHeroOption()) == 1) {
		}
		else if (option == 2) {
		}
		return (null);
	}
}
