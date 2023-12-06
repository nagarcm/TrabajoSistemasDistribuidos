package model;

public class Action {
	protected ActionType type;
	protected Target target;
	protected int value;
	
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Action(ActionType type, Target target, int value) {
		this.type = type;
		this.target = target;
		this.value = value;
	}
	@Override
	public Object clone() {
		return new Action(type, target, value);
	}
}
 