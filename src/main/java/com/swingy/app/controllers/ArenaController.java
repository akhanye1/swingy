/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.swingy.app.views.Display;
import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.PlayerViewConsole;
import com.swingy.app.views.PlayerViewGui;

public class ArenaController {
	private PlayerController		hero;
	private PlayerModel				playerModel;
	private List<PlayerController>	enemies;
	private static int				collisionCounter;
	private static int				enemyCount;
	private Display					display;
	private int						width;
	private int						height;

	public ArenaController(PlayerController controller, Display pDisplay) {
		hero = controller;
		enemies = new ArrayList<PlayerController>();

		this.playerModel = controller.getPlayer();
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
		int sizeMap;
		int level;

		level = (this.hero.getPlayer().getLevel() == 0) ? 1 : this.hero.getPlayer().getLevel();
		System.out.println("Level given  : " + level);
		sizeMap = (((level - 1) * 5) + 10)  - (level % 2);
		this.width = sizeMap;
		this.height = sizeMap;
		System.out.println("Started <" + this.hero.getPlayer().getName()+">");
		System.out.println("Size of map :: " + sizeMap);
		this.playerModel.setX(sizeMap / 2);
		this.playerModel.setY(sizeMap / 2);
		System.out.println("Start position : " + this.playerModel.getX());
		this.createEnemies();
	}

	private String			getRandomName() {
		Random rand = new Random();
		String names[] = {
			"Joker",
			"Thenos",
			"Skippy",
			"Freezer",
			"Blink",
			"Walker"
		};
		return (name[rand.nextInt(6)]);
	}

	private String			getClassName() {
		Random rand = new Random();
		String pClasses[] = {
			"Knight",
			"Elf",
			"Viking"
		};
		return (pClasses[rand.nextInt(3)]);
	}

	private void			setPlayerStats(PlayerModel tempPlayer) {
		Random rand = new Random();

		tempPlayer.setLevel(rand.nextInt(playerModel.getLevel() + 2));
		tempPlayer.setHitPoints(100);
		switch (tempPlayer.getPClass()) {
			case "Knight":
				tempPlayer.setExperience(150);
				tempPlayer.setAttack(10);
				tempPlayer.setDefence(7);
				break ;
			case "Elf":
				tempPlayer.setExperience(100);
				tempPlayer.setAttack(3);
				tempPlayer.setDefenece(3);
				break ;
			case "Viking":
				tempPlayer.setExperience(200);
				tempPlayer.setAttack(15);
				tempPlayer.setDefence(15);
				break ;
		}
	}

	private void			createEnemies() {
		int	numEnemies;
		Random rand = new Random();

		numEnemies = width;
		for (int x = 0; x < numEnemies; x++) {
			PlayerModel tempEnemy = new PlayerModel(getRandomName(), getClassName());
			setPlayerStats(tempEnemy);
		}
	}

	public void				registerEnemy(PlayerController enemy) {
		this.enemies.add(enemy);
		enemyCount++;
	}
}
