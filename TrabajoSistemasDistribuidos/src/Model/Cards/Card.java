package Model.Cards;

import java.util.HashMap;

import Model.basics.CardColor;
import Model.basics.CardType;

public class Card {
	public static HashMap<String, Integer> NumberOfCopy = new HashMap<String, Integer>();
	private String name; //ID de la carta
	private int numberCopy;
	private CardColor color;
	private CardType type;
	private String description;
	
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
	
	//fin de la clase
}
