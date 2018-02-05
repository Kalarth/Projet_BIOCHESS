import java.util.*;
import java.io.*;
import java.lang.*;


class Plateau{

///////////////////////////////Main/////////////////////////////////////
  public static void main (String [] args){
    //Les variable
    int nbcase = 225 ;
    String [] plateau = new String [nbcase];
    //Initialisation des joueurs
    Joueur [] tab = new Joueur[2];
    tab = InitJoueur(tab);
    //Initialisation du plateau
    plateau = InitPlateau(plateau,nbcase);
  //  AffichageScore(tab);
    //Jeu
    tours(plateau,tab);

  }


///////////////////////Initialisation Plataeau////////////////////////////
  public static String[] InitPlateau(String[]plateau,int nbcase){
    for(int i = 0;i<nbcase; i++ ){
      plateau [i]= "O";
    }
    //AffichePlateau(plateau);
    System.out.println();
    System.out.println(plateau.length);
    return plateau ;
  }


/////////////////////////Gestion des Joueurs//////////////////////////////
  public static Joueur[] InitJoueur(Joueur[]tab){
    System.out.println("Entrez le nom du joueur 1");
    String playeur1 = saisie_chaine();
    CreationDesJoueur(tab , playeur1 ,0);
    System.out.println("Entrez le nom du joueur 2");
    String playeur2 = saisie_chaine();
    CreationDesJoueur(tab , playeur2 ,1);
    return tab ;
  }

  public static void CreationDesJoueur(Joueur [] tab,String nom ,int position){
    Joueur item = new Joueur(nom);
    tab[position] = item ;
  }

  public static void VerifVictoire(Joueur[]tab,int i){
      tab[i].Victoire();

  }

  public static void tourJoueur(String[]plateau,Joueur[]tab){
    System.out.println(tab[0].getNomJoueur()+" joue");
    VerifVictoire(tab,0);
    CleanTerminal();
    AffichePlateau(plateau);
    System.out.println(tab[1].getNomJoueur()+" joue");
    VerifVictoire(tab,1);
    CleanTerminal();
    AffichePlateau(plateau);
  }


///////////////////////Gestion des metabolite///////////////////////////
  public static void tourMetabolite(String[]plateau){
    System.out.println("Deplacement des metabolites");
    AffichePlateau(plateau);
  }


/////////////////////////////Affichage//////////////////////////////////
 public static void AffichePlateau(String [] plateau){
    for(int i = 0;i<225;i++){
      if(i>2 && i%15 == 0 ){
        System.out.println();
        //System.out.println("  ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ___");
      }
        System.out.print(" | " + plateau[i]) ;
    }
    System.out.println();
  }

  public static void AffichageScore(Joueur[] tab){
    for( int i = 0 ; i < tab.length ; i++){
      tab[i].AfficheScore();
    }
  }

  public static void tours(String[]plateau,Joueur[]tab){
    int nbtours = 0;
    for(;;){
      System.out.println("TOURS "+ nbtours);
      tourMetabolite(plateau);
      tourJoueur(plateau,tab);
      AffichageScore(tab);
      nbtours = nbtours + 1;
      System.exit(0);
    }
  }

  public static void CleanTerminal(){
    try{
      Runtime.getRuntime().exec("clear");
    }
    catch(IOException e){
      System.out.println("Erreur");
    }
  }
//////////////////////////////Menu///////////////////////////////////////
  public static void menuDeplacement(){
    System.out.println("Quel deplacement voulez vous effectuer ?");
    System.out.println("1) Vers l'avant ");
    //if(piece != lipide ){};
    System.out.println(" Vers l'arriere");
    System.out.println("à gauche ");
    System.out.println("à droite");
  }


////////////////////////////Saisie//////////////////////////////////////
 public static String saisie_chaine(){
    try{
      BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
      String chaine=buff.readLine();
      return chaine;
    }
    catch(IOException e) {
      System.out.println(" impossible de travailler" +e);
      return null;
    }
  }



}
