import java.util.*;
import java.io.*;

public class Lipide extends Piece {

  public Lipide(int [] pos,String proprio,String color){
    this.position=pos;
    this.proprietaire=proprio;
    this.couleur=color;
    this.couleur_alt="RESET";
    this.living=true;
    this.Symbole="L";

  }

  public Lipide(int posx,int posy,String proprio,String color){
    this.position=new int [2];
    this.position[0]=posx;
    this.position[1]=posy;
    this.proprietaire=proprio;
    this.couleur=color;
    this.couleur_alt="RESET";
    this.living=true;
    this.Symbole="L";
  }

  public void randmove(){

  }

  public void incremente_capa(){

  }


}
