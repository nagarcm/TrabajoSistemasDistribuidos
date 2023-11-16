package Model.Cards;

import Model.Enums.CardColor;
import Model.Enums.CardType;

public class Card {
	private String name; //ID de la carta
	private CardColor color;
	private CardType Card;
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
		return Card;
	}
	public void setCard(CardType card) {
		Card = card;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/***********************************************************************************************************************/
	public Card(String name, CardColor color, CardType card, String description) {
		this.name = name;
		this.color = color;
		Card = card;
		this.description = description;
	}
	
	
	
	
	
	
	
	//fin de la clase
}
