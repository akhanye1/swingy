/*
 * Created by Katleho Khanye (akhanye)
 * */

package com.swingy.app;

import com.swingy.app.controllers.PlayerController;
import com.swingy.app.controllers.ArenaController;

public class Swingy {
	public static void main(String[] args) {
		PlayerController	playerController;
		ArenaController		arena;
		Display				display;

		if (args.length > 0) {
			if (args[0].equals("gui")) {
				//display = new Console();
			}
			else if (args[0].equals("console")) {
				//display = new Gui();
			}
			else {
				System.out.println("Invalid Option: gui or console");
				return ;
			}
		}
		else {
			System.out.println("Defaulting to console");
			//display = new Console();
		}
		playerController = new PlayerController();
		/*heroFactory = new HeroFactory(display);
		if (!heroFactory.chooseHero()) {
			System.out.println("No hero chosen");
			return ;
		}*/
	}
}
