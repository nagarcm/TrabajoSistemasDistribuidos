package logic;

import model.*;
import java.util.*;

public class DataUpdate {
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


    private List<Card> hand;

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
    public DataUpdate(boolean gameEnd, boolean playNextAction, Target user, int damagePerHit,
                      int hits, int reflectDamage, int blockGain, boolean buffsModified,
                      List<Buff> ownBuffs, List<Buff> enemyBuffs, int finalEnemyHP, int finalEnemyBlock, CharacterStance finalEnemyStance,
                      int finalOwnHP, int finalOwnBlock, CharacterStance finalOwnStance,int energy, List<Card> hand) {
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
        this.hand = hand;
    }

    public DataUpdate invertData() {
        return new DataUpdate(gameEnd, !playNextAction, (user == Target.Self) ? Target.Enemy : Target.Self, damagePerHit,
                hits, reflectDamage, blockGain, buffsModified, enemyBuffs, ownBuffs, finalOwnHP, finalOwnBlock, finalOwnStance,finalEnemyHP, finalEnemyBlock, finalEnemyStance, 0, null);
    }


}
