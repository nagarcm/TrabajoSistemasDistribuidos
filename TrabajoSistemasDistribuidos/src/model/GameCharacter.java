package model;

import java.util.*;

public class GameCharacter implements ITurnReset {
	private String name;
	private int hp;
	private int block;
	private int energy;
	
	private List<Relic> relics;
	private CharacterStance stance;
	private List<Buff> buffs;
	private Card lastCardPlayed;

	//
	private CardContainer deck;
	private CardContainer hand;
	private CardContainer drawPile;
	private CardContainer discardPile;
	private CardContainer exhaustPile;
	
	private TurnData turn;
	private CombatData combat;
	
	
	public GameCharacter(String name, int hP) {
		super();
		this.name = name;
		this.hp = hP;
		this.block = 0;
		this.relics =  new ArrayList<>();
		this.stance = CharacterStance.Neutral;
		this.buffs = new ArrayList<>();
		this.lastCardPlayed = null;



		this.deck = new CardContainer();
		this.hand = new CardContainer();
		this.drawPile = new CardContainer();
		this.discardPile = new CardContainer();
		this.exhaustPile = new CardContainer();
		this.drawPile.setAuxiliarCardSource(this.discardPile);
		this.hand.setAuxiliarCardDiscard(this.discardPile);
		this.hand.setLimitCardAmount(10) ;
		
		this.turn = new TurnData();
		this.combat = new CombatData();
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp ==0) {
			//End Game or send a signal
		}
	}


	public int getBlock() {
		return block;
	}


	public void setBlock(int block) {
		this.block = block;
		if (this.block == 0) {
			//Bypassed block
		}
	}
	public void addBlock(int block){
		this.block += block;
	}
	public int getEnergy() {
		return this.energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public void addEnergy(int energyInc){
		this.energy += energyInc;
	}
	public void consumeEnergy(int energyConsume){
		this.energy -= energyConsume;
	}

	public List<Relic> getRelics() {
		return relics;
	}


	public void setRelics(List<Relic> relics) {
		this.relics = relics;
	}

	public void addRelic(Relic r) {
		this.relics.add(r);
	}

	public CharacterStance getStance() {
		return stance;
	}
	
	public void setStance(CharacterStance stance) {
		this.stance = stance;
	}


	public Card getLastCardPlayed() {
		return lastCardPlayed;
	}


	public void setLastCardPlayed(Card lastCardPlayed) {
		this.lastCardPlayed = lastCardPlayed;
	}


	public CardContainer getDeck() {
		return deck;
	}


	public void setDeck(CardContainer deck) {
		this.deck = deck;
	}


	public CardContainer getHand() {
		return hand;
	}


	public void setHand(CardContainer hand) {
		this.hand = hand;
	}


	public CardContainer getDrawPile() {
		return drawPile;
	}


	public void setDrawPile(CardContainer drawPile) {
		this.drawPile = drawPile;
	}


	public CardContainer getDiscardPile() {
		return discardPile;
	}


	public void setDiscardPile(CardContainer discardPile) {
		this.discardPile = discardPile;
	}


	public CardContainer getExhaustPile() {
		return exhaustPile;
	}


	public void setExhaustPile(CardContainer exhaustPile) {
		this.exhaustPile = exhaustPile;
	}


	public String getName() {
		return name;
	}


	public List<Buff> getBuffs() {
		return buffs;
	}


	public TurnData getTurn() {
		return turn;
	}


	public CombatData getCombat() {
		return combat;
	}
	
	/********************************        Methods        ********************************/
	
	@Override
	public void turnReset() {
		this.turn.reset();
		//do stuff when the turn ends;
		this.discardPile.addAll(this.hand.takeAllNonRetained());
		for ( Buff b : this.buffs){
			b.setTurnsLeft(b.getTurnsLeft()-1);
			if (b.getTurnsLeft()==0){
				buffs.remove(b);
			}
		}

		// UPDATE : DEberia añadir una clase debuff container para gestionar toda la logica de buffs y que este manejase
		//el efecto del paso del turno de cada buff/debuff
	}
	public void turnStart(){
		this.block =0;
		System.out.println("si");
		this.hand.addAll(this.drawPile.drawCards(5));
		System.out.println("no");
		this.energy = 3;
	}
	public void addMantra(int mantra) {
		int previousMantra = this.combat.getMantra();
		if ((previousMantra/10) < ((previousMantra+mantra)/10)) {
			this.setStance(CharacterStance.Divinity);
		}
		this.combat.addMantra(mantra);
	}
	//Simple Damage Take, block first
	//
	//
	public void takeDamage(int dmg) {
		for (Buff b : this.buffs) {
			if (b.getName().equals("Vulnerable")) {
				dmg = dmg +(dmg/2);
			}
		}
		
		
		if(this.block<dmg) {
			this.block-=dmg;
			this.hp+=this.block;
			this.block=0;
		}else {
			this.block-=dmg;
		}
		
		if(this.hp<0) {
			this.hp=0;
		}
		if(this.block<0) {
			this.block=0;
		}
		
		
		/*
		for (Buff b : this.buffs) {
			if (b.getName().equals("Vulnerable")) {
				dmg += dmg / 2;
			}
		}
		//This should also consider relics that affect this debuff but for now let it be
		if (this.block > 0 ) {
			if (this.block < dmg) {
				dmg -= this.block;
				this.setBlock(0);
				if (this.hp > dmg) {
					this.hp -= dmg;
				} else {
					this.setHp(0);
					//End of the game;
				}
			}
		} else if (this.block > dmg){
			this.block -= dmg;
		} else {
			//I do this in this way because in the future I want to implement some relics 
			//that have specific behavior when you bypass foe's block
			this.setBlock(0);
		}*/
	}
	
	public int calculateCardDamage(AttackCard c) {
		int str = 0; //Take Strength from character buffs or make it a attribute of the class
			
		int dmg = c.damage(str, this.block);
		if (this.stance == CharacterStance.Wrath) {
			dmg = 2*dmg;
		} else if (this.stance == CharacterStance.Divinity) {
			dmg = 3*dmg;
		}
		return dmg;
	}
	public void drawCard(){
		hand.addCard(drawPile.drawCard());
	}
	public void discardCard(){
		discardPile.addCard(hand.drawCard());
	}
	public void discardCard(Card card){
		discardPile.addCard(hand.drawCard(card));
	}



}
