package Model.basics;

import java.util.List;

import Model.Effects.Action;

public interface IPerformableAtEvent {
	public List<Action> performAt(EventType e); 
}
