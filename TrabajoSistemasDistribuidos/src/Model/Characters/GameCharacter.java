package Model.Characters;

import java.util.List;

import Model.Buffs.Buff;
import Model.Cards.Card;
import Model.Relics.*;
import Model.basics.*;


public class GameCharacter {
	private String Name;
	private int HP;
	private int shield;
	private List<Relic> relics;
	private CharacterStatus status;
	private List<Buff> buffs;
	private Card lastCardPlayed;
	private int timesLoseHP; 
	
}
