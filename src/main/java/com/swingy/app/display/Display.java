/*
 * Created by Katleho Khanye (akhanye)
 * */

package com.swingy.app.display;

import com.swingy.app.arena.Arena;

public abstract class Display {
	private String	currentViewType;
	private	Arena	arena;

	public String	getViewType() { return (this.currentViewType); }
	public void		setViewType(String view) { this.currentViewType = view; }
	
	public abstract void		updateFight(String description);

	public abstract void		showHeros();

	public abstract void		updatePositions();
}
