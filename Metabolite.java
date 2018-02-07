import java.util.*;
import java.io.*;

public class Metabolite extends Piece {

  public Metabolite(int posx,int posy,String couleur){
    super(posx,posy);
    setSymbol("M");
    setColor(couleur);
  }
}
