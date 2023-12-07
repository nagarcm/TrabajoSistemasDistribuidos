package logic;
import model.*;

public class PlayerAction {
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
}
