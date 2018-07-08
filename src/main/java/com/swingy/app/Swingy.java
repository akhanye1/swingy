/*
 * Author by Katleho Khanye (akhanye)
 * */

package com.swingy.app;

import com.swingy.app.arena.Arena;
import com.swingy.app.players.Players;
import com.swingy.app.players.Hero;
import com.swingy.app.players.Enemy;
import com.swingy.app.players.HeroFactory;
import com.swingy.app.players.EnemyFactory;
import com.swingy.app.display.Display;
import com.swingy.app.display.Console;
import com.swingy.app.display.Gui;

public class Swingy {
	public static void main(String[] args) {
		Arena 			arena;
		Display			display;
		HeroFactory		heroFactory;

		if (args.length > 0) {
			if (args[0].equals("gui")) {
				display = new Console();
			}
			else if (args[0].equals("console")) {
				display = new Gui();
			}
			else {
				System.out.println("Invalid Option: gui or console");
				return ;
			}
		}
		else {
			display = new Console();
		}
		heroFactory = new HeroFactory(display);
		if (!heroFactory.chooseHero()) {
			System.out.println("No hero chosen");
			return ;
		}
	}
}
