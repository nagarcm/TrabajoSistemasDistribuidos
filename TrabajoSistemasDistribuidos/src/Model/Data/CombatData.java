package Model.Data;

public class CombatData {
	private int timesRecivedDamage;

	public CombatData() {

		this.timesRecivedDamage = 0;
	}
	
	public int getTimesRecivedDamage() {
		return this.timesRecivedDamage;
	}
	public void recivedDamage() {
		this.timesRecivedDamage++;
	}
	public void reset() {
		this.timesRecivedDamage = 0;
	}
	
}
