package logic;
import java.io.Serializable;

import model.*;

public class PlayerAction implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean endTurn;
    private Card playedCard;


    public boolean isEndTurn() {
        return endTurn;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public PlayerAction(boolean endTurn, Card playedCard) {
        this.endTurn = endTurn;
        this.playedCard = playedCard;
    }
    public String toString() {
    	if(endTurn) {
    		return "true"+ playedCard.toString();
    	}else{
    		return "false"+ playedCard.toString();
    	}
    }

}
