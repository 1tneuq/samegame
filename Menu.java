/**
* Cette classe affiche le menu de depart du jeu
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

public class Menu extends JFrame{
  private JButton bouton1;
  private JButton bouton2;
  private BoutonMenu btm;

  /**
  * Construit la fenetre
  */
  public Menu(){
    super("SameGame - Lacombe & Meddahi");
	  Image fond=Toolkit.getDefaultToolkit().getImage("./Images/menu.png");
	  this.setContentPane(new ImageDeFond(fond));
	  this.setLayout(null);
    this.bouton1=new JButton("Aleatoire");
    this.bouton2=new JButton("Fichier");
    this.btm=new BoutonMenu(this);
    bouton1.addActionListener(btm);
	  bouton2.addActionListener(btm);

	  try {
		   GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		   ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Elderberry.ttf")));
	  }catch(IOException|FontFormatException e) {
		   System.out.println("erreur de police: "+e.getMessage());
	  }
	  Font f=new Font("Elderberry", Font.BOLD, 26);


	  bouton1.setContentAreaFilled(false);
	  bouton1.setBorderPainted(false);
	  bouton1.setFocusPainted(false);
	  bouton1.setFont(f);
	  bouton1.setBounds(62,292,260,95);

	  bouton2.setContentAreaFilled(false);
	  bouton2.setBorderPainted(false);
	  bouton2.setFocusPainted(false);
	  bouton2.setFont(f);
	  bouton2.setBounds(440,292,260,95);

	  this.add(bouton1);
	  this.add(bouton2);

    this.setSize(770, 570);
    this.setLocation(100, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);
  }
  /**
  *Ouvre le menu
  * @param args la liste des arguments de la ligne de commande (inutilisée ici)
  */
  /*public static void main(String[] args){
    Menu menu=new Menu();
  }*/
}
