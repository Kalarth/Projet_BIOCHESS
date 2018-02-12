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

    Plateau plateau=new Plateau(nbligne);
    initialisation(joueur_tab,nbligne,plateau,liste_M);
    plateau.AffichePlateau();
    tours(plateau,joueur_tab,liste_M);
  }


  public static void tours(Plateau plateau,Joueur[]tab, int [][] liste_M){
    int nbtours = 0;
    //for(;;){
      System.out.println("TOURS "+ nbtours);
      tourMetabolite(plateau, liste_M);

      System.out.println();
      plateau.AffichePlateau();
      tourJoueur(plateau,tab,0);
      tourJoueur(plateau,tab,1);
      AffichageScore(tab);
      nbtours = nbtours + 1;
      //System.exit(0);
    //}
  }

  public static void tourMetabolite(Plateau plateau,int [][] liste_M){
    System.out.println("Deplacement des metabolites");
    Random rand= new Random();
    int nb_bloque=0;
    int [] coordonee = new int [2];
    Piece courant;
    for (int i=0;i<liste_M.length;i++){
      coordonee = liste_M[i];
      courant=plateau.getPiece(coordonee[0],coordonee[1]);
      /**
      System.out.println(liste_M[i][0]+"x");
      System.out.println(liste_M[i][1]+"y");
      System.out.println(courant.getPos()[0]+courant.getPos()[1]);
      **/

      //System.out.println(plateau.getCase(coordonee[0],coordonee[1]));
      //System.out.println(courant.getSymbole());

      courant.randmove();
      int m=courant.getMovement();
      int [] direction=checkmovement(plateau,m,coordonee[0],coordonee[1]);
      boolean mouv_possible = false;
      for (int j=0;j<direction.length ;j++ ) {
        if (direction[j]==1) {
          mouv_possible=true;

        }
      }
      if (mouv_possible==true) {
        int x=0;
        while (x==0) {

          int test=rand.nextInt(direction.length);
          if (direction[test]==1) {
            if (test==0) { //VERS LE HAUT
              /**
              Piece p=new Lipide(0,0,"non","RESET");
              plateau.placerPiece(p);//MARCHE
              p.setNewpos(0,1);//MARCHE
              plateau.placerPiece(p);//MARCHE
              plateau.deletePiece(0,0);//MARCHE
              p=plateau.getPiece(0,1);//MARCHE
              p.setNewpos(0,0);//MARCHE
              plateau.placerPiece(p);//MARCHE
              **/
              System.out.println("Deplacement vers le haut");
              courant=plateau.getPiece(coordonee[0],coordonee[1]);
              bougerpiece(plateau,courant,(courant.getPos()[0]-m),courant.getPos()[1]);
              liste_M[i]=courant.getPos();
              /**
              courant.setNewpos(coordonee[0]-m,coordonee[1]);//MARCHE PAS
              plateau.placerPiece(courant);//MARCHE PAS
              plateau.deletePiece(coordonee[0],coordonee[1]);//MARCHE PAS
              liste_M[i]=courant.getPos();
              **/
              /**
              System.out.println("x"+liste_M[i][0]+"y"+liste_M[i][1]);
              System.out.println(courant.getSymbole());
              System.out.println(plateau.getCase(coordonee[0],coordonee[1]));
              System.out.println(plateau.getCase(liste_M[i][0],liste_M[i][1]));
              **/
            }
            if (test==1) { //VERS LA DROITE
              System.out.println("Deplacement vers la droite");
              /**
              courant.setNewpos(coordonee[0],coordonee[1]+m);
              plateau.placerPiece(courant);
              plateau.deletePiece(coordonee[0],coordonee[1]);
              liste_M[i]=courant.getPos();
              **/
              courant=plateau.getPiece(coordonee[0],coordonee[1]);
              bougerpiece(plateau,courant,courant.getPos()[0],(courant.getPos()[1]+m));
              liste_M[i]=courant.getPos();
              /**
              System.out.println("x"+liste_M[i][0]+"y"+liste_M[i][1]);
              System.out.println(courant.getSymbole());
              System.out.println(plateau.getCase(coordonee[0],coordonee[1]));
              System.out.println(plateau.getCase(liste_M[i][0],liste_M[i][1]));
              **/
            }
            if (test==2) { // VERS LE BAS
              System.out.println("Deplacement vers le bas");
              /**
              courant.setNewpos(coordonee[0]+m,coordonee[1]);
              plateau.placerPiece(courant);
              plateau.deletePiece(coordonee[0],coordonee[1]);
              liste_M[i]=courant.getPos();
              **/
              courant=plateau.getPiece(coordonee[0],coordonee[1]);
              bougerpiece(plateau,courant,(courant.getPos()[0]+m),courant.getPos()[1]);
              liste_M[i]=courant.getPos();
              /**
              System.out.println("x"+liste_M[i][0]+"y"+liste_M[i][1]);
              System.out.println(courant.getSymbole());
              System.out.println(plateau.getCase(coordonee[0],coordonee[1]));
                System.out.println(plateau.getCase(liste_M[i][0],liste_M[i][1]));
                **/
            }
            if (test==3) { //VERS LA GAUCHE
              /**
              System.out.println("Deplacement vers la gauche");
              courant.setNewpos(coordonee[0],coordonee[1]-m);
              plateau.placerPiece(courant);
              plateau.deletePiece(coordonee[0],coordonee[1]);
              liste_M[i]=courant.getPos();
              **/
              courant=plateau.getPiece(coordonee[0],coordonee[1]);
              bougerpiece(plateau,courant,(courant.getPos()[0]),courant.getPos()[1]-m);
              liste_M[i]=courant.getPos();
              /**
              System.out.println("x"+liste_M[i][0]+"y"+liste_M[i][1]);
              System.out.println(courant.getSymbole());
              System.out.println(plateau.getCase(coordonee[0],coordonee[1]));
              System.out.println(plateau.getCase(liste_M[i][0],liste_M[i][1]));
              **/

            }
            //plateau.AffichePlateau();
            x=1;
          }
        }
      }
      else{
        nb_bloque++;
      }
    } // fin for
    plateau.AffichePlateau();


    if (nb_bloque==liste_M.length) {
      for (int l=0;l<liste_M.length;l++){
        coordonee=liste_M[l];
        Piece p=plateau.getPiece(coordonee[0],coordonee[1]);
        int [] listexy=casevide_aleatoire(plateau);
        p.setNewpos(listexy[0],listexy[1]);
        plateau.placerPiece(p);
        plateau.deletePiece(coordonee[0],coordonee[1]);
        liste_M[l]=listexy;
      }
    }

  }

  public static void bougerpiece(Plateau plateau,Piece piece,int x,int y){
    int [] oldpos = piece.getPos();
    plateau.deletePiece(oldpos[0],oldpos[1]);
    System.out.println("oldposx"+oldpos[0]+"oldposy"+oldpos[1]);
    piece.setNewpos(x,y);
    System.out.println("newposx"+x+"newposy"+y);
    plateau.placerPiece(piece);

  }

  public static int [] casevide_aleatoire(Plateau plateau){
    for(;;){
      Random rand=new Random();
      int randx=rand.nextInt(15);
      int randy=rand.nextInt(15);
      if (plateau.getCase(randx,randy) == " ") {
        int [] liste={randx,randy};
        return liste;
      }
    }
  }

  public static int [] checkmovement(Plateau plateau,int mouvement,int x, int y) {
    int dir1=1;
    int dir2=1;
    int dir3=1;
    int dir4=1;

    for (int i=1;i<=mouvement ;i++) {
      if (plateau.getCase(x-i,y)!=" ") {
        dir1=0;
      }
      if (plateau.getCase(x,y+i)!=" ") {
        dir2=0;

      }
      if (plateau.getCase(x+i,y)!=" ") {
        dir3=0;

      }
      if (plateau.getCase(x,y-i)!=" ") {
        dir4=0;

      }
    }

    int [] liste = {dir1,dir2,dir3,dir4};
    return liste;
  }

  public static void tourJoueur(Plateau plateau,Joueur[] joueur_tab,int i){
    System.out.println(joueur_tab[i].getNomJoueur()+" joue");
    Piece piece_courant=selection_case(plateau,joueur_tab[i]);
    if (piece_courant.getSymbole()=="L") {
      mouvement_lipide(plateau,piece_courant,joueur_tab,i);
    }
    if (piece_courant.getSymbole()=="E") {
      mouvement_enzyme(plateau,piece_courant,joueur_tab,i);
    }

    VerifVictoire(joueur_courant);
    //CleanTerminal();
    plateau.AffichePlateau();
  }

  public static Piece selection_piece(Plateau plateau,Joueur joueur){
    while {
      System.out.println("Saisissez le numero de la ligne");
      int x=Saisie_entier();
      System.out.println("Saisissez le numero de la colonne");
      int y=Saisie_entier();
      if (x>=0 && x<=plateau.getNb()-1 && y>=0 && y<=plateau.getNb()-1) {
        Piece p=plateau.getPiece();
        if (p.getSymbole()=="L" || p.getSymbole()=="E") {
          if (p.getProprio()==joueur.getNomJoueur()) {
            return p
          }
          else {
            System.out.println("Erreur,cette piece ne vous appartiens pas.")
          }
        }
        else {
          System.out.println("Erreur, vous ne pouvez pas bouger cette piece.");
        }
      }
      else {
        System.out.println("Erreur, la case n'est pas dans le tableau.");
      }


    }
  }

  public static void mouvement_enzyme(Plateau plateau,Piece piecet,Joueur [] joueur_tab,int i){
    pos [] = piece.getPos();
    System.out.println("Dans quelle direction voulez vous déplacer cette Enzyme ?");
    System.out.println("1 : Vers le haut");
    System.out.println("2 : Vers la droite");
    System.out.println("3 : Vers le bas");
    System.out.println("4 : Vers la gauche");
    int rep=Saisie_entier();
    switch (rep) {
      case 1: enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]-1),pos[1]); return;
      case 2: enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]),pos[1]+1); return;
      case 3: enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]+1),pos[1]); return;
      case 4: enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]),pos[1]-1); return;
      default: System.out.println("reponse incorrecte");
    }
  }

  public static void enzyme_manger(Plateau plateau,Piece enzyme,Joueur [] joueur_tab,int i,int x,int y){
    if (plateau.getCase(x,y)=="M" && plateau.getPiece().getCouleur()==piece.getCouleur()) {
      enzyme.incremente_capa();
      joueur_tab[i].ScorePlusUn();
    }
    bougerpiece(Plateau plateau,Piece piece,int x,int y);
  }

  public static void mouvement_lipide(Plateau plateau,Piece piece,Joueur[] joueur_tab,int i){
    while{
      System.out.println("de combien de cases voulez vous déplacer ce lipide (1 a 3) ?");
      int nb_case=Saisie_entier();
      if (nb_case<=3 && nb>=1) {
        int [] pos = piece.getPos();
        int [] direction = checkmovement(plateau,nb_case,pos[0], pos[1]);
        if (i==0) {
          if (direction[2]==1) {
            bougerpiece(plateau,piece,(x+nb_case),y);
            return;
          }
          else {
            System.out.println("Erreur,la case cible est inaccessible.");
          }
        }
        if (i==1) {
          if (direction[0]==1) {
            bougerpiece(plateau,piece,(x-nb_case),y);
            return;
          }
          else {
            System.out.println("Erreur,la case cible est inaccessible.");
          }
        }
      }
      else {
        System.out.println("De un a trois on vous a dit !");
      }
    }

  }

  public static void AffichageScore(Joueur[] tab){
    for( int i = 0 ; i < tab.length ; i++){
      tab[i].AfficheScore();
    }
  }

  public static void VerifVictoire(Joueur joueur){
      joueur.Victoire();

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

  public static void ajout_metabolites(Plateau plateau,int [][] liste_M,int limit){
    int limitecouleur=0;
    int k=0;
    String [] liste_couleur = {"RED","YELLOW","GREEN","BLUE"};
    int i=0;

    while (i != limit){
      int [] position = casevide_aleatoire(plateau);
      if (position[0]>=4 && position[0]<=10) {
        //System.out.println("ok");
        Piece p= new Metabolite(position,liste_couleur[k]);
        plateau.placerPiece(p);
        liste_M[i]=position;
        limitecouleur++;
        i++;
        if (limitecouleur == 10) {
          k++;
          limitecouleur=0;
        }
      }
    }
  }
    /**
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
    **/








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
