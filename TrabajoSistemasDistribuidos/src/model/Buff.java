package model;

//import Model.Characters.GameCharacter;

import java.io.Serializable;

public class Buff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3060950808585650399L;//Se guardo con esto y no quiero volver a hacer toda la base de datos
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
	@Override
	public String toString() {
		return this.name + " for "+ this.turnsLeft+ " turns.";
	}
	
}
