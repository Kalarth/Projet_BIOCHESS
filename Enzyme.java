import java.util.*;
import java.io.*;

public class Enzyme extends Piece {
  private int capacite_capture;

  public Enzyme(int[] pos,String proprio,String color){
    this.position=pos;
    this.proprietaire=proprio;
    this.couleur=color;
    this.couleur_alt="RESET";
    this.living=true;
    this.Symbole="E";
    capacite_capture=0;

  }

  public Enzyme(int posx,int posy,String proprio,String color){
    this.position=new int [2];
    this.position[0]=posx;
    this.position[1]=posy;
    this.proprietaire=proprio;
    this.couleur=color;
    this.couleur_alt="RESET";
    this.living=true;
    this.Symbole="E";
    capacite_capture=0;
  }

  public void randmove(){

  }

  public void incremente_capa(){
    capacite_capture++;
  }


}
