package model;

//import Model.Characters.GameCharacter;

import java.io.Serializable;

public class Buff implements Serializable {
	private String name;
	private int turnsLeft;
	private int value;

	public Buff(String name, int turnsLeft) {
		super();
		this.name = name;
		this.turnsLeft = turnsLeft;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTurnsLeft() {
		return turnsLeft;
	}
	public void setTurnsLeft(int turnsLeft) {
		this.turnsLeft = turnsLeft;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Buff(String name, int turnsLeft, int value) {
		super();
		this.name = name;
		this.turnsLeft = turnsLeft;
		this.value = value;
	}

	@Override
	public Object clone() {
		return new Buff(name, turnsLeft, value);
	}
	
}
