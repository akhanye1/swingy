/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import java.util.ArrayList;
import java.util.List;
import com.swingy.app.views.Display;
import com.swingy.app.models.PlayerModel;

public class ArenaController {
	private PlayerController		hero;
	private List<PlayerController>	enemies;
	private static int				collisionCounter;
	private static int				enemyCount;
	private Display					display;
	private int						width;
	private int						height;


	public ArenaController(PlayerController controller) {
		hero = controller;
		enemies = new ArrayList<PlayerController>();
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
		while (true) {
			System.out.println("Name : " + this.hero.getPlayer().getName());
		}
	}

	public void				registerEnemy(PlayerController enemy) {
		this.enemies.add(enemy);
		enemyCount++;
	}
}
