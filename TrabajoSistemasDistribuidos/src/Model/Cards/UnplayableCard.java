package Model.Cards;

import Model.Effects.Effect;
import Model.basics.CardColor;
import Model.basics.CardType;

public class UnplayableCard extends Card {
	
	private Effect action;
	

	public UnplayableCard(String name, CardColor color, CardType card, String description) {
		super(name, color, card, description);
		// TODO Auto-generated constructor stub
	}

}
