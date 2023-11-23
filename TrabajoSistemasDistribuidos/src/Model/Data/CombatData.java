package Model.Data;

public class CombatData {
	private int timesRecivedDamage;
	private int mantra;

	public CombatData() {
		this.timesRecivedDamage = 0;
		this.mantra = 0;
	}
	
	public int getTimesRecivedDamage() {
		return this.timesRecivedDamage;
	}
	public void recivedDamage() {
		this.timesRecivedDamage++;
	}
	public int getMantra() {
		return this.mantra;
	}
	public void addMantra(int n) {
		this.mantra += n;
	}
	public void reset() {
		this.timesRecivedDamage = 0;
	}
	
}
