/**
* Cette classe gere l'action quand on appuie sur un des boutons du menu de fin
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class BoutonMenuFin implements ActionListener{

  private MenuFin menu;
  public BoutonMenuFin(MenuFin menu){
	  this.menu = menu;
  }


  @Override
  public void actionPerformed(ActionEvent event){
    JButton btn = (JButton)event.getSource();
	  if(btn.getText().equalsIgnoreCase("REJOUER")){
      menu.dispose();
		  Menu jeu=new Menu();
    }else if(btn.getText().equalsIgnoreCase("QUITTER")){
		   System.exit(0);
     }
   }
 }
