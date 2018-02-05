import java.util.*;
import java.io.*;


class Joueur{

  private int score;
  private String nomJoueur;
  private Vector liste_pieces;


  public Joueur(String nom){
    nomJoueur = nom;
    score = 0;
    liste_pieces = new Vector();
  }

  public String getNomJoueur(){
    return nomJoueur;
  }

  public int getScore(){
    return score;
  }
  public Vector getPieces(){
    return liste_pieces ;
  }

  public int ScorePlusUn(){
    score = score + 1 ;
    return score;
  }
  public void AfficheScore(){
    System.out.println(nomJoueur + " a "  +score);
  }

  public void Victoire(){
    if(score == 25){
      System.out.println("Felicitation ! " + nomJoueur + " a gagner");
      System.exit(0);
    }
  }

}
