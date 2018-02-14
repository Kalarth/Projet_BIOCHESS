import java.util.*;
import java.io.*;
import java.nio.file.*;


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
    for(;;){
      System.out.println("TOURS "+ nbtours);
      tourMetabolite(plateau, liste_M);

      System.out.println();

      tourJoueur(plateau,tab,0);

      tourJoueur(plateau,tab,1);

      AffichageScore(tab);
      //System.exit(0);
    }
  }

  public static void tourMetabolite(Plateau plateau,int [][] liste_M){
    System.out.println("Deplacement des metabolites");
    Random rand= new Random();
    int nb_bloque=0;
    int cpt=0;
    int [] coordonee = new int [2];
    Piece courant;
    for (int i=0;i<plateau.getNb();i++){
      for (int k=0;k<plateau.getNb();k++){
          coordonee[0]=i;
          coordonee[1]=k;
          courant=plateau.getPiece(coordonee[0],coordonee[1]);
          if (courant.getSymbole()=="M") {
            cpt++;
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
                    courant=plateau.getPiece(coordonee[0],coordonee[1]);
                    bougerpiece(plateau,courant,(courant.getPos()[0]-m),courant.getPos()[1]);

                  }
                  if (test==1) { //VERS LA DROITE
                    courant=plateau.getPiece(coordonee[0],coordonee[1]);
                    bougerpiece(plateau,courant,courant.getPos()[0],(courant.getPos()[1]+m));

                  }
                  if (test==2) { // VERS LE BAS
                    courant=plateau.getPiece(coordonee[0],coordonee[1]);
                    bougerpiece(plateau,courant,(courant.getPos()[0]+m),courant.getPos()[1]);

                  }
                  if (test==3) { //VERS LA GAUCHE

                    courant=plateau.getPiece(coordonee[0],coordonee[1]);
                    bougerpiece(plateau,courant,(courant.getPos()[0]),courant.getPos()[1]-m);

                  }
                  x=1;
                }
              }
            }
            else{
              nb_bloque++;
            }

        }
      }
    } // fin for
    plateau.AffichePlateau();


    if (nb_bloque==cpt) {
      for (int l=0;l<cpt;l++){
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
    piece.setNewpos(x,y);
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
    plateau.mass_switch(joueur_tab[i].getNomJoueur());
    plateau.AffichePlateau();
    System.out.println(joueur_tab[i].getNomJoueur()+" joue");
    System.out.println("Les pieces de votre adversaire sont colorées en blanc.");
    Piece piece_courant=selection_piece(plateau,joueur_tab[i]);
    if (piece_courant.getSymbole()=="L") {
      mouvement_lipide(plateau,piece_courant,joueur_tab,i);
    }
    if (piece_courant.getSymbole()=="E") {
      mouvement_enzyme(plateau,piece_courant,joueur_tab,i);
    }

    VerifVictoire(joueur_tab[i]);
    //CleanTerminal();
    plateau.mass_switch(joueur_tab[i].getNomJoueur());
    plateau.AffichePlateau();
  }

  public static Piece selection_piece(Plateau plateau,Joueur joueur){
    for(;;) {
      System.out.println("Saisissez le numero de la ligne");
      int x=Saisie_entier();
      System.out.println("Saisissez le numero de la colonne");
      int y=Saisie_entier();
      boolean flag=verifier_limite(plateau,x-1,y-1);
      if (flag==true) {
        Piece p=plateau.getPiece(x-1,y-1);
        if (p.getSymbole()=="L" || p.getSymbole()=="E") {
          if (p.getProprio()==joueur.getNomJoueur()) {
            return p;
          }
          else {
            System.out.println("Erreur,cette piece ne vous appartiens pas.");
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

  public static boolean verifier_limite(Plateau plateau,int x, int y){
    boolean flag=false;
    if (x>=0 && x<=plateau.getNb()-1 && y>=0 && y<=plateau.getNb()-1) {
      flag=true;
    }
    return flag;

  }

  public static void mouvement_enzyme(Plateau plateau,Piece piece,Joueur [] joueur_tab,int i){
    boolean flag;
    int [] pos = piece.getPos();
    for(;;){
      System.out.println("Dans quelle direction voulez vous déplacer cette Enzyme ?");
      System.out.println("1 : Vers le haut");
      System.out.println("2 : Vers la droite");
      System.out.println("3 : Vers le bas");
      System.out.println("4 : Vers la gauche");
      int rep=Saisie_entier();
      switch (rep) {
        case 1:
        flag=verifier_limite(plateau,pos[0]-1,pos[1]);
        if (flag==true) {
          enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]-1),pos[1]); return;
        }
        else{
          System.out.println("La case cible est hors du tableau");
          break;
        }
        case 2:
        flag=verifier_limite(plateau,pos[0],pos[1]+1);
        if (flag==true) {
          enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]),pos[1]+1); return;
        }
        else{
          System.out.println("La case cible est hors du tableau");
          break;
        }
        case 3:
        flag=verifier_limite(plateau,pos[0]+1,pos[1]);
        if (flag==true) {
          enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]+1),pos[1]); return;
        }
        else{
          System.out.println("La case cible est hors du tableau");
          break;
        }
        case 4:
        flag=verifier_limite(plateau,pos[0],pos[1]-1);
        if (flag==true) {
          enzyme_manger(plateau,piece,joueur_tab,i,(pos[0]),pos[1]-1); return;
        }
        else{
          System.out.println("La case cible est hors du tableau");
          break;
        }
        default: System.out.println("reponse incorrecte");
      }
    }
  }

  public static void enzyme_manger(Plateau plateau,Piece enzyme,Joueur [] joueur_tab,int i,int x,int y){
    if (plateau.getCase(x,y)=="M" && plateau.getPiece(x,y).getCouleur()==enzyme.getCouleur()) {
      enzyme.incremente_capa();
      joueur_tab[i].ScorePlusUn();
    }
    bougerpiece(plateau,enzyme,x,y);
  }

  public static void mouvement_lipide(Plateau plateau,Piece piece,Joueur[] joueur_tab,int i){
    for(;;){
      System.out.println("de combien de cases voulez vous déplacer ce lipide (1 a 3) ?");
      int nb_case=Saisie_entier();
      if (nb_case<=3 && nb_case>=1) {
        int [] pos = piece.getPos();
        int [] direction = checkmovement(plateau,nb_case,pos[0], pos[1]);
        if (i==0) {
          if (direction[2]==1) {
            bougerpiece(plateau,piece,(pos[0]+nb_case),pos[1]);
            return;
          }
          else {
            System.out.println("Erreur,la case cible est inaccessible.");
          }
        }
        if (i==1) {
          if (direction[0]==1) {
            bougerpiece(plateau,piece,(pos[0]-nb_case),pos[1]);
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
          Piece p=new Lipide(i,j,tab[0].getNomJoueur(),"CYAN");
          plat.placerPiece(p);
        }

        if ((j>1 && j<14) && j%2==0 && i==13 ) {
          Piece p=new Lipide(i,j,tab[1].getNomJoueur(),"CYAN");
          plat.placerPiece(p);
        }

        if (j%2==1 && i==2 ) {
          Piece p=new Lipide(i,j,tab[0].getNomJoueur(),"CYAN");
          plat.placerPiece(p);
        }

        if (j%2==1 && i==12 ) {
          Piece p=new Lipide(i,j,tab[1].getNomJoueur(),"CYAN");
          plat.placerPiece(p);
        }

        if (j>1 && j%2==0 && i==3 ) {
          Piece p=new Lipide(i,j,tab[0].getNomJoueur(),"CYAN");
          plat.placerPiece(p);
        }

        if (j>1 && j%2==0 && i==11 ) {
          Piece p=new Lipide(i,j,tab[1].getNomJoueur(),"CYAN");
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


  public static String Saisie_chaine(){
    Scanner input = new Scanner(System.in);
    String n = input.next();
    return n;
  }

  public static int Saisie_entier(){
    int nb=0;
    for (;;){
      try {
        Scanner intput = new Scanner(System.in);
        nb = intput.nextInt();
        return nb;
      }
      catch(InputMismatchException exception){
        System.out.println("Veuillez entrer un nombre");
      }
    }
  }
}
