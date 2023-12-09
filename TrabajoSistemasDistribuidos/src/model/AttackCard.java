
package model;

import java.util.*;


public class AttackCard extends Card {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int energyCost;

	protected int baseDmg;
	protected int strScaling;
	protected int blockScaling;
	protected int numHits;

	protected int blockGain;
	protected int cardsDraw;
	protected int cardsDiscarted;
	protected CharacterStance stance;

	protected Set<Buff> buffs;
	protected Set<Buff> debuffs;
	
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
	public CharacterStance getStance() {return stance;}
	public void setStance(CharacterStance stance) {this.stance = stance;}

	public Set<Buff> getBuffs() {
		return buffs;
	}

	public void setBuffs(Set<Buff> buffs) {
		this.buffs = buffs;
	}
	public void addBuffs(Buff buffs) {
		this.buffs.add(buffs);
	}
	
	public Set<Buff> getDebuffs() {
		return debuffs;
	}

	public void setDebuffs(Set<Buff> debuffs) {
		this.debuffs = debuffs;
	}
	public void addDebuffs(Buff debuffs) {
		this.debuffs.add(debuffs);
	}

	public AttackCard(String name, CardColor color, String description, int energyCost, int baseDmg, int strScaling,
					  int blockScaling, int numHits, int blockGain, int cardsDraw, int cardsDiscarted, CharacterStance stance, Set<Buff> buffs, Set<Buff> debuffs) {
		super(name, color, CardType.Attack, description);
		this.energyCost = energyCost;
		this.baseDmg = baseDmg;
		this.strScaling = strScaling;
		this.blockScaling = blockScaling;
		this.numHits = numHits;
		this.blockGain = blockGain;
		this.cardsDraw = cardsDraw;
		this.cardsDiscarted = cardsDiscarted;
		this.stance = stance;
		this.buffs = buffs;
		this.debuffs = debuffs;
	}
	public int damage(int currentStr, int currentBlock) {
		return this.baseDmg + this.blockScaling * currentBlock + this.strScaling * currentStr ;
	}
	@Override
	public void played() {
		// TODO Auto-generated method stub
		
	}

	public AttackCard(String name, CardColor color, String description, int energyCost, int baseDmg,
			int strScaling, int blockScaling) {
		super(name, color, CardType.Attack, description);
		
		this.energyCost = energyCost;
		this.baseDmg = baseDmg;
		this.strScaling = strScaling;
		this.blockScaling = blockScaling;
		
		this.numHits = 1;
		this.blockGain = 0;
		this.cardsDraw = 0;
		this.cardsDiscarted = 0;
		this.buffs = new HashSet<>();
		this.debuffs = new HashSet<>();
		
	}
	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return new AttackCard(name,color,description, energyCost, baseDmg, strScaling, blockScaling, numHits, blockGain, cardsDraw, cardsDiscarted, stance,buffs, debuffs);
		
	}
	@Override
	public void copyStats(Card c) {
		// TODO Auto-generated method stub
		if (c instanceof AttackCard) {
			AttackCard attack = (AttackCard) c;
			this.energyCost = attack.energyCost;
			this.baseDmg = attack.baseDmg;
			this.strScaling = attack.strScaling;
			this.blockScaling = attack.blockScaling;
			
			this.numHits = attack.numHits;
			this.blockGain = attack.blockGain;
			this.cardsDraw = attack.cardsDraw;
			this.cardsDiscarted = attack.cardsDiscarted;
			this.buffs.clear();
			this.debuffs.clear();
			for (Buff b: attack.buffs) {
				this.buffs.add(b);
			}
			for (Buff b : attack.debuffs) {
				this.debuffs.add(b);			
			}

		}
	}
	
	

}
