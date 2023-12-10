package model;

import java.util.*;

public class CardManager {
	private static Map<String, Card> cardCollection = new HashMap<>();
	private static Map<String, Card> cursesAndStatus = new HashMap<>();
	private static Random random = new Random();
	
	///This class will manage the cards base information, it means, the base state of every type of card, for this it will save a "base copy" of every card (taken form persistence)
	

	public static Card getRandomCard(){
		Set<String> set = cardCollection.keySet();
		return (Card) cardCollection.get((String)set.toArray()[random.nextInt(set.size())]).clone();
	}
	public static Card getRandomCard(CardType ct){
		Collection<Card> cards = cardCollection.values();
		ArrayList<Card> ar = new ArrayList<>();
		for (Card c: cards) {
			if(c.getCardType() == ct){
				ar.add(c);
			}
		}
		//Suponemos que existe almenos 1 carta del tipo introducido
		Card c = (Card) ar.get(random.nextInt(ar.size())).clone();
		return c;
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
	public static boolean existCardName(String cardName){
		return cardCollection.containsKey(cardName) || cursesAndStatus.containsKey(cardName);
	}
	
	public static Card createCardByName(String cardName){
		Card c = cursesAndStatus.get(cardName);
		return (c != null) ? c : cardCollection.get(cardName);
	}
	public static void addAll(Collection<Card> collection){
		for (Card c : collection){
			CardManager.addCard(c);
		}
	}
	
	
	
}
