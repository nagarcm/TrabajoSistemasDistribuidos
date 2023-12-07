package model;
//import Model.basics.*;
import java.util.*;

import model.Card;
import model.CardMod;

public class CardContainer {
	private List<Card> cards;
	private int numInnate;
	private CardContainer auxiliarCardSource;
	private int limitCardAmount;
	private CardContainer auxiliarCardDiscard;

	public CardContainer() {
		this.cards = new ArrayList<>();
		this.numInnate = 0;
		this.auxiliarCardSource = null;
		this.auxiliarCardDiscard = null;
		this.limitCardAmount = 99;
	}

	public void setAuxiliarCardSource(CardContainer auxiliarCardSource){
		this.auxiliarCardSource = auxiliarCardSource;
	}
	public void setAuxiliarCardDiscard(CardContainer auxiliarCardDiscard){
		this.auxiliarCardDiscard = auxiliarCardDiscard;
	}
	// PRE : The card collection is not empty or there is a auxiliarCardSource != null;
	// POST: Returns a card (first among the innate ones or first of the array if there aren't any innate cards
	public Card drawCard() {
		if(this.cards.isEmpty() && this.auxiliarCardSource != null){
			this.cards.addAll(auxiliarCardSource.getAllCards());
			auxiliarCardSource.clear();
		}
		if(numInnate>0) {
			for(Card c : cards) {
				if (c.haveMod(CardMod.Innate)) {
					this.cards.remove(c);
					return c;
				}
			}
		}
		return cards.get(0);
	}
	// PRE : Card c is in the container
	// POST : Removes de the card from the container and returns it
	public Card drawCard(Card c) {
		this.removeCard(c);
		return c;
	}
	public List<Card> drawCards(int num){
		ArrayList<Card> arrayList = new ArrayList<>();
		for (int n = 0 ; n<num;n++){
			arrayList.add(this.drawCard());
		}
		return arrayList;
	}
	
	public void addCard(Card c) {
		if (this.limitCardAmount<this.cards.size()){
			this.cards.add(c);
			if (c.haveMod(CardMod.Innate)) {
				this.numInnate++;
			}
		} else if(this.auxiliarCardDiscard != null){
			this.auxiliarCardDiscard.addCard(c);
		}

	}
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	public boolean contains(Card c) {
		return this.cards.contains(c);
	}
	public void removeCard(Card c) {
		if (this.cards.remove(c) && c.haveMod(CardMod.Innate)) {
			this.numInnate--;
		}
		
	}
	public void addAll(Collection<Card> collection){
		for (Card card : collection){
			this.addCard(card);
		}
	}
	public void suflle(){
		Random random = new Random();
		Card card = this.cards.getLast();
		this.cards.removeLast();
		int index = random.nextInt(this.cards.size());
		for(int n = 0; n <70 ; n++){
			card  = this.cards.set(index,card);
			index = random.nextInt(this.cards.size());
		}
		this.cards.add(card);
	}
	
	public List<Card> getAllCards(){
		return this.cards;
	}
	// PRE : The list contains the index of the cards to draw, none of this index exceed array "cards" size-1
	// POST: Return a list of the cards Selected by the set ; The Cards are removed from the container
	public List<Card> getSelectedCards(Set<Integer> list){
		List<Card> result = new ArrayList<Card>();
		for(int n : list) {
			result.add(this.cards.get(n));
		}
		for (Card c : result) {
			this.cards.remove(c);
		}
		return result;
	}
	public List<Card> takeAllNonRetained(){
		List<Card> ar = new ArrayList<>();
		for (Card c : this.cards){
			if(!c.haveMod(CardMod.Retain)){
				ar.add(c);
				this.cards.remove(c);
			}
		}
		return ar;
	}

	public void clear(){
		this.cards.clear();
	}
	public int size(){return this.cards.size();}
	public void setLimitCardAmount(int limitCardAmount){
		this.limitCardAmount = limitCardAmount;
	}
	
}
