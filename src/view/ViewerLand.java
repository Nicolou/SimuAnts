package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import model.AbstractInsect;
import model.Ant;

public class ViewerLand extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Image image;
	private model.SpaceField spField;
	private Timer timer;
	
	public ViewerLand(model.SpaceField sp) {
		this.spField = sp;
		image = new BufferedImage(spField.getDimX(), spField.getDimY(), BufferedImage.TYPE_INT_ARGB);
			
		this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
		this.spField.addObserver(this);
		this.timer = new Timer();
		this.timer.schedule(new SchedduledPaintImage(), 250, 100); //display image 10/seconds and begin after 250 millisecond
		
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
		AbstractInsect a = null;
		if ( arg instanceof AbstractInsect) a = (AbstractInsect) arg;
		if ( a != null) {
//			Graphics2D g2 = ((BufferedImage) image).createGraphics();
//			//g2.clearRect(0, 0, image.getWidth(null), image.getHeight(null));
//			g2.drawImage(a.getImage(), a.getPosx()-a.getImage().getWidth(null)/2, a.getPosy()-a.getImage().getHeight(null)/2, null);
//			
		}
	}
	
	private class SchedduledPaintImage extends TimerTask {

		@Override
		public void run() {
			//get all Insect from the spaceFields, draw them and display my image;
			Graphics2D g2 = ((BufferedImage) image).createGraphics();
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
			for (model.AbstractInsect a : spField.getList()) {
				g2.drawImage(a.getImage(), a.getPosx()-a.getImage().getWidth(null)/2, a.getPosy()-a.getImage().getHeight(null)/2, null);
			}
			g2.dispose();			
			repaint();
			
		}
		
	}

}
