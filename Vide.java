import java.util.*;
import java.io.*;

public class Vide extends Piece {
  public Vide(int [] pos){
    this.position=pos;
    this.proprietaire="none";
    this.couleur="RESET";
    this.living=false;
    this.Symbole=" ";
  }

  public Vide(int posx,int posy){
    this.position=new int [2];
    this.position[0]=posx;
    this.position[1]=posy;
    this.proprietaire="none";
    this.couleur="RESET";
    this.living=false;
    this.Symbole=" ";
  }

  public void randmove(){

  }

  public void incremente_capa(){

  }
}
