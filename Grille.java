/**
* Cette classe gere gère le panneau de grille, entre autres son initialisation aléatoire ou en se basant sur un fichier grâce à, ainsi que sa mise à jour apres chaque clic l'organisation des espaces blancs et la fin de la partie
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Grille extends JPanel{
  //Tableau de caracteres qui represente la grille; R=rouge, V=vert et B=bleu
  protected static char[][] tabGrille;
  //Tableau de panneaux qui representent la grille "physiquement"
  protected JPanel[][] tabPano;
  //Entier qui represente la grille aleatoire ou la lecture par fichier
  private int choix;
  //Score de la partie
  private Score score;
  //Fichier de grille predefinie
  private File file;
  //Fenetre de jeu
  private Samegame sg;

  /**
  *Construit la Grille
  */
  public Grille(Samegame sg, File file){
    super();
	  this.file=file;
	  this.sg=sg;
    this.tabGrille=new char[10][15];
    this.tabPano=new JPanel[10][15];
	  score=new Score();
	  this.setLayout(new GridLayout(10,15,0,0));
    if(file==null){
      this.initGrille();
    }else{
	    if(!lireFichier()){
        return;
	    }
      this.dessinerGrille(this.tabGrille);
    }
    //this.afficherGrille();
  }

  /**
  *Lit le fichier .txt de grille et copie son contenu dans tabGrille
  */
	public boolean lireFichier(){
		try{
			BufferedReader in=new BufferedReader(new FileReader(this.file));
			String ligne;
			int l=0;
			while((ligne=in.readLine())!=null){
				for(int i=0; i<15; i++){
					this.tabGrille[l][i]=ligne.charAt(i);
				}
				l++;
			}
			in.close();
		}catch(Exception e){
			System.out.println("error: "+e.getMessage());
			this.initGrille();
			return false;
		}
		return true;
	}

  /**
  *Initialise et dessine la grille aleatoirement
  */
	public void initGrille(){
		Random aleat = new Random();
		int nbAleat;
		for(int i = 0;i<10;i++) {
			for(int j=0; j<15; j++) {
				nbAleat = aleat.nextInt(3);
				this.tabPano[i][j]=new Bloc(nbAleat);
				this.add(this.tabPano[i][j], BorderLayout.CENTER);
				if(nbAleat==0){
					this.tabGrille[i][j]='R';
				}else if(nbAleat==1){
					this.tabGrille[i][j]='V';
				}else{
					this.tabGrille[i][j]='B';
				}
			}
		}
		GestionnaireSouris gest=new GestionnaireSouris(this, score);
	}

  /**
  *Permet de dessiner/mettre a jour la grille a partir d'un tableau de caractere, donc d'un fichier
  *
  * @param tableau un tableau de caracteres qui represente une grille et ses blocs de couleur R,V,B
  */
  public void dessinerGrille(char[][] tableau){
    if(this.testFin()==0){
      System.out.println("Pas fini");
    }else if(this.testFin()==1){
      System.out.println("Fin de la partie");
	  this.sg.dispose();
	  MenuFin mf = new MenuFin(score.getScore());
    }

    this.removeAll();
    this.updateUI();
    this.reorgaGrille();
    for (int i=0; i<10; i++) {
        for (int j=0; j<15; j++) {
          if(this.tabGrille[i][j]=='R'){
            this.tabPano[i][j]=new Bloc(0);
            this.add(this.tabPano[i][j], BorderLayout.CENTER);
          }else if(this.tabGrille[i][j]=='V'){
            this.tabPano[i][j]=new Bloc(1);
            this.add(this.tabPano[i][j], BorderLayout.CENTER);
          }else if(this.tabGrille[i][j]=='B'){
            this.tabPano[i][j]=new Bloc(2);
            this.add(this.tabPano[i][j], BorderLayout.CENTER);
          }else{
            this.add(new JPanel());
          }
        }
    }
    GestionnaireSouris gest1=new GestionnaireSouris(this, score);
  }

  /*public void test(){
    this.tabGrille[5][5]='W';
  }*/

  /**
  *Affiche le tableau de char sur le terminal pour verifier que tout se passe bien
  */
  public void afficherGrille(){
    for (int i=0; i<10; i++) {
      System.out.println(" ");
        for (int j=0; j<15; j++) {
          System.out.print(this.tabGrille[i][j]);
        }
    }
    System.out.println("");
  }

  /**
  *Reorganise la grille, en liberant les espaces blancs et en decalant a gauche si une colonne est vide
  *C'est moche et bourrin comme technique donc si on a du temps en rab on change ca
  */
  public void reorgaGrille(){
    for(int x=0;x<15;x++){
      for(int i=9;i>0;i--){
        for(int j=14;j>=0;j--){
          if(this.tabGrille[i][j]=='W'){
            this.tabGrille[i][j]=this.tabGrille[i-1][j];
            this.tabGrille[i-1][j]='W';
          }
        }
      }
    }
    for(int x=0;x<15;x++){
      for(int j=0;j<14;j++){
        int compteur=0;
        for(int i=0;i<10;i++){
          if(this.tabGrille[i][j]=='W'){
            compteur++;
          }
        }
        if(compteur==10){
          for(int i=0;i<10;i++){
            this.tabGrille[i][j]=this.tabGrille[i][j+1];
            this.tabGrille[i][j+1]='W';
          }
        }
      }
    }
  }

	/**
	*renvoie 0 quand le jeu n'est pas fini, 1 quand la partie est finie
	*/
	public int testFin(){
		int val;
		for(int i=0; i<10; i++){
			for(int j=0; j<15; j++){
				val=this.tabGrille[i][j];
				if(val!='W'){
					if((j+1<15 && this.tabGrille[i][j+1]==val) ||
					   (j-1>=0 && this.tabGrille[i][j-1]==val) ||
					   (i+1<10 && this.tabGrille[i+1][j]==val) ||
					   (i-1>=0 && this.tabGrille[i-1][j]==val)){
               return 0;
					}
				}
			}
		}
		return 1;
	}

  /**
  *renvoie le tableau de caracteres
  */
  public char[][] getGrille(){
    return this.tabGrille;
  }

  /**
  *renvoie le tableau panneaux
  */
  public JPanel[][] getPanos(){
    return this.tabPano;
  }

  /**
  *renvoie le score de la partie
  */
  public Score getScore(){
	  return this.score;
  }
}
