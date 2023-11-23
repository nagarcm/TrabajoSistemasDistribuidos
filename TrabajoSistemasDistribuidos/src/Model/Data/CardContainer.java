package Model.Data;
//import Model.basics.*;
import java.util.*;
import Model.Cards.*;
import Model.basics.CardMod;

public class CardContainer {
	private List<Card> cards;
	private int numInnate;

	public CardContainer() {
		this.cards = new ArrayList<>();
		this.numInnate = 0;
	}
	// PRE: The Container is not empty
	// POST: Returns a card (first among the innate ones or first of the array if there aren't any innate cards 
	public Card drawCard() {
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
	
	public void addCard(Card c) {
		this.cards.add(c);
		if (c.haveMod(CardMod.Innate)) {
			this.numInnate++;
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
	
	
}
