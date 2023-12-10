package logic;

import model.*;

import java.io.Serializable;
import java.util.*;

public class DataUpdate implements Serializable{
    /**
	 * 
	 */
	private boolean lastUpdate;
	private static final long serialVersionUID = 1L;
	private boolean gameEnd;
    private boolean playNextAction;

    private Target user;

    private int damagePerHit;
    private int hits;
    private int reflectDamage;
    private int blockGain;

    private boolean buffsModified;
    private List<Buff> ownBuffs;
    private List<Buff> enemyBuffs;


    private int finalEnemyHP;
    private int finalEnemyBlock;
    private CharacterStance finalEnemyStance;
    private int finalOwnHP;
    private int finalOwnBlock;
    private CharacterStance finalOwnStance;
    private int energy;
    private Card lastPlayedCard;


    private List<Card> hand;

    
    public boolean isLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(boolean lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public boolean isGameEnd() {
        return gameEnd;
    }

    public boolean isPlayNextAction() {
        return playNextAction;
    }

    public Target getUser() {
        return user;
    }

    public int getDamagePerHit() {
        return damagePerHit;
    }

    public int getHits() {
        return hits;
    }

    public int getReflectDamage() {
        return reflectDamage;
    }

    public int getBlockGain() {
        return blockGain;
    }

    public boolean isBuffsModified() {
        return buffsModified;
    }

    public List<Buff> getOwnBuffs() {
        return ownBuffs;
    }

    public List<Buff> getEnemyBuffs() {
        return enemyBuffs;
    }

    public int getFinalEnemyHP() {
        return finalEnemyHP;
    }

    public int getFinalEnemyBlock() {
        return finalEnemyBlock;
    }

    public int getFinalOwnHP() {
        return finalOwnHP;
    }


    public int getFinalOwnBlock() {
        return finalOwnBlock;
    }

    public int getEnergy() {return energy;}

    public CharacterStance getFinalEnemyStance() {
        return finalEnemyStance;
    }

    public CharacterStance getFinalOwnStance() {
        return finalOwnStance;
    }

    public List<Card> getHand(){
        return this.hand;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public void setLastPlayedCard(Card lastPlayedCard) {
        this.lastPlayedCard = lastPlayedCard;
    }
    public DataUpdate(boolean gameEnd, boolean playNextAction, Target user, int damagePerHit,
                      int hits, int reflectDamage, int blockGain, boolean buffsModified,
                      List<Buff> ownBuffs, List<Buff> enemyBuffs, int finalEnemyHP, int finalEnemyBlock, CharacterStance finalEnemyStance,
                      int finalOwnHP, int finalOwnBlock, CharacterStance finalOwnStance, int energy,Card lastPlayedCard, List<Card> hand) {
        this.gameEnd = gameEnd;
        this.playNextAction = playNextAction;
        this.user = user;
        this.damagePerHit = damagePerHit;
        this.hits = hits;
        this.reflectDamage = reflectDamage;
        this.blockGain = blockGain;
        this.buffsModified = buffsModified;
        this.ownBuffs = ownBuffs;
        this.enemyBuffs = enemyBuffs;
        this.finalEnemyHP = finalEnemyHP;
        this.finalEnemyBlock = finalEnemyBlock;
        this.finalEnemyStance = finalEnemyStance;
        this.finalOwnHP = finalOwnHP;
        this.finalOwnBlock = finalOwnBlock;
        this.finalOwnStance = finalOwnStance;
        this.energy = energy;
        this.lastPlayedCard = lastPlayedCard;
        this.hand = hand;
        this.lastUpdate=false;
    }

    public DataUpdate invertData() {
        return new DataUpdate(gameEnd, !playNextAction, (user == Target.Self) ? Target.Enemy : Target.Self, damagePerHit,
                hits, reflectDamage, blockGain, buffsModified, enemyBuffs, ownBuffs, finalOwnHP, finalOwnBlock, finalOwnStance,finalEnemyHP, finalEnemyBlock, finalEnemyStance, 0, lastPlayedCard,null);
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("Estado Actual de la partida: \n Tu:\nHP:");
    	sb.append(this.finalOwnHP);sb.append("\t\t");
    	sb.append("Block: ");sb.append(this.finalOwnBlock);sb.append("\t\t");
    	sb.append("Stance: ");sb.append(this.finalOwnStance);sb.append("\n");
    	sb.append("Buffs: ");
    	if(this.ownBuffs!=null) {
    		for(Buff b : this.ownBuffs) {
        		sb.append(b.toString());
        	}
    	}else {
    		System.out.println("No buffs");
    	}
    	
    	
    	
    	sb.append("\n\nEnemigo:\nHP:");
    	sb.append(this.finalEnemyHP);sb.append("\t\t");
    	sb.append("Block: ");sb.append(this.finalEnemyBlock);sb.append("\t\t");
    	sb.append("Stance: ");sb.append(this.finalEnemyStance);sb.append("\n");
    	sb.append("Buffs: ");
    	if(this.enemyBuffs!=null) {
    		for(Buff b : this.enemyBuffs) {
        		sb.append(b.toString());
        	}
    	}else {
    		System.out.println("No buffs");
    	}
    	
    	if(this.playNextAction) {
    		sb.append("\nTu turno:\nEnergia: ");sb.append(this.energy);
    		sb.append("\n0. Finalizar turno.\n");
    		for (int i= 1; i<=this.hand.size();i++) {
    			
    			sb.append(i);sb.append(". ");sb.append(hand.get(i-1).toString());
    			sb.append("\n");
    		}
    	}else {
    		sb.append("\nTurno del rival\nEsperando al rival...");
    	}

        return sb.toString();
    }
}
