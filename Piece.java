public class Piece {
  private int [] position;
  private String proprietaire;
  private int movement;
  private String couleur;
  private boolean living;

  public Piece(int [] pos,String proprio,String coul){
    position=pos;
    proprietaire=proprio;
    couleur=coul;
    mouvement=0;
    IsALive=true;

  }

  public Piece(int posx,int posy,String proprio,String coul){
    position=new int [2];
    position[0]=posx;
    position[1]=posy;
    proprietaire=proprio;
    couleur=coul;
    mouvement=0;
    IsALive=true;

  }

  public void kill(){
    IsAlive=false
  }

  public int getPos(){
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
}
