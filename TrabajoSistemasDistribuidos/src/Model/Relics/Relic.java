package Model.Relics;

import java.util.List;

import Model.Effects.Action;
import Model.basics.EventType;
import Model.basics.IPerformableAtEvent;

public class Relic implements IPerformableAtEvent {
	private String name;
	private int rarty;
	@Override
	public List<Action> performAt(EventType e) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isPerformableAt(EventType e) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
