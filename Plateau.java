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
        plateau [i][j]= new Vide(i,j);
      }
    }
  }
/////////////////////////////Affichage//////////////////////////////////
 public void AffichePlateau(){
   System.out.println("  _1_ _2_ _3_ _4_ _5_ _6_ _7_ _8_ _9_ 10_ 11_ 12_ 13_ 14_ 15_");
   for (int k=0;k<nbcase ;k++ ) {

     System.out.println("  ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___");
     for (int l=0;l<nbcase ;l++ ) {

       System.out.print(" | " + Color.colorTxt(plateau[k][l].getSymbole(),plateau[k][l].getCouleur()));

     }
     System.out.print(" | ");
     System.out.print((k+1));
     System.out.println();

   }



  }

  public int getNb(){
    return nbcase;
  }

  public void placerPiece(Piece piece){

    int [] pos=piece.getPos();
    plateau[pos[0]][pos[1]]=piece;
  }

  public void deletePiece(int posx,int posy){
    plateau[posx][posy]=null;
    plateau[posx][posy]=new Vide(posx,posy);
  }
  /*
  public void mass_delete(){
    for (int k=0;k<nbcase ;k++ ) {
      for (int l=0;l<nbcase ;l++ ) {
        plateau[k][l]=new Vide(k,l);
      }
    }
  }
  */
  public void mass_switch(String nom_joueur){
    for (int k=0;k<nbcase ;k++ ) {
      for (int l=0;l<nbcase ;l++ ) {
        String p=plateau[k][l].getProprio();
        if (p!=nom_joueur && p!="none"){
          plateau[k][l].switchcolor();
        }
      }
    }
  }

  public Piece getPiece(int x, int y){
    return plateau[x][y];
  }

  public String getCase(int x,int y){
    if ((x>=0 &&  x<=(nbcase-1)) && (y>=0 && y<=(nbcase-1))) {
      return plateau[x][y].getSymbole();
    }
    else{
      return "out of range.";
    }
  }

}
