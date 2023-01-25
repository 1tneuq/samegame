/**
* Cette classe gere le score du jeu
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Score extends JPanel{
  private int score;
  private JLabel lab;
  public Score(){
    super();
    this.setPreferredSize(new Dimension(770, 40));
	  this.score = 0;
	  this.lab = new JLabel("Score actuel: " + this.score);
	  this.lab.setFont(lab.getFont().deriveFont(24.0f));
	  this.add(lab);
  }

  /**
	*update le score. Prend en argument le nombre de case du groupe supprimé
  */
  public void majScore(int nbGroup){
	  this.score += Math.pow(nbGroup-2,2);
    System.out.println("");
    System.out.println("Score actuel: " + this.score);
    this.lab.setText("Score actuel: " + this.score);
  }

  /**
	*Renvoie le score
  */
  public int getScore(){
	  return this.score;
  }
}
