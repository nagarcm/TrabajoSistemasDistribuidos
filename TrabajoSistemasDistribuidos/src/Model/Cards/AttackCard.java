package Model.Cards;

import java.util.*;
import Model.basics.*;

import Model.Buffs.Buff;


public class AttackCard extends Card {
	private int energyCost;
	
	private int baseDmg;
	private int strScaling;
	private int blockScaling;
	private int numHits;
	
	private int blockGain;
	private int cardsDraw;
	private int cardsDiscarted;
	private List<Buff> buffs;
	
	public int getBaseDmg() {
		return this.baseDmg;
	}
	public void setBaseDmg(int baseDmg) {
		this.baseDmg = baseDmg;
	}
	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public int getStrScaling() {
		return strScaling;
	}

	public void setStrScaling(int strScaling) {
		this.strScaling = strScaling;
	}

	public int getBlockScaling() {
		return blockScaling;
	}

	public void setBlockScaling(int blockScaling) {
		this.blockScaling = blockScaling;
	}

	public int getNumHits() {
		return numHits;
	}

	public void setNumHits(int numHits) {
		this.numHits = numHits;
	}

	public int getBlockGain() {
		return blockGain;
	}

	public void setBlockGain(int blockGain) {
		this.blockGain = blockGain;
	}

	public int getCardsDiscarted() {
		return cardsDiscarted;
	}

	public void setCardsDiscarted(int cardsDiscarted) {
		this.cardsDiscarted = cardsDiscarted;
	}
	public int getCardsDraw() {
		return cardsDraw;
	}

	public void setCardsDraw(int cardsDraw) {
		this.cardsDraw = cardsDraw;
	}

	public List<Buff> getBuffs() {
		return buffs;
	}

	public void setBuffs(List<Buff> buffs) {
		this.buffs = buffs;
	}

	public AttackCard(String name, CardColor color, CardType type, String description, int energyCost, int baseDmg, int strScaling,
			int blockScaling, int numHits, int blockGain, int cardsDraw, int cardsDiscarted, List<Buff> buffs) {
		super(name, color, type, description);
		this.energyCost = energyCost;
		this.baseDmg = baseDmg;
		this.strScaling = strScaling;
		this.blockScaling = blockScaling;
		this.numHits = numHits;
		this.blockGain = blockGain;
		this.cardsDraw = cardsDraw;
		this.cardsDiscarted = cardsDiscarted;
		this.buffs = buffs;
	}
	public int damage(int currentStr, int currentBlock) {
		return this.baseDmg + this.blockScaling * currentBlock + this.strScaling * currentStr ;
	}
	@Override
	public void played() {
		// TODO Auto-generated method stub
		
	}
	@Override 
	public void reset() {
		
	}
	public AttackCard(String name, CardColor color, CardType type, String description, int energyCost, int baseDmg,
			int strScaling, int blockScaling) {
		super(name, color, type, description);
		this.energyCost = energyCost;
		this.baseDmg = baseDmg;
		this.strScaling = strScaling;
		this.blockScaling = blockScaling;
		
		this.numHits = 1;
		this.blockGain = 0;
		this.cardsDraw = 0;
		this.cardsDiscarted = 0;
		this.buffs = new ArrayList<>();
		
	}
	
	

}
