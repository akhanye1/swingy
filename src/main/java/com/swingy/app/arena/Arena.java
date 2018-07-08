/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.arena;

import com.swingy.app.players.Players;
import com.swingy.app.players.Enemy;
import com.swingy.app.players.Hero;
import com.swingy.app.display.Display;
import java.util.ArrayList;
import java.util.List;

public class Arena {
	private Players			hero;
	private List<Players>	enemies;
	private static int		collisionCounter;
	private static int		enemyCount;
	private Display			display;
	private int				width;
	private int				height;


	public Arena() {
		enemies = new ArrayList<Players>();
		enemyCount = 0;
	}

	public void				checkCollisions() {
	}

	public void				movePlayers() {
	}

	public void				registerHero(Hero hero) {
		this.hero = hero;
	}

	public void				registerEnemy(Enemy enemy) {
		this.enemies.add(enemy);
		enemyCount++;
	}
}
