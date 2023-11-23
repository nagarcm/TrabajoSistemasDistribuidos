package Model.Characters;

import java.util.*;

import Model.Buffs.Buff;
import Model.Cards.Card;
import Model.Relics.*;
import Model.basics.*;
import Model.Data.*;

public class GameCharacter implements ITurnReset{
	private String name;
	private int hp;
	private int block;
	
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
		
		this.turn = new TurnData();
		this.combat = new CombatData();
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}


	public int getBlock() {
		return block;
	}


	public void setBlock(int block) {
		this.block = block;
	}


	public List<Relic> getRelics() {
		return relics;
	}


	public void setRelics(List<Relic> relics) {
		this.relics = relics;
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
	
	//Methods
	
	@Override
	public void turnReset() {
		//do stuff when the turn ends;
	}
	
	
	
	
	
}
