import java.util.*;
import java.io.*;

public abstract class Piece {
  private int [] position;
  private String proprietaire;
  private int mouvement;
  private String couleur;
  private boolean living;
  private String Symbole;

  public Piece(int [] pos){
    position=pos;
    proprietaire="none";
    couleur="RESET";
    mouvement=0;
    living=true;
    Symbole=" ";
  }

  public Piece(int posx,int posy){
    position=new int [2];
    position[0]=posx;
    position[1]=posy;
    proprietaire="none";
    couleur="RESET";
    mouvement=0;
    living=true;
    Symbole=" ";
  }

  public Piece(int [] pos,String proprio,String coul){
    position=pos;
    proprietaire=proprio; //Joueur1, Joueur2, none
    couleur=coul;
    mouvement=0;
    living=true;
    Symbole=" ";

  }

  public Piece(int posx,int posy,String proprio,String coul){
    position=new int [2];
    position[0]=posx;
    position[1]=posy;
    proprietaire=proprio;
    couleur=coul;
    mouvement=0;
    living=true;
    Symbole=" ";

  }

  public abstract void kill(){
    living=false;
  }

  public abstract int[] getPos(){
    return position;
  }

  public abstract String getProprio(){
    return proprietaire;
  }

  public abstract String getCouleur(){
    return couleur;
  }

  public abstract boolean isAlive(){
    return living;
  }

  public abstract String getSymbole(){
    return Symbole;
  }

  public abstract int getMovement(){
    return mouvement;
  }

  public abstract void setNewpos(int posx,int posy){
    position[0]=posx;
    position[1]=posy;
  }

  public abstract void setSymbol(String s){
    Symbole=s;
  }

  public abstract void setColor(String co){
    couleur=co;
  }

  public abstract void  setMove(int mov){
    mouvement=mov;
  }

  public abstract void randmove(){ //QUE POUR METABOLITES
    Random rand = new Random();
    int move=rand.nextInt(3)+1;
    setMove(move);

  }
}
