package Model.Cards;

import Model.Effects.Effect;
import Model.Enums.CardColor;
import Model.Enums.CardType;

public class UnplayableCard extends Card {
	
	private Effect action;
	

	public UnplayableCard(String name, CardColor color, CardType card, String description) {
		super(name, color, card, description);
		// TODO Auto-generated constructor stub
	}

}
