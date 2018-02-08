import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.lang.*;


public class Gestion{
  public static void main(String[] args) {
    int nbligne=15;
    int [] [] liste_M = new int [40] [2];

    Joueur[] joueur_tab=initJoueur();
    Color.setColors();

    Plateau plat=new Plateau(nbligne);
    initialisation(joueur_tab,nbligne,plat,liste_M);
    plat.AffichePlateau();
    tours(plat,joueur_tab,liste_M);
  }


  public static void tours(Plateau plateau,Joueur[]tab, int [][] liste_M){
    int nbtours = 0;
    for(;;){
      System.out.println("TOURS "+ nbtours);
      tourMetabolite(plateau, liste_M);
      tourJoueur(plateau,tab);
      AffichageScore(tab);
      nbtours = nbtours + 1;
      //System.exit(0);
    }
  }

  public static void tourMetabolite(Plateau plateau,int [][] liste_M){
    System.out.println("Deplacement des metabolites");
    for (int i=0;i<liste_M.length;i++){
      int nb_bloque=0;
      int [] coordonee = liste_M[i];
      Piece courant=plateau.getPiece(coordonee[0],coordonee[1]);
      courant.randmove();
      int m=courant.getMovement();
      int [] direction=checkmovement(plateau,m,coordonee[0],coordonee[1]);
      boolean mouv_possible = false;
      for (int j=0;j<direction.length ;j++ ) {
        if (direction[j]==1) {
          mouv_possible=true;
          break;
        }
      }
      if (mouv_possible==true) {
        int x=0;
        while (x==0) {
          Random rand= new Random();
          int test=rand.nextInt(direction.length);
          if (direction[test]==1) {
            if (test==0) { //VERS LE HAUT
              courant.setNewpos(coordonee[0]-m,coordonee[1]);
              plateau.placerPiece(courant);
              plateau.deletePiece(coordonee[0],coordonee[1]);
              liste_M[i][0]=liste_M[i][0]-m;
            }
            if (test==1) { //VERS LA DROITE
              courant.setNewpos(coordonee[0],coordonee[1]+m);
              plateau.placerPiece(courant);
              plateau.deletePiece(coordonee[0],coordonee[1]);
              liste_M[i][1]=liste_M[i][1]+m;
            }
            if (test==2) { // VERS LE BAS
              courant.setNewpos(coordonee[0]+m,coordonee[1]);
              plateau.placerPiece(courant);
              plateau.deletePiece(coordonee[0],coordonee[1]);
              liste_M[i][0]=liste_M[i][0]+m;
            }
            if (test==1) { //VERS LA GAUCHE
              courant.setNewpos(coordonee[0],coordonee[1]-m);
              plateau.placerPiece(courant);
              plateau.deletePiece(coordonee[0],coordonee[1]);
              liste_M[i][1]=liste_M[i][1]-m;
            }
          }
        }
      }
      else{
        nb_bloque++;
      }
      plateau.AffichePlateau();






    }
  }

  public static int [] checkmovement(Plateau plateau,int mouvement,int x, int y){
    int dir1=1;
    int dir2=1;
    int dir3=1;
    int dir4=1;

    for (int i=1;i==mouvement ;i++ ) {
      if (plateau.getCase(x-i,y)!=" ") {
        dir1=0;
      }
      if (plateau.getCase(x,y+i)!=" ") {
        dir2=0;

      }
      if (plateau.getCase(x+i,y)!=" ") {
        dir2=0;

      }
      if (plateau.getCase(x,y-i)!=" ") {
        dir2=0;

      }
    }
    int [] liste = {dir1,dir2,dir3,dir4};
    return liste;


  }

  public static void tourJoueur(Plateau plateau,Joueur[] tab){
    System.out.println(tab[0].getNomJoueur()+" joue");
    VerifVictoire(tab,0);
    //CleanTerminal();
    plateau.AffichePlateau();
    System.out.println(tab[1].getNomJoueur()+" joue");
    VerifVictoire(tab,1);
    //CleanTerminal();
    plateau.AffichePlateau();
  }

  public static void AffichageScore(Joueur[] tab){
    for( int i = 0 ; i < tab.length ; i++){
      tab[i].AfficheScore();
    }
  }

  public static void VerifVictoire(Joueur[]tab,int i){
      tab[i].Victoire();

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

  public static void initialisation(Joueur [] tab, int nbligne, Plateau plat, int [] [] liste_M){
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
    ajout_metabolites(plat,liste_M,40);
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

  public static void ajout_metabolites(Plateau plat,int [][] liste_M,int limit){
    int k=0;
    int limitecouleur=0;
    String [] liste_couleur = {"RED","YELLOW","GREEN","BLUE"};
    Random rand=new Random();
    String couleur="";
    for (int i=0;i<limit ;i++ ) {
      int randx=rand.nextInt(6)+4;
      int randy=rand.nextInt(15);
      if (plat.getCase(randx,randy) == " ") {
        Piece p= new Metabolite(randx,randy,liste_couleur[k]);
        plat.placerPiece(p);
        liste_M[i]=p.getPos();

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
