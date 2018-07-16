/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.models;

import com.swingy.app.artifacts.Artifacts;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class PlayerModel {
	private int				rec;
	@NotNull(message="Hero name cannot be blank")
	@Length(max=30, min=5, message="Minimum length of champ = 5 Max 30")
	private String			name;
	@NotNull(message= "Player class cannot be blank")
	private String			pClass;
	@Digits(integer=3, fraction=0, message="The value of level cannot be more than 3 digits")
	@Min(value=0, message="Min value of level cannot be less than 0")
	@Max(value=100, message="Max value of level cannot be more than 100")
	private int				level;
	@Digits(integer=5, fraction=0, message="The value of experience cannot be more than 5 digits")
	@Min(value=0, message="Min value of experience cannot be less than 0")
	@Max(value=100000, message="Max value of experience cannot be more than 10000")
	private int				experience;
	@Digits(integer=3, fraction=0, message="The value of attack cannot be more than 3 digits")
	@Min(value=0, message="Min value of attack cannot be less than 0")
	@Max(value=100, message="Max value attack cannot be more than 100")
	private int				attack;
	@Digits(integer=3, fraction=0, message="The value of defence cannot be more than 3 digits")
	@Min(value=0, message="Min value of defence cannot be less than 0")
	@Max(value=100, message="Max value defence cannot be more than 100")
	private int				defence;
	@Digits(integer=3, fraction=0, message="The value of hitpoints cannot be more than 3 digits")
	@Min(value=0, message="Min value of hitpoints cannot be less than 0")
	@Max(value=100, message="Max value hitpoints cannot be more than 100")
	private int				hitPoints;
	private List<Artifacts>	artifacts;
	private Artifacts		currentArtifact;
	private int				y;
	private int				x;

	public PlayerModel() {
	}

	public PlayerModel(String name, String pClass) {
		this.name = name;
		this.pClass = pClass;
	}

	public PlayerModel(String name, String pclass, int level, int Experience, int Attack,
			int defence, int hitPoints) {
		this.name = name;
		this.pClass = pclass;
		this.level = level;
		this.experience = Experience;
		this.attack = Attack;
		this.defence = defence;
		this.hitPoints = hitPoints;
		artifacts = new ArrayList<Artifacts>();
		this.currentArtifact = null;
		this.y = 0;
		this.x = 0;
	}

	public int				getRec() { return (this.rec); }
	public String			getName() { return (this.name); }
	public String			getPClass() { return (this.pClass); }
	public int				getLevel() { return (this.level); }
	public int				getExperience() { return (this.experience); }
	public int				getAttack() { return (this.attack); }
	public int				getDefence() { return (this.defence); }
	public int				getHitPoints() { return (this.hitPoints); }
	public int				getX() { return (this.x); }
	public int				getY() { return (this.y); }
	public void				addArtifact(Artifacts artifact) { this.artifacts.add(artifact); }
	public void				setName(String name) { this.name = name; }
	public void				setPClass(String pClass) { this.pClass = pClass; }
	public void				setLevel(int level) { this.level = level; }
	public void				setExperience(int experience) { this.experience = experience; }
	public void				setAttack(int attack) { this.attack = attack; }
	public void				setDefence(int defence) { this.defence = defence; }
	public void				setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }
	public void				setRec(int rec) { this.rec = rec; }
	public void				setX(int x) { this.x = x; }
	public void				setY(int y) { this.y = y; }
	public void				setPosition(int y, int x) { this.y = y; this.x = x; }
}	
