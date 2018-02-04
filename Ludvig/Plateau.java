import java.util.*;
import java.io.*;


class Plateau{

///////////////////////////////Main/////////////////////////////////////
  public static void main (String [] args){
    int nbcase = 225 ;
    Piece [] [] plateau = new Piece [nbcase][nbcase];
    for(int i = 0;i<nbcase; i++ ){
      for (int j = 0;j<nbcase ;j++ ) {*
        plateau [i][j]= "O";
      }
    }
    AffichePlateau(plateau);
    System.out.println();
    Joueur [] tab = new Joueur[2];
    System.out.println(plateau.length);
  //  System.out.println(Playeur1.getScore());
  }
/////////////////////////////Affichage//////////////////////////////////
 public static void AffichePlateau(String [] plateau){
    for(int i = 0;i<225;i++){
      if(i>2 && i%15 == 0 ){
        System.out.println();
        System.out.println("  ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___");
      //  System.out.println("check");
      }
        System.out.print(" | " + plateau[i]) ;
    //  System.out.println("check");
    }
  }

  //public static void tours









}
