/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.players;	

import com.swingy.app.arena.Arena;
import com.swingy.app.display.Display;

public class HeroFactory {
	private Arena	arena;
	private Display display;
	private Players	hero;

	public HeroFactory (Display display) {
		hero = null;
		this.display = display;
	}

	public boolean chooseHero() {
		int option;

		if ((option = display.chooseHero()) == 3) {
			return (false);
		}
		else if (option == 1) {
			this.createHero();
		}
		else if (option == 2) {
			this.selectHero();
		}
		return (true);
	}

	private boolean selectHero() {
		return (true);
	}

	private boolean	createHero() {	
		return (true);
	}
}
