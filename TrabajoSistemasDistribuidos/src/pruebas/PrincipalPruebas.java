package pruebas;

import logic.*;
import model.*;
import persistence.Persistencia;

import java.io.*;

import java.util.*;
public class PrincipalPruebas {
    public static void main(String[] args) throws Exception{
        System.out.println("INITIALIZATION\n");


/*
        Game game = new Game();
        game.setHosting(true);
        System.out.println("creado y hosting");
        game.startGame();
*/
        //ESto hay que meterlo tras la creacion del juego y antes de la partida
        Persistencia.initialiceData();
        CardManager.addAll(Persistencia.getAllCards());

        ClaseDePruebaQueExtiendeThread si = new ClaseDePruebaQueExtiendeThread(new Game());
        si.start();

        Game g =new Game();
        g.setHosting(false);
        g.startGame();




        System.out.println("\nEND");
    }
}
