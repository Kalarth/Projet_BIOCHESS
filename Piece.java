import java.util.*;
import java.io.*;

public class Piece {
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

  public void kill(){
    living=false;
  }

  public int[] getPos(){
    return position;
  }

  public String getProprio(){
    return proprietaire;
  }

  public String getCouleur(){
    return couleur;
  }

  public boolean isAlive(){
    return living;
  }

  public String getSymbole(){
    return Symbole;
  }

  public void setNewpos(int posx,int posy){
    position[0]=posx;
    position[1]=posy;
  }

  public void setSymbol(String s){
    Symbole=s;
  }

  public void setColor(String co){
    couleur=co;
  }
}
