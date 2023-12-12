package aplication;


import logic.*;
import model.*;
import interfaz.*;
import persistence.Persistencia;

import java.io.*;

import java.util.*;
public class PrincipalPruebas {
    public static void main(String[] args) throws Exception{
        InterfazTextual it = new InterfazTextual(new Game());
        it.startGame();
    }
}
