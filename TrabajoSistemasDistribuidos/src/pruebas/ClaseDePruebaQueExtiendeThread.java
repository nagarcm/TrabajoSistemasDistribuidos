package pruebas;

import logic.*;

public class ClaseDePruebaQueExtiendeThread extends Thread{
    public Game game;
    public ClaseDePruebaQueExtiendeThread(Game game){this.game=game;}
    @Override
    public void run(){
        game.setHosting(true);
        game.startGame();
    }


}
