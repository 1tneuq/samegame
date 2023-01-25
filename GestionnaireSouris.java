/**
* Cette classe gere le fonctionnement du jeu grace a la souris
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class GestionnaireSouris implements MouseListener{
  //Grille de jeu
  private Grille grille;
  //Tableau constitue de 0 et de 1; lorsqu'un groupe est survole, a la position des panneaux du groupe, les elements de tabGroupes passent a 1
  private int[][] tabGroupes;
  //entier qui contient la taille d'un groupe survole
  private int tailleGroupes;
  //score de la partie
  private Score score;

  /**
  *Construit les attributs et place un observateur sur chacun des panneaux de la grille
  *
  * @param args la liste des arguments de la ligne de commande (inutilisée ici)
  */
  public GestionnaireSouris(Grille grillee, Score score){
    this.grille=grillee;
    this.tabGroupes=new int[10][15];
    this.tailleGroupes=0;
    this.initTabGroupes();
	  this.score = score;
    for(int i = 0;i<10;i++){
      for(int j = 0;j<15;j++){
        this.grille.tabPano[i][j].addMouseListener(this);
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent evenement){
    //System.out.println("TEST clic");
    for(int i = 0;i<10;i++){
      for(int j = 0;j<15;j++){
        if(this.tabGroupes[i][j]==1 && this.tailleGroupes>1){
          this.grille.tabGrille[i][j]='W';
        }
      }
    }
	if(this.tailleGroupes > 1){
		this.score.majScore(this.tailleGroupes);
	}
    this.grille.dessinerGrille(this.grille.tabGrille);
    //this.grille.afficherGrille();
  }

  /**
  *Gere le survolement des elements de la grille; des qu'un panneau est survole, la fonction pour regarder les panneaux adjacents est appelee et l'arriere plan des panneaux du groupe sont surlignes
  *
  * @param evenement qui permet de savoir quel panneau en particulier est survole grace a getSource()
  */
  @Override
  public void mouseEntered(MouseEvent evenement){
    for(int i = 0;i<10;i++){
      for(int j = 0;j<15;j++){
        if(evenement.getSource()==this.grille.tabPano[i][j] && this.grille.tabGrille[i][j]!='W'){
          //this.grille.tabPano[i][j].setBackground(Color.YELLOW);
          this.trouveGroupes(i,j);
        }
        for(int a = 0;a<10;a++){
          for(int b = 0;b<15;b++){
            if(this.tabGroupes[a][b]==1){
              this.grille.tabPano[a][b].setBackground(Color.YELLOW);
            }
          }
        }
        //if(this.tabGroupes[i][j]==1){
          //this.grille.tabPano[i][j].setBackground(Color.YELLOW);
        //}
      }
    }
    //this.afficherGroupes();
  }

  /**
  *Remet la couleur d'arriere plan initiale des panneaux d'un groupe lorsque plus aucun element du groupe n'est survole par la souris; fait egelement appel a la fonction qui reset le tableau d'entier
  *
  * @param evenement qui n'est pas utilise ici
  */
  @Override
  public void mouseExited(MouseEvent evenement){
    Color fond = new Color(238,238,238);
    for(int i = 0;i<10;i++){
      for(int j = 0;j<15;j++){
        //if(this.grille.tabGrille[i][j]=='B'){
          this.grille.tabPano[i][j].setBackground(fond);
        //}
      }
    }
    this.initTabGroupes();
    this.tailleGroupes=0;
  }

  /**
  *Ne sert a rien
  */
  @Override
  public void mousePressed(MouseEvent evenement){

  }

  /**
  *Ne sert a rien
  */
  @Override
  public void mouseReleased(MouseEvent evenement){

  }
  /**
  *Identifie les groupes et initialise le tableau d'entiers tabGroupe a 1 pour chaque coordonne de panneau du groupe
  *
  *Pour chacun des panneaux dont les coordonnees sont entrees, on regarde si le panneau aux coordonnes a gauche, en haut, a droite et au dessus (s'il existe) est de la meme couleur,
  * et si oui on utilise la recursivite pour aller jusqu'aux extremites de chaque groupe
  *
  * @param x et y qui coorespondent aux coordonnes d'un panneau
  */
  public void trouveGroupes(int x, int y){
    if(x-1>=0 && this.grille.tabGrille[x][y]==this.grille.tabGrille[x-1][y] && this.testGroupe(x-1,y)==false){
      this.tabGroupes[x-1][y]=1;
      this.trouveGroupes(x-1,y);
      this.tailleGroupes++;
    }
    if(y-1>=0 && this.grille.tabGrille[x][y]==this.grille.tabGrille[x][y-1] && this.testGroupe(x,y-1)==false){
      this.tabGroupes[x][y-1]=1;
      this.trouveGroupes(x,y-1);
      this.tailleGroupes++;
    }
    if(x+1<10 && this.grille.tabGrille[x][y]==this.grille.tabGrille[x+1][y] && this.testGroupe(x+1,y)==false){
      this.tabGroupes[x+1][y]=1;
      this.trouveGroupes(x+1,y);
      this.tailleGroupes++;
    }
    if(y+1<15 && this.grille.tabGrille[x][y]==this.grille.tabGrille[x][y+1] && this.testGroupe(x,y+1)==false){
      this.tabGroupes[x][y+1]=1;
      this.trouveGroupes(x,y+1);
      this.tailleGroupes++;
    }
  }

  /**
  *Ititialise/reset tout le tableau d'entiers tabGroupes avec la valeur 0
  */
  public void initTabGroupes(){
    for(int i = 0;i<10;i++){
      for(int j = 0;j<15;j++){
        this.tabGroupes[i][j]=0;
      }
    }
  }

  /**
  *Verifie si un element de tabGroupes a deja ete place a 1
  */
  public boolean testGroupe(int x, int y){
    if(this.tabGroupes[x][y]==1){
      return true;
    }
    return false;
  }

  /**
  *Affiche le tableau de groupes et le nombre d'elements du groupe pour voir si tout se passe bien
  */
  public void afficherGroupes(){
    for (int i=0; i<10; i++) {
      System.out.println(" ");
        for (int j=0; j<15; j++) {
          System.out.print(this.tabGroupes[i][j]);
        }
    }
    System.out.println("");
    System.out.println("Taille du groupe: "+tailleGroupes);
  }

  //public static void main(String[] args){
    //GestionnaireSouris test=new GestionnaireSouris();
  //}

}
