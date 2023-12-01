package Model.Cards;


import java.util.*;

import Model.Effects.Action;
import Model.basics.*;

public class StatusCard extends Card implements IPerformableAtEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int energyCost;
	protected Map<EventType, List<Action>> events;

	

	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public Map<EventType, List<Action>> getEvents() {
		return events;
	}

	public void setEvents(Map<EventType, List<Action>> events) {
		this.events = events;
	}
	public void addEventReponse(EventType e, List<Action> l) {
		this.events.put(e, l);
	}
	public StatusCard(String name, CardColor color, CardType type, String description, int energyCost,
			Map<EventType, List<Action>> events) {
		super(name, color, type, description);
		this.energyCost = energyCost;
		this.events = events;
	}
	
	

	public StatusCard(String name, CardColor color, CardType type, String description, int energyCost) {
		super(name, color, type, description);
		this.energyCost = energyCost;
		this.events = new HashMap<EventType, List<Action>>();
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		Map<EventType,List<Action>> map = new HashMap<>(); 
		for (EventType e : this.events.keySet()) {
			List<Action> ev = new ArrayList<Action>();
			List<Action> l = this.events.get(e);
			for(Action a : l) {
				ev.add((Action) a.clone());
			}
			map.put(e, ev);
		}
		return new StatusCard(name,color,type,description,energyCost, map);
	}

	@Override
	public void played() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		CardManager.resetCard(this);
	}

	@Override
	public void copyStats(Card c) {
		// TODO Auto-generated method stub
		if (c instanceof StatusCard) {
			StatusCard status = (StatusCard) c;
			this.energyCost = status.energyCost;
			this.events.clear();
			
			for (EventType e : status.events.keySet()) {
				List<Action> ev = new ArrayList<Action>();
				List<Action> l = status.events.get(e);
				for(Action a : l) {
					ev.add((Action) a.clone());
				}
				this.events.put(e, ev);
			}
		}
	}

	@Override
	public List<Action> performAt(EventType e) {
		// TODO Auto-generated method stub
		
		return events.get(e);
	}

	@Override
	public boolean isPerformableAt(EventType e) {
		// TODO Auto-generated method stub
		return events.containsKey(e);}

}
