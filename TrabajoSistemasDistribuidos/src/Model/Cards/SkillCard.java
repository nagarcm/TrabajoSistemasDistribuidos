package Model.Cards;

import java.util.*;
import Model.basics.*;

import Model.Buffs.Buff;

import Model.Buffs.Buff;

public class SkillCard extends Card {
	
	private int energyCost;
	private int blockGain;
	
	private int cardsDraw;
	private CardSource source;
	private boolean drawSelect;
	
	private int cardsDiscarted;
	private boolean discardSelect;
	
	
	private CharacterStance stance;
	

	private List<Buff> buffsSelf;
	private List<Buff> debuffEnemy;
	
	
	
	
	@Override
	public void played() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
