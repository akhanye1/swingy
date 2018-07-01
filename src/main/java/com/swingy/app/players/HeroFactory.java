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

	public void chooseHero() {
		this.hero = display.chooseHero();
	}
}
