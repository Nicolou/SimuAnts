package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Ant;

public class Land extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Image image;
	private model.SpaceField spField;
	
	public Land(model.SpaceField sp) {
		this.spField = sp;
		image = new BufferedImage(spField.getDimX(), spField.getDimY(), BufferedImage.TYPE_INT_ARGB);
			
		this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));

		this.spField.addObserver(this);
	}
	 
	public void paintComponent(Graphics g) {
		  // Appel de la m�thode paintComponent de la classe m�re
		  super.paintComponent(g);
		  // Conversion en un contexte 2D
		  Graphics2D g2 = (Graphics2D) g;
		  // Contexte graphique
		  g2.drawImage(image, 0, 0, null);
		  
		  g2.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		Ant a = null;
		a = (Ant) arg;
		if ( a != null) {
			Graphics2D g2 = ((BufferedImage) image).createGraphics();
			g2.setColor(Color.BLUE);
			g2.fillRect(a.getPosx(), a.getPosy(), 1, 1);
			g2.dispose();
			
			this.repaint();
		}
	}

}
