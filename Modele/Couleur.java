package Modele;

import java.awt.*;

public class Couleur {

   public Color CouleurtoColor(enumCouleur _couleur) {
   //System.out.println(_couleur);
      switch (_couleur) {
       //ordre des couleur : ROUGE, CYAN, GRIS, JAUNE, MAGENTA, VERT, BLEU
         case ROUGE:
            return Color.RED;
         case CYAN:
            return Color.CYAN;
         case GRIS:
            return Color.GRAY;
         case JAUNE:
            return Color.YELLOW;
         case MAGENTA:
            return Color.MAGENTA;
         case VERT:
            return Color.GREEN;
         case BLEU:
            return Color.BLUE;
         case NOIR:
            return Color.BLACK;
         default:
            return Color.BLACK;
      }
  }  

}
