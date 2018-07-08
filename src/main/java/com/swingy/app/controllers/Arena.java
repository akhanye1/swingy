/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import java.util.ArrayList;
import java.util.List;
import com.swingy.app.views.Display;

public class Arena {
	private PlayerController		hero;
	private List<PlayerController>	enemies;
	private static int				collisionCounter;
	private static int				enemyCount;
	private Display					display;
	private int						width;
	private int						height;


	public Arena() {
		enemies = new ArrayList();
		enemyCount = 0;
	}

	public void				checkCollisions() {
	}

	public void				movePlayers() {
	}

	public void				registerHero(PlayerController hero) {
		this.hero = hero;
	}

	public void				registerEnemy(PlayerController enemy) {
		this.enemies.add(enemy);
		enemyCount++;
	}
}
