/*
 * Created by Katleho Khanye (akhanye)
 * */

package com.swingy.app;

import com.swingy.app.arena.Arena;
import com.swingy.app.players.Players;
import com.swingy.app.players.Hero;
import com.swingy.app.players.Enemy;

public class Swingy {
	public static void main(String[] args) {
		Arena arena;

		if (args.length > 0) {
			if (args[0].equals("graphics")) {
				System.out.println("Graphics");
			}
			else if (args[0].equals("terminal")) {
				System.out.println("Terminal");
			}
			else {
				System.out.println("Invalid Option");
				return ;
			}
		}
		else {
			System.out.println("Defaulting to terminal");
		}
		//arena = new Arena();
	}
}
