/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.models;

import com.swingy.app.artifacts.Artifacts;
import java.util.ArrayList;
import java.util.List;

public abstract class PlayerModel {
	private String			name;
	private String			pClass;
	private int				level;
	private int				experience;
	private int				health;
	private int				attack;
	private int				defence;
	private int				hitPoints;
	private List<Artifacts>	artifacts;
	private Artifacts		currentArtifact;
	private int				y;
	private int				x;

	PlayerModel(String name, String pclass, int level, int Experience, int Attack,
			int defence, int hitPoints) {
		this.name = name;
		this.pClass = pclass;
		this.level = level;
		this.experience = Experience;
		this.health = 100;
		this.attack = Attack;
		this.defence = defence;
		this.hitPoints = hitPoints;
		artifacts = new ArrayList<Artifacts>();
		this.currentArtifact = null;
		this.y = 0;
		this.x = 0;
	}

	public String			getName() { return (this.name); }
	public String			getPClass() { return (this.pClass); }
	public int				getLevel() { return (this.level); }
	public int				getExperience() { return (this.experience); }
	public int				getHealth() { return (this.health); }
	public int				getAttack() { return (this.attack); }
	public int				getDefence() { return (this.defence); }
	public int				getHitPoints() { return (this.hitPoints); }
	public int				getX() { return (this.x); }
	public int				getY() { return (this.y); }
	public void				addArtifact(Artifacts artifact) { this.artifacts.add(artifact); }
}	
