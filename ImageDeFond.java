/**
* Cette classe gere l'image de fond pour les menus
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class ImageDeFond extends JPanel{
  //Image de fond de menu
  private Image fond;

  /**
  *Initialise l'image
  */
  public ImageDeFond(Image fond){
    this.fond=fond;
  }

  /**
  * Permet de generer l'image d'arriere plan d'un menu
  *
  * @param pinceau
  */
  @Override
  protected void paintComponent(Graphics pinceau){
    // obligatoire : on crée un nouveau pinceau pour pouvoir le modifier plus tard
    Graphics secondPinceau = pinceau.create();
    // obligatoire : si le composant n'est pas censé être transparent
    if(this.isOpaque()){
          // obligatoire : on repeint toute la surface avec la couleur de fond
          secondPinceau.setColor(this.getBackground());
          secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    //On dessine ce qu'on veut
    secondPinceau.drawImage(this.fond, 0,0,this.getWidth(), this.getHeight(), null, this);
  }
}
