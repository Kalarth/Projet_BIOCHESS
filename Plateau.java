import java.util.*;
import java.io.*;


public class Plateau{
  private Piece [][] plateau ;
///////////////////////////////Main/////////////////////////////////////
  public void Plateau(int nbcase){
    //int nbcase = 225 ;
    plateau = new Piece [nbcase][nbcase];
    for(int i = 0;i<nbcase; i++ ){
      for (int j = 0;j<nbcase ;j++ ) {*
        plateau [i][j]= "O";
      }
    }
  }

  AffichePlateau(plateau);
  System.out.println();
  Joueur [] tab = new Joueur[2];
  System.out.println(plateau.length);
/////////////////////////////Affichage//////////////////////////////////
 public void AffichePlateau(){
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
  /////////////////////////////GESTION PIECES//////////////////////////////////
  public void placerPiece(Piece piece){
    int [] pos=piece.getPos();
    plateau[pos[0]][pos[1]]=piece.getCase() //TODO
  }

  public void deletePiece(int posx,int posy){
    plateau[posx][posy]="0";
  }





}
