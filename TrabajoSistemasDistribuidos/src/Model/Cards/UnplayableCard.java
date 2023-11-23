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


	@Override
	public void played() {
		// TODO Auto-generated method stub
		//On play exhaust?
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
