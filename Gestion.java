import java.util.*;
import java.io.*;
import java.nio.file.*;


public class Gestion{
  public static void main(String[] args) {
    int nbligne=15;
    Joueur[] joueur_tab=initJoueur();
    Color.setColors();

    Plateau plat=new Plateau(nbligne);
    initialisation(joueur_tab,nbligne,plat);
    plat.AffichePlateau();
  }

  public static Joueur[] initJoueur(){
    Joueur[] joueur_tab=new Joueur[2];
    System.out.println("Entrez le nom du joueur 1"); //en haut
    String playeur1 = Saisie_chaine();
    joueur_tab[0]=CreationDesJoueur(playeur1);
    System.out.println("Entrez le nom du joueur 2");  //en bas
    String playeur2 = Saisie_chaine();
    joueur_tab[1]=CreationDesJoueur(playeur2);
    return joueur_tab;

  }

  public static Joueur CreationDesJoueur(String nom){
    Joueur item = new Joueur(nom);
    return item;
  }

  public static void initialisation(Joueur [] tab, int nbligne, Plateau plat){

    for (int i=0;i<nbligne ;i++ ){
      for (int j=0;j<nbligne;j++){
        if (i==0) {
          if ((j%2)==0){
            Piece p=new Enzyme(i,j,tab[0].getNomJoueur(),"RESET");
            plat.placerPiece(p);


          }
        }
        if (i==14) {
          if ((j%2)==0) {
            Piece p=new Enzyme(i,j,tab[1].getNomJoueur(),"RESET");
            plat.placerPiece(p);


          }
        }





        if ((j>1 && j<14) && j%2==0 && i==1 ) {
          Piece p=new Lipide(i,j,tab[0].getNomJoueur(),"RESET");
          plat.placerPiece(p);
        }

        if ((j>1 && j<14) && j%2==0 && i==13 ) {
          Piece p=new Lipide(i,j,tab[1].getNomJoueur(),"RESET");
          plat.placerPiece(p);
        }

        if (j%2==1 && i==2 ) {
          Piece p=new Lipide(i,j,tab[0].getNomJoueur(),"RESET");
          plat.placerPiece(p);
        }

        if (j%2==1 && i==12 ) {
          Piece p=new Lipide(i,j,tab[1].getNomJoueur(),"RESET");
          plat.placerPiece(p);
        }

        if (j>1 && j%2==0 && i==3 ) {
          Piece p=new Lipide(i,j,tab[0].getNomJoueur(),"RESET");
          plat.placerPiece(p);
        }

        if (j>1 && j%2==0 && i==11 ) {
          Piece p=new Lipide(i,j,tab[1].getNomJoueur(),"RESET");
          plat.placerPiece(p);
        }
      }
    }
    ajout_metabolites(plat);
    colore_enzyme(plat,0);
    colore_enzyme(plat,14);
  }


  public static void colore_enzyme(Plateau plat,int x){
    int k=0;
    int limitecouleur=0;
    String [] liste_couleur = {"RED","YELLOW","GREEN","BLUE"};
    for (int j=0;j<plat.getNb(); j++) {
      if (plat.getCase(x,j)=="E") {
        plat.getPiece(x,j).setColor(liste_couleur[k]);
        limitecouleur++;
        if (limitecouleur == 2) {
          k++;
          limitecouleur=0;
        }
      }
    }
  }
/**
  public static int ajout_enzymes(Joueur [] tab,Plateau plat, int k,int x,int y, int numjoueur,int limitecouleur) {
    //int limitecouleur=0;
    k=k+0;
    limitecouleur=limitecouleur+0;
    String [] liste_couleur = {"RED","YELLOW","GREEN","BLUE"};
    Piece p=new Enzyme(x,y,tab[numjoueur].getNomJoueur(),liste_couleur[k]);
    plat.placerPiece(p);
    limitecouleur++;
    if (limitecouleur == 2) {
      k++;
      limitecouleur=0;
    }
    return k;
  }
  //int k=0;
**/



  public static void ajout_metabolites(Plateau plat){
    int k=0;
    int limit=40;
    int limitecouleur=0;
    String [] liste_couleur = {"RED","YELLOW","GREEN","BLUE"};
    Random rand=new Random();
    String couleur="";
    while (limit != 0) {
      int randx=rand.nextInt(6)+4;
      int randy=rand.nextInt(15);
      if (plat.getCase(randx,randy) == " ") {
        Piece p= new Metabolite(randx,randy,liste_couleur[k]);
        plat.placerPiece(p);
        limit--;
        limitecouleur++;
        if (limitecouleur == 10) {
          k++;
          limitecouleur=0;
        }
      }
    }
  }







  public static String Saisie_chaine(){
    Scanner input = new Scanner(System.in);
    String n = input.next();
    return n;
  }

  public static int Saisie_entier(){
    Scanner intput = new Scanner(System.in);
    int nb = intput.nextInt();
    return nb;
  }


  /**
  public static void AfficheColorTxt(String txt,String color){
    Color.colorTxt(txt,color);
  }
  **/
}









/**
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
**/
