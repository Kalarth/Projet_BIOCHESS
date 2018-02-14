import java.util.*;
import java.io.*;

public abstract class Piece {
  protected int [] position;
  protected String proprietaire;
  protected int mouvement;
  protected String couleur;
  protected String couleur_alt;
  //protected boolean living;
  protected String Symbole;
  /*
  public void kill(){
    living=false;
  }
*/
  public int[] getPos(){
    return position;
  }

  public String getProprio(){
    return proprietaire;
  }

  public String getCouleur(){
    return couleur;
  }
/*
  public boolean isAlive(){
    return living;
  }
*/
  public  String getSymbole(){
    return Symbole;
  }

  public  int getMovement(){
    return mouvement;
  }

  public  void setNewpos(int posx,int posy){
    this.position[0]=posx;
    this.position[1]=posy;
  }

  public  void setSymbol(String s){
    this.Symbole=s;
  }

  public  void setColor(String co){
    this.couleur=co;
  }

  public void switchcolor(){
    String temp=this.couleur;
    this.couleur=couleur_alt;
    this.couleur_alt=temp;
  }

  public  void  setMove(int mov){
    this.mouvement=mov;
  }

  public abstract void randmove();

  public abstract void incremente_capa();

}
