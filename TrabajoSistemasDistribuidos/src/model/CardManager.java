package model;

import java.util.*;

public class CardManager {
	private static Map<String, Card> cardCollection = new HashMap<>();
	private static Map<String, Card> cursesAndStatus = new HashMap<>();
	private static Random random = new Random();
	
	///This class will manage the cards base information, it means, the base state of every type of card, for this it will save a "base copy" of every card (taken form persistence)
	

	public static Card getRandomCard(){
		Set<String> set = cardCollection.keySet();
		int n = random.nextInt() % set.size();
		return (Card) cardCollection.get((String)set.toArray()[n]).clone();
	}
	public static Card getRandomCard(CardType ct){
		Collection<Card> cards = cardCollection.values();
		ArrayList<Card> ar = new ArrayList<>();
		for (Card c: cards) {
			if(c.getCardType() == ct){
				ar.add(c);
			}
		}
		//Suponemos que existe almenos 1 carta de
		return (Card) ar.get(random.nextInt()%ar.size()).clone();
	}

	public static void addCard(Card c){
		if(c.getCardType()!=CardType.Curse && c.getCardType()!=CardType.Status){
			cardCollection.put(c.getName(), c);
		} else {
			cursesAndStatus.put(c.getName(), c);
		}
	}
	
	// Once we have the data, respond the data request 
	
	public static void resetCard(Card c) {
		//theoretically we will always enter the if because all the types of Cards are in cardColection
		if(c.getCardType()!=CardType.Curse && c.getCardType() != CardType.Status){
			if(cardCollection.containsKey(c.getName())) {
				c.copyStats(cardCollection.get(c.getName()));
			}
		} else {
			if (cursesAndStatus.containsKey(c.getName())) {
				c.copyStats(cursesAndStatus.get(c.getName()));
			}
		}
	}
	
	
	
	
	
	
}
