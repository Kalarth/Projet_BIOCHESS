class Joueur{

  private int score;
  private String nomJoueur;

  public Joueur(String playeur){
    nomJoueur = playeur;
    score = 0;
  }

  public int getScore(){
    return score;
  }

  public void ScorePlusUn(){
    score = score++ ;
  }
  public void AfficheScore(){
    System.out.println(nomJoueur + "à"  +Score)
  }

}
