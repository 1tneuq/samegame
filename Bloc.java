/**
* Cette classe represente un bloc de couleur qui peut etre rouge, vert ou bleu
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Bloc extends JPanel{
  //entier qui definit la couleur d'un bloc
  private int couleur;
  //image d'un bloc
  private Image rvb;

  /**
  * Construit un bloc
  *
  * @param color; en fonction de ce parametre, la couleur d'un bloc est affectee
  */
  public Bloc(int color){
    super();
    this.couleur=color;
    if(color==0){
      this.rvb = Toolkit.getDefaultToolkit().getImage("./Images/rouge.png");
    }else if(color==1){
      this.rvb = Toolkit.getDefaultToolkit().getImage("./Images/vert.png");
    }else if(color==2){
      this.rvb = Toolkit.getDefaultToolkit().getImage("./Images/bleu.png");
    }
  }

  /**
  * Permet de generer l'image d'un bloc
  *
  * @param pinceau
  */
  @Override
  protected void paintComponent(Graphics pinceau){
    // obligatoire : on crée un nouveau pinceau pour pouvoir le modifier plus tard
    Graphics secondPinceau = pinceau.create();
    // obligatoire : si le composant n'est pas censé être transparent
    if (this.isOpaque()){
      	// obligatoire : on repeint toute la surface avec la couleur de fond
     secondPinceau.setColor(this.getBackground());
     secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
   }
    //On dessine ce qu'on veut
   secondPinceau.drawImage(this.rvb, 0, 0, this);

 }

}
