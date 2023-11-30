package Model.Buffs;

//import Model.Characters.GameCharacter;

public class Buff {
	private String name;
	private int turnsLeft;
	private int value;

	public Buff(String name) {
		super();
		this.name = name;
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
