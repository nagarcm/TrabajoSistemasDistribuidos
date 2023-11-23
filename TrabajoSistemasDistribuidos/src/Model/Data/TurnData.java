package Model.Data;
import java.util.HashMap;
import java.util.Map;

import Model.basics.*;

public class TurnData {
	private int timesRecivedDamage;
	private int numCardsDiscarted;
	private int defaultCardCost;
	private boolean costPerType;
	private Map<CardType,Integer> typeCost;
	
	private int attacksPlayed;
	private int skilsPlayed;
	private int powersPlayed;
	
	public int getAttacksPlayed() {
		return attacksPlayed;
	}
	public void addAttacksPlayed() {
		this.attacksPlayed++;
	}
	public int getSkilsPlayed() {
		return skilsPlayed;
	}
	public void addSkilsPlayed() {
		this.skilsPlayed++;
	}
	public int getPowersPlayed() {
		return powersPlayed;
	}
	public void addPowersPlayed() {
		this.powersPlayed++;
	}
	public TurnData() {
		this.timesRecivedDamage = 0;
		this.numCardsDiscarted = 0;
		this.defaultCardCost = -1;
		this.costPerType = false;
		this.typeCost = new HashMap<>();
		this.attacksPlayed = 0;
		this.skilsPlayed = 0;
		this.powersPlayed = 0;
	}
	public int getTimesRecivedDamage() {
		return timesRecivedDamage;
	}
	public void recivedDamage() {
		this.timesRecivedDamage++;
	}
	public int getNumCardsDiscarted() {
		return numCardsDiscarted;
	}
	public void addCardsDiscarted(int numCardsDiscarted) {
		this.numCardsDiscarted += numCardsDiscarted;
	}
	public int getDefaultCardCost() {
		return defaultCardCost;
	}
	public void setDefaultCardCost(int defaultCardCost) {
		this.defaultCardCost = defaultCardCost;
	}
	public boolean isCostPerType() {
		return costPerType;
	}
	public void setCostPerType(boolean costPerType) {
		this.costPerType = costPerType;
	}
	public Map<CardType, Integer> getTypeCost() {
		return typeCost;
	}
	public void addCostType(CardType ct, Integer in) {
		this.typeCost.put(ct, in);
	}
	public void setTypeCost(Map<CardType, Integer> typeCost) {
		this.typeCost = typeCost;
	}

	
	public void reset() {
		this.timesRecivedDamage = 0;
		this.numCardsDiscarted = 0;
		this.defaultCardCost = -1;
		this.costPerType = false;
		this.typeCost = new HashMap<>();
		this.attacksPlayed = 0;
		this.skilsPlayed = 0;
		this.powersPlayed = 0;
	}

}
