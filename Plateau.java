import java.util.*;
import java.io.*;


public class Plateau{
  private Piece [][] plateau ;
  private int nbcase;
///////////////////////////////Main/////////////////////////////////////
  public Plateau(int num){
    //int nbcase = 225 ;
    nbcase=num;
    plateau = new Piece [nbcase][nbcase];
    for(int i = 0;i<nbcase; i++ ){
      for (int j = 0;j<nbcase ;j++ ) {
        plateau [i][j]= new Void(i,j);
      }
    }
  }
/**
  AffichePlateau(plateau);
  System.out.println();
  Joueur [] tab = new Joueur[2];
  System.out.println(plateau.length);
**/
/////////////////////////////Affichage//////////////////////////////////
 public void AffichePlateau(){
   for (int k=0;k<nbcase ;k++ ) {
     System.out.println("  ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___");
     for (int l=0;l<nbcase ;l++ ) {
       System.out.print(" | " + Color.colorTxt(plateau[k][l].getSymbole(),plateau[k][l].getCouleur()));

     }
     System.out.print(" | ");
     System.out.println();
     //System.out.println("  ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___");

   }


   /**
    for(int i = 0;i<nbcase;i++){
      if(i>2 && i%15 == 0 ){
        System.out.println();
        System.out.println("  ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___");
      //  System.out.println("check");
      }
        System.out.print(" | " + plateau[i]) ;
    //  System.out.println("check");
    **/
  }

  public int getNb(){
    return nbcase;
  }

  public void placerPiece(Piece piece){
    int[] pos=piece.getPos();
    plateau[pos[0]][pos[1]]=piece;

  }

  public void deletePiece(int posx,int posy){
    plateau[posx][posy]=new Vide(posx,posy);
  }

  public Piece getPiece(int x, int y){
    return plateau[x][y];
  }

  public String getCase(int x,int y){
    return plateau[x][y].getSymbole();
  }

}
  /////////////////////////////GESTION PIECES//////////////////////////////////
