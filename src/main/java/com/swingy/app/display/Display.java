/*
 * Created by Katleho Khanye (akhanye)
 * */

package com.swingy.app.display;

import com.swingy.app.arena.Arena;
import com.swingy.app.players.Players;
import com.swingy.app.players.Enemy;

public abstract class Display {
	private String	currentViewType;
	private	Arena	arena;
	private Players	hero;

	public Display(String viewType) {
		this.currentViewType = viewType;
		this.arena = null;
	}

	public String	getViewType() { return (this.currentViewType); }
	public void		setViewType(String view) { this.currentViewType = view; }
	
	public abstract void		updateFight(String description);

	public abstract void		showHeros();

	public abstract void		updatePositions();

	public abstract int			chooseHero();
}
