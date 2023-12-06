package model;

import java.util.*;

public class CardManager {
	private static Map<String, Card> cardCollection = new HashMap<>();
	
	///This class will manage the cards base information, it means, the base state of every type of card, for this it will save a "base copy" of every card (taken form persistence)
	
	/*
	 * Take cards from persistence to fill the map
	 */
	
	
	// Once we have the data, respond the data request 
	
	public static void resetCard(Card c) {
		//theoretically we will always enter the if because all the types of Cards are in cardColection
		if(cardCollection.containsKey(c.getName())) {
			c.copyStats(cardCollection.get(c.getName()));
		}
	}
	
	
	
	
	
	
}
