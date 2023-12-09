package model;

import java.io.Serial;
import java.util.*;


public class SkillCard extends Card {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	protected int energyCost;
	
	protected int blockGain;
	protected int energyGain;
	
	protected int cardsDraw;
	protected CardSource source;
	protected boolean drawSelect;
	
	protected int cardsDiscarted;
	protected boolean discardSelect;
	
	
	protected CharacterStance stance;
	

	protected Set<Buff> buffsSelf;
	protected Set<Buff> debuffEnemy;
	
	protected String nameCardToAdd;
	
	
	public SkillCard(String name, CardColor color, String description, int energyCost, int blockGain,
					 int energyGain, int cardsDraw, int cardsDiscarted ){
		
		super(name, color, CardType.Skill, description);
		this.energyCost = energyCost;
		this.blockGain = blockGain;
		this.energyGain = energyGain;
		this.cardsDraw = cardsDraw;
		this.source = CardSource.Random;
		this.drawSelect = false;
		this.cardsDiscarted = cardsDiscarted;
		this.discardSelect = false;
		this.stance = CharacterStance.None;
		this.nameCardToAdd = null;
		this.buffsSelf = new HashSet<>();
		this.debuffEnemy = new HashSet<>();
	}


	public SkillCard(String name, CardColor color, CardType type, String description, int energyCost, int blockGain,
			int energyGain, int cardsDraw, CardSource source, boolean drawSelect, int cardsDiscarted,
			boolean discardSelect, CharacterStance stance, Set<Buff> buffsSelf, Set<Buff> debuffEnemy,
			String nameCardToAdd) {
		
		super(name, color, type, description);
		this.energyCost = energyCost;
		this.blockGain = blockGain;
		this.energyGain = energyGain;
		this.cardsDraw = cardsDraw;
		this.source = source;
		this.drawSelect = drawSelect;
		this.cardsDiscarted = cardsDiscarted;
		this.discardSelect = discardSelect;
		this.stance = stance;
		this.buffsSelf = buffsSelf;
		this.debuffEnemy = debuffEnemy;
		this.nameCardToAdd = nameCardToAdd;
	}



	public int getEnergyCost() {
		return energyCost;
	}


	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}


	public int getBlockGain() {
		return blockGain;
	}


	public void setBlockGain(int blockGain) {
		this.blockGain = blockGain;
	}


	public int getEnergyGain() {
		return energyGain;
	}


	public void setEnergyGain(int energyGain) {
		this.energyGain = energyGain;
	}


	public int getCardsDraw() {
		return cardsDraw;
	}


	public void setCardsDraw(int cardsDraw) {
		this.cardsDraw = cardsDraw;
	}


	public CardSource getSource() {
		return source;
	}


	public void setSource(CardSource source) {
		this.source = source;
	}


	public boolean isDrawSelect() {
		return drawSelect;
	}


	public void setDrawSelect(boolean drawSelect) {
		this.drawSelect = drawSelect;
	}


	public int getCardsDiscarted() {
		return cardsDiscarted;
	}


	public void setCardsDiscarted(int cardsDiscarted) {
		this.cardsDiscarted = cardsDiscarted;
	}


	public boolean isDiscardSelect() {
		return discardSelect;
	}


	public void setDiscardSelect(boolean discardSelect) {
		this.discardSelect = discardSelect;
	}


	public CharacterStance getStance() {
		return stance;
	}


	public void setStance(CharacterStance stance) {
		this.stance = stance;
	}


	public Set<Buff> getBuffsSelf() {
		return buffsSelf;
	}


	public void setBuffsSelf(Set<Buff> buffsSelf) {
		this.buffsSelf = buffsSelf;
	}
	public void addBuffsSelf(Buff buff) {
		this.buffsSelf.add(buff);
	}


	public Set<Buff> getDebuffEnemy() {
		return debuffEnemy;
	}


	public void setDebuffEnemy(Set<Buff> debuffEnemy) {
		this.debuffEnemy = debuffEnemy;
	}
	public void addDebuffEnemy(Buff debuff) {
		this.debuffEnemy.add(debuff);
	}


	public String getNameCardToAdd() {
		return nameCardToAdd;
	}


	public void setNameCardToAdd(String nameCardToAdd) {
		this.nameCardToAdd = nameCardToAdd;
	}


	@Override
	public void played() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return new SkillCard(name, color, type, description, energyCost,  blockGain, energyGain, cardsDraw, source, drawSelect,cardsDiscarted, discardSelect, stance, buffsSelf,debuffEnemy, nameCardToAdd );
	}
	@Override
	public void copyStats(Card skill) {
		if (skill instanceof SkillCard) {
			SkillCard skillCard = (SkillCard) skill;
			
			this.energyCost = skillCard.energyCost;
			this.blockGain = skillCard.blockGain;
			this.energyGain = skillCard.energyGain;
			this.cardsDraw = skillCard.cardsDraw;
			this.source = skillCard.source;
			this.drawSelect = skillCard.drawSelect;
			this.cardsDiscarted = skillCard.cardsDiscarted;
			this.discardSelect = skillCard.discardSelect;
			this.stance = skillCard.stance;
			this.buffsSelf.clear();
			this.debuffEnemy.clear();
			for (Buff b : skillCard.buffsSelf) {
				this.buffsSelf.add((Buff)b.clone());
			}
			for (Buff b : skillCard.debuffEnemy) {
				this.debuffEnemy.add(b);
			}
			this.nameCardToAdd = skillCard.nameCardToAdd;
		}

	}

}
