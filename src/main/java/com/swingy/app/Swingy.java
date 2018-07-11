/*
 * Author by Katleho Khanye (akhanye)
 * */

package com.swingy.app;

import com.swingy.app.controllers.PlayerController;
import com.swingy.app.controllers.ArenaController;
import com.swingy.app.views.Display;
import com.swingy.app.views.PlayerViewConsole;
import com.swingy.app.views.PlayerViewGui;
import com.swingy.app.views.PlayerView;

public class Swingy {
	
	public static void		main(String[] args) {
		PlayerController	playerController;
		ArenaController		arena;
		Display				display;
		int					choice;

		display = null;
		if (args.length > 0) {
			if (args[0].equals("gui")) {
				display = new PlayerViewGui();
			}
			else if (args[0].equals("console")) {
				display = new PlayerViewConsole();
			}
			else {
				System.out.println("Invalid Option: gui or console");
				return ;
			}
		}
		else {
			System.out.println("Defaulting to console");
			display = new PlayerViewConsole();
		}
		if (display == null) {
			System.out.println("Display Error");
			return ;
		}
		playerController = new PlayerController(display);
		playerController.choosePlayer();
	}
}
