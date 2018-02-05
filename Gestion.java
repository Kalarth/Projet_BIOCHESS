
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
