import java.util.*;
import java.io.*;

public class Lipide extends Piece {

  public Lipide(int [] pos,String proprio,String couleur){
    super(pos,proprio,couleur);
    setSymbol("L");

  }

  public Lipide(int posx,int posy,String proprio,String couleur){
    super(posx,posy,proprio,couleur);

    setSymbol("L");

  }


}
