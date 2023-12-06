package model;

import java.util.List;

public interface IPerformableAtEvent {
	public List<Action> performAt(EventType e); 
	public boolean isPerformableAt(EventType e);
}
