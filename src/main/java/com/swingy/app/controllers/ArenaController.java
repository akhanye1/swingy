/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import java.util.ArrayList;
import java.util.List;
import com.swingy.app.views.Display;
import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.PlayerViewConsole;
import com.swingy.app.views.PlayerViewGui;

public class ArenaController {
	private PlayerController		hero;
	private List<PlayerController>	enemies;
	private static int				collisionCounter;
	private static int				enemyCount;
	private Display					display;
	private int						width;
	private int						height;

	public ArenaController(PlayerController controller, Display pDisplay) {
		hero = controller;
		enemies = new ArrayList<PlayerController>();
		if (pDisplay instanceof PlayerViewConsole) {
			System.out.println("Console Arena");
		}
		else {
			System.out.println("Gui Arena");
		}
		enemyCount = 0;
	}

	public void				checkCollisions() {
	}

	public void				movePlayers() {
	}

	public void				registerHero(PlayerController hero) {
		this.hero = hero;
	}

	public void				initPlay() {
		System.out.println("Started<>");
		/*while (true) {
			System.out.println("Name : " + this.hero.getPlayer().getName());
		}*/
	}

	public void				registerEnemy(PlayerController enemy) {
		this.enemies.add(enemy);
		enemyCount++;
	}
}
