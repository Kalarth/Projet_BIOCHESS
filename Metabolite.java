import java.util.*;
import java.io.*;

public class Metabolite extends Piece {

  public Metabolite(int[] pos,String color){
    this.position=pos;
    this.proprietaire="none";
    this.couleur=color;
    //this.living=true;
    this.Symbole="M";

  }

  public Metabolite(int posx,int posy,String color){
    this.position=new int [2];
    this.position[0]=posx;
    this.position[1]=posy;
    this.proprietaire="none";
    this.couleur=color;
    //this.living=true;
    this.Symbole="M";
  }

  public void randmove(){ //QUE POUR METABOLITES
    Random rand = new Random();
    int move=rand.nextInt(3)+1;
    this.setMove(move);

  }

  public void incremente_capa(){

  }


}
