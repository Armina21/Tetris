package VueControleur;

import Modele.Couleur;
import Modele.Grille;



import javax.swing.*;
import java.awt.*;


import java.util.Observable;
import java.util.Observer;

class VueGrilleV2 extends JPanel implements Observer {

    public int TAILLE = 30;
    private final static int TAILLE2 = 30;
    private Grille modele;
    private Couleur col;

    public VueGrilleV2(Grille _modele) {
        modele = _modele;
        modele.addObserver(this); // Ajoutez cette ligne pour vous assurer que la vue est notifiée des changements dans le modèle
        col = new Couleur();
    }

    public void afficherScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        //affichage propre selon le nombre de chiffre du score
        if (modele.getPoints().getScore() < 10) {
            g.drawString(Integer.toString(modele.getPoints().getScore()), 100, 200);
        }
        if (modele.getPoints().getScore() >= 10 && modele.getPoints().getScore() < 100) {
            g.drawString(Integer.toString(modele.getPoints().getScore()), 90, 200);
        }
        if (modele.getPoints().getScore() >= 100 && modele.getPoints().getScore() < 1000) {
            g.drawString(Integer.toString(modele.getPoints().getScore()), 80, 200);
        }
        if (modele.getPoints().getScore() >= 1000 && modele.getPoints().getScore() < 10000) {
            g.drawString(Integer.toString(modele.getPoints().getScore()), 70, 200);
        }
        if (modele.getPoints().getScore() >= 10000 && modele.getPoints().getScore() < 100000) {
            g.drawString(Integer.toString(modele.getPoints().getScore()), 60, 200);
        }
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Chargement de l'image depuis le fichier
        ImageIcon background_menu_Image = new ImageIcon("Data/Background_menu.jpg");
        ImageIcon backgroundImage = new ImageIcon("Data/Background.png"); // Assurez-vous de mettre le chemin correct
        ImageIcon JouerImage = new ImageIcon("Data/Jouer.png");
        ImageIcon ScoreImage = new ImageIcon("Data/Score.png");
        ImageIcon LevelImage = new ImageIcon("Data/Level.png");
        ImageIcon PauseImage = new ImageIcon("Data/Pause.png");
        ImageIcon BackgroundPauseImage = new ImageIcon("Data/Pause_Background.png");
        ImageIcon ReprendreImage = new ImageIcon("Data/Reprendre.png");
        ImageIcon RecommencerImage = new ImageIcon("Data/Recommencer.png");
        ImageIcon QuitterImage = new ImageIcon("Data/Quitter.png");
        ImageIcon MystereImage = new ImageIcon("Data/Mystere.png");
        
            // Calcul de la position pour centrer la grille
            int xOffset = (getWidth() - modele.TAILLE_X * TAILLE) / 2;
            int yOffset = (getHeight() - (modele.TAILLE_Y-1) * TAILLE) / 2;



        if(modele.getJeuCommence()){
            // Dessin de l'image de fond
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

            g.drawImage(ScoreImage.getImage(), 5, 50, 200, 200, this);
            g.drawImage(LevelImage.getImage(), 5, 300, 200, 200, this);
            


            // Affichage du niveau
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString(Integer.toString(modele.getNiveau().getNiveau()), 100, 400);
                
            // Affichage du score
            afficherScore(g);
            
            
         

            g.drawImage(PauseImage.getImage(),xOffset+430 , yOffset+520, 80, 80, this);
        
            for (int i = 2; i < modele.TAILLE_X-1; i++) {
                for (int j = 0; j < modele.TAILLE_Y-1; j++) {
                    g.setColor(col.CouleurtoColor(modele.getGrilleCouleurs()[i][j]));
                    g.fillRect(xOffset + i * TAILLE, yOffset + j * TAILLE, TAILLE, TAILLE);
                }
            }
        
            for (int k = 0; k < 4; k++) {
                for (int l = 0; l < 4; l++) {
                    if (modele.getPieceCourante().getCase(k, l) == 1) {
                        g.setColor(col.CouleurtoColor(modele.getPieceCourante().getCouleur()));
                        g.fillRect(xOffset + (modele.getPieceCourante().getx() + k) * TAILLE, yOffset + (modele.getPieceCourante().gety() + l) * TAILLE, TAILLE, TAILLE);
                    }
                    if (modele.getPieceCourante().getCase(k, l) == 3) {
                        g.setColor(Color.WHITE);
                        g.fillRect(xOffset + (modele.getPieceCourante().getx() + k) * TAILLE, yOffset + (modele.getPieceCourante().gety() + l) * TAILLE, TAILLE, TAILLE);
                    }
                }
            }

            for (int i = 2; i < modele.TAILLE_X-1; i++) {
                for (int j = 0; j < modele.TAILLE_Y-1; j++) {
                    g.setColor(Color.GRAY);
                    g.drawRoundRect(xOffset + i * TAILLE, yOffset + j * TAILLE, TAILLE, TAILLE, 1, 1);
                }
            }


            //affichage de la grille pour les pieces suivantes
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    g.setColor(col.CouleurtoColor(modele.getGrilleCouleurs()[i][j]));
                    g.fillRect((xOffset +400) + i * TAILLE2, (yOffset + 90) + j * TAILLE2, TAILLE2, 8*TAILLE2);
                    g.setColor(Color.GRAY);
                    g.drawRoundRect(xOffset +399, yOffset +89 , TAILLE2+120, TAILLE2+320, 5, 5);
                }
            }

            //affichage de la grille de piece suivante
            if(modele.getPieceSuivante().getEst_mystere()){
                g.drawImage(MystereImage.getImage(),xOffset + 430, yOffset + 105, 70, 90, this);
            }
            else{  
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (modele.getPieceSuivante().getCase(k, l) == 1) {
                            g.setColor(col.CouleurtoColor(modele.getPieceSuivante().getCouleur()));
                            g.fillRect((xOffset +400) +k * TAILLE2, (yOffset +90 )+(l+1) * TAILLE2, TAILLE2, TAILLE2);
                            g.setColor(Color.GRAY);
                            g.drawRoundRect((xOffset +400) + k * TAILLE2, (yOffset +90 ) + (l+1) * TAILLE2, TAILLE2, TAILLE2, 1, 1);
                        }
                    }
                }
            }

            //affichage de la grille de piece suivante2
            
            if(modele.getPieceSuivante2().getEst_mystere()){
                g.drawImage(MystereImage.getImage(),xOffset + 430, yOffset + 205, 70, 90, this);
            }
            else{  
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (modele.getPieceSuivante2().getCase(k, l) == 1) {
                            g.setColor(col.CouleurtoColor(modele.getPieceSuivante2().getCouleur()));
                            g.fillRect((xOffset +400) +k * TAILLE2, (yOffset +190 )+(l+1) * TAILLE2, TAILLE2, TAILLE2);
                            g.setColor(Color.GRAY);
                            g.drawRoundRect((xOffset +400) + k * TAILLE2, (yOffset +190 ) + (l+1) * TAILLE2, TAILLE2, TAILLE2, 1, 1);
                        }
                    }
                }
            }

            //affichage de la grille de piece suivante3
            
            if(modele.getPieceSuivante3().getEst_mystere()){
                g.drawImage(MystereImage.getImage(),xOffset + 430, yOffset + 305, 70, 90, this);
            }
            else{  
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (modele.getPieceSuivante3().getCase(k, l) == 1) {
                            g.setColor(col.CouleurtoColor(modele.getPieceSuivante3().getCouleur()));
                            g.fillRect((xOffset +400) +k * TAILLE2, (yOffset +290 )+(l+1) * TAILLE2, TAILLE2, TAILLE2);
                            g.setColor(Color.GRAY);
                            g.drawRoundRect((xOffset +400) + k * TAILLE2, (yOffset +290 ) + (l+1) * TAILLE2, TAILLE2, TAILLE2, 1, 1);
                        }
                    }
                }
            }

            if(modele.getPause()){
                System.out.println("affichagepause");
                g.drawImage(BackgroundPauseImage.getImage(),xOffset + 29 , yOffset +100, 361, 450, this);
                g.drawImage(ReprendreImage.getImage(),xOffset+105 , yOffset+140, 225, 90, this);
                g.drawImage(RecommencerImage.getImage(),xOffset+105 , yOffset+250, 225, 90, this);
                g.drawImage(QuitterImage.getImage(),xOffset+105 , yOffset+360, 225, 90, this);
            }
        }
        
        
        else{
            g.drawImage(background_menu_Image.getImage(), 0, 0, getWidth(), getHeight(), this);
            g.drawImage(JouerImage.getImage(),xOffset+105 , yOffset+140, 225, 90, this);
            g.drawImage(QuitterImage.getImage(),xOffset+105 , yOffset+360, 225, 90, this);
        }
    }
    

    @Override
    public void update(Observable o, Object arg) {
        
        repaint();
        
    }
}
