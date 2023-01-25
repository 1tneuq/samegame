/**
* Cette classe represente la fenetre qui contient les elements du jeu
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Samegame extends JFrame{
  //Attribut qui est un panneau avec le score dessus
  private Score score;
  //Attribut qui est une grille
  protected Grille grille;

  public Samegame(File file){
    super("SameGame - Lacombe & Meddahi");
    grille=new Grille(this, file);
    this.setSize(770, 570);
    this.setLocation(100, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.add(grille.getScore(), BorderLayout.NORTH);
    this.add(grille, BorderLayout.CENTER);
    this.setVisible(true);
  }

  /*public static void main(String[] args){
    Samegame fenetre=new Samegame();
  }*/
}
