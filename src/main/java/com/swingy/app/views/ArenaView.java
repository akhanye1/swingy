/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import com.swingy.app.controllers.ArenaController;
import com.swingy.app.models.PlayerModel;

public abstract class ArenaView {
	public abstract void showMap(char[][] map, ArenaController arenaController, PlayerModel playerModel);
	public abstract void updateMap(char[][] map);
	public abstract void prepareFight(String prepareString);
	public abstract void updateFight(String updateString);
	public abstract void showMessage(String message, boolean exitProg);
	public abstract void makeChoice(PlayerModel enemy, ArenaController arenaController);
}
