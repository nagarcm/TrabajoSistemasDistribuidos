package persistence;
 import model.*;

 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.util.*;
public class Persistencia {
    private static final String DEFAULTDATAPATH = "TrabajoSistemasDistribuidos/resources/data/SlayTheSpire.dat";
    private static String dataPath = DEFAULTDATAPATH;
    private static Set<Card> cards = null;
    public static Set<Card> getAllCards(){
        initialiceData();
        return cards;
    }


    public static void initialiceData(){
        if (cards == null){
            updateData();
        }
    }
    public static void updateData(){
        cards = new HashSet<>();
        try{
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(dataPath));
            Card c;
            c = (Card) oi.readObject();
            while (c!=null){
                cards.add(c);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDataPath(String path){
        dataPath = path;
    }
    public static void resetDataPath(){
        dataPath = DEFAULTDATAPATH;
    }

}
