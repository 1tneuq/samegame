/**
* Cette classe gere l'action quand on appuie sur un des boutons
*
* @version 1.0
* @authors Quentin LACOMBE & Adam MEDDAHI
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class BoutonMenu implements ActionListener{

  private Menu menu;
  public BoutonMenu(Menu menu){
	  this.menu=menu;
  }

  /**
  *Lance le jeu Aleatoire ou venant d'un fichier en fct du bouton appuye; initialise "fermer" a 1 pour que le menu se ferme quand fenetre de jeu s'ouvre
  *
  * @param evenement qui sert a distinguer les deux boutons
  */
  @Override
  public void actionPerformed(ActionEvent evenement){
    JButton btn=(JButton)evenement.getSource();
	  if(btn.getText().equalsIgnoreCase("Aleatoire")){
      menu.dispose();
		  Samegame jeu=new Samegame(null);
	  }else if(btn.getText().equalsIgnoreCase("Fichier")){
		  JFileChooser fc=new JFileChooser();
		  int res=fc.showOpenDialog(btn.getParent());
		  if(res==JFileChooser.APPROVE_OPTION){
			   File f=fc.getSelectedFile();
			   menu.dispose();
			   Samegame jeu=new Samegame(f);
		  }
    }
  }
}
