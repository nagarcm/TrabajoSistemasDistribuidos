package Model.Cards;

import java.io.Serializable;
import java.util.*;

import Model.basics.*;

public abstract class Card implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static HashMap<String, Integer> NumberOfCopy = new HashMap<String, Integer>();
	public static Set<Card> allCards = new HashSet<Card>();
	
	protected String name; //ID of any type of card
	protected int numberCopy; //name + numCopy ID of the instance of card
	protected CardColor color;
	protected CardType type;
	protected String description;
	
	
	protected Set<CardMod> mods;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CardColor getColor() {
		return color;
	}
	public void setColor(CardColor color) {
		this.color = color;
	}
	public CardType getCard() {
		return this.type;
	}
	public void setCard(CardType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberCopy() {
		return this.numberCopy;
	}
	
	/***********************************************************************************************************************/
	public Card(String name, CardColor color, CardType type, String description) {
		this.name = name;
		this.color = color;
		this.type =  type;
		this.description = description;
		if(Card.NumberOfCopy.containsKey(name)) {
			this.numberCopy = Card.NumberOfCopy.get(name) + 1;
			Card.NumberOfCopy.replace(name, this.numberCopy);
		}else {
			Card.NumberOfCopy.put(name, 1);
			this.numberCopy =1;
		}
		
		this.mods = new HashSet<>();
	}
	public Card() { }

	@Override
	public boolean equals(Object c) {
		if (c instanceof Card) {
		return this.name.equals(( (Card)c).name) && this.numberCopy ==((Card)c).numberCopy;
		} else {
			return false;
		}
	}

	
	public Set<CardMod> getMods() {
		return mods;
	}
	public void addMod(CardMod e) {
		this.mods.add(e);
	}
	public void removeMod(CardMod e) {
		this.mods.remove(e);
	}
	public boolean haveMod(CardMod e) {
		return this.mods.contains(e);
	}

	public void reset() {
		
		CardManager.resetCard(this);
	}

	@Override
	public abstract Object clone();
	public abstract void played();
	public abstract void copyStats(Card c);
	
	
	
	
	//end of the class
}
