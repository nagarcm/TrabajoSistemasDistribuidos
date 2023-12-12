package aplication;


import logic.*;
import model.*;
import interfaz.*;
import persistence.Persistencia;

import java.io.*;

import java.util.*;
public class Juego {
    public static void main(String[] args) {
        InterfazTextual it = new InterfazTextual(new Game());
        it.startGame();
    }
}
