/**
* Cette classe represente le menu de fin du jeu
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

public class MenuFin extends JFrame{
  //Bouton rejouer
  private JButton bouton1;
  //Bouton quitter
  private JButton bouton2;

  /**
  * Construit la fenetre
  */
  public MenuFin(int score){
    super("SameGame - Lacombe & Meddahi");
    Image fond=Toolkit.getDefaultToolkit().getImage("./Images/menuFin.png");
    this.setContentPane(new ImageDeFond(fond));
    this.setLayout(null);
    this.bouton1=new JButton("REJOUER");
    this.bouton2=new JButton("QUITTER");
    BoutonMenuFin bmf=new BoutonMenuFin(this);
    bouton1.addActionListener(bmf);
    bouton2.addActionListener(bmf);

    try{
      GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Elderberry.ttf")));
    }catch (IOException|FontFormatException e) {
      System.out.println("erreur de police: "+e.getMessage());
    }
    Font f=new Font("Elderberry", Font.BOLD, 26);

    JLabel lab=new JLabel(score+"");
    lab.setFont(f);
    lab.setHorizontalAlignment(SwingConstants.CENTER);
    lab.setBounds(222,150,310,95);

    bouton1.setContentAreaFilled(false);
    bouton1.setBorderPainted(false);
    bouton1.setFocusPainted(false);
    bouton1.setFont(f);
    bouton1.setBounds(63,321,260,95);

    bouton2.setContentAreaFilled(false);
    bouton2.setBorderPainted(false);
    bouton2.setFocusPainted(false);
    bouton2.setFont(f);
    bouton2.setBounds(441,321,260,95);

    this.add(lab);
    this.add(bouton1);
    this.add(bouton2);

    this.setSize(770, 570);
    this.setLocation(100, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);
  }
}
