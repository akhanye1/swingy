/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import com.swingy.app.views.Display;
import com.swingy.app.models.PlayerModel;
import com.swingy.app.views.PlayerViewConsole;
import com.swingy.app.views.PlayerViewGui;
import com.swingy.app.views.ArenaView;
import com.swingy.app.views.ArenaViewConsole;


public class ArenaController {
	private PlayerController		hero;
	private PlayerModel				playerModel;
	private PlayerModel				enemyModel;
	private List<PlayerModel>	enemies;
	private static int				collisionCounter;
	private static int				enemyCount;
	private Display					display;
	private int						width;
	private int						height;
	private char					arenaArea[][];
	private ArenaView				arenaView;
	private PlayerController		fightController;
	private int						currentLevel;

	public ArenaController(PlayerController controller, Display pDisplay) {
		hero = controller;
		enemies = new ArrayList<PlayerModel>();

		this.playerModel = controller.getPlayer();
		if (pDisplay instanceof PlayerViewConsole) {
			System.out.println("Console Arena");
			display = new ArenaViewConsole();
			arenaView = (ArenaView)display;
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
		currentLevel = this.hero.getPlayer().getLevel();
	}

	public void				initPlay() {
		int sizeMap;
		int level;

		level = (this.hero.getPlayer().getLevel() == 0) ? 1 : this.hero.getPlayer().getLevel();
		sizeMap = (((level - 1) * 5) + 10)  - (level % 2);
		this.width = sizeMap;
		this.height = sizeMap;
		this.playerModel.setX(sizeMap / 2);
		this.playerModel.setY(sizeMap / 2);
		this.createEnemies();
		this.setArena();
		this.arenaView.showMap(this.arenaArea, this, this.playerModel);
	}

	private void			setArena() {
		int		wholeArea;

		wholeArea = this.width + 2;
		arenaArea = new char[wholeArea][wholeArea];
		for (int y = 0; y < wholeArea; y++) {
			for (int x = 0; x < wholeArea; x++) {
				arenaArea[y][x] = ' ';
			}
		}
		arenaArea[this.playerModel.getY() + 1][this.playerModel.getX() + 1] = '@';
		for (PlayerModel tempEnemy : this.enemies) {
			arenaArea[tempEnemy.getY() + 1][tempEnemy.getX() + 1] = '*';
		}
		for (int y = 0; y < wholeArea; y++) {
			arenaArea[y][0] = '#';
			arenaArea[y][wholeArea - 1] = '#';
			if (y == 0 || (y == wholeArea - 1)) {
				for (int x = 1; x < (wholeArea - 1); x++) {
					arenaArea[y][x] = '#';
				}
			}
		}
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
		return (names[rand.nextInt(6)]);
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
				tempPlayer.setExperience(250);
				tempPlayer.setAttack(15);
				tempPlayer.setDefence(10);
				break ;
			case "Elf":
				tempPlayer.setExperience(150);
				tempPlayer.setAttack(5);
				tempPlayer.setDefence(5);
				break ;
			case "Viking":
				tempPlayer.setExperience(300);
				tempPlayer.setAttack(25);
				tempPlayer.setDefence(25);
				break ;
		}
	}

	private void			positionPlayerRandomly(PlayerModel tempEnemy) {
		Random	rand = new Random();
		boolean	conflict;
		int		i;
		int		maxEnemies;

		do {
			conflict = false;
			i = -1;
			maxEnemies = this.enemies.size();
			tempEnemy.setPosition(rand.nextInt(this.width), rand.nextInt(this.height));
			conflict = ((tempEnemy.getX() == this.playerModel.getX()) &&
					(tempEnemy.getY() == this.playerModel.getY()));
			while (!conflict && ++i <  maxEnemies) {
				conflict = ((tempEnemy.getX() == this.enemies.get(i).getX()) &&
						tempEnemy.getY() == this.enemies.get(i).getY());
			}
		} while (conflict);
	}

	private void			createEnemies() {
		int	numEnemies;
		Random rand = new Random();

		numEnemies = width;
		for (int x = 0; x < numEnemies; x++) {
			PlayerModel tempEnemy = new PlayerModel(getRandomName(), getClassName());
			setPlayerStats(tempEnemy);
			positionPlayerRandomly(tempEnemy);
			this.registerEnemy(tempEnemy);
		}
	}

	public void				registerEnemy(PlayerModel enemy) {
		this.enemies.add(enemy);
		enemyCount++;
	}

	public void				setSelection(int choice) {
		switch (choice) {
			case 1:
				this.playerModel.setY(this.playerModel.getY() - 1);
				break ;
			case 2:
				this.playerModel.setX(this.playerModel.getX() + 1);
				break ;
			case 3:
				this.playerModel.setY(this.playerModel.getY() + 1);
				break ;
			case 4:
				this.playerModel.setX(this.playerModel.getX() - 1);
				break ;
			case 10:
				if (display instanceof PlayerViewConsole) {
				}
				break ;
		}
		if (choice != 0) {
			this.checkPlayer();
		}
	}

	private void		simulateFight(PlayerModel fightEnemy) {
		String 				prepareFight;
		PlayerModel			player1;
		PlayerModel 		player2;

		enemyModel = fightEnemy;
		prepareFight = this.playerModel.getName() + " vs " +
			fightEnemy.getName() + "\n";
		prepareFight += "======================================";
		this.arenaView.prepareFight(prepareFight);
		fightController = new PlayerController(this.playerModel, fightEnemy, this.arenaView, this);
		fightController.simulateFight();
	}

	public void			fightOver() {
		if (playerModel.getHitPoints() > 0) {
			fightController.addExperience(this.playerModel, enemyModel);
		}
		if (enemyModel.getHitPoints() <= 0) {
			for (Iterator<PlayerModel> iter = enemies.listIterator(); iter.hasNext();) {
				PlayerModel tempEnemy = iter.next();
				if (enemyModel == tempEnemy) {
					iter.remove();
				}
			}
		}
		if (this.playerModel.getHitPoints() > 0) {
			this.arenaView.showMessage(this.playerModel.getName() + " won the fight ");
			if (currentLevel != this.playerModel.getLevel()) {
				currentLevel = this.playerModel.getLevel();
				this.initPlay();
				return ;
			}
			this.setArena();
			this.arenaView.updateMap(arenaArea);
			return ;
		}
	}

	private void		checkPlayer() {
		boolean 	collision;
		PlayerModel	fightEnemy;

		fightEnemy = null;
		collision = false;
		for (PlayerModel tempEnemy : this.enemies) {
			if ((tempEnemy.getX() == this.playerModel.getX()) &&
					(tempEnemy.getY() == this.playerModel.getY())) {
				collision = true;
				fightEnemy = tempEnemy;
				break ;
			}
		}
		if (collision && fightEnemy != null) {
			//this.arenaView.makeChoice();
			simulateFight(fightEnemy);
			return ;
		}
		if (this.playerModel.getX() == -1 || this.playerModel.getX() == (this.width) ||
				this.playerModel.getY() == -1 || this.playerModel.getY() == (this.width)) {
			this.arenaView.showMessage(this.playerModel.getName() + " WON");
			return ;
		}
		this.setArena();
		this.arenaView.updateMap(arenaArea);
	}
}
