package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class ViewerLand extends JPanel implements ComponentListener {
	private static final long serialVersionUID = 1L;
	private Image image;
	private model.SpaceField spField;
	private Timer timer;
	
	public ViewerLand(model.SpaceField sp) {
		this.spField = sp;
		image = new BufferedImage(spField.getDimX(), spField.getDimY(), BufferedImage.TYPE_INT_ARGB);
			
		this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
		this.timer = new Timer();
		this.timer.schedule(new SchedduledPaintImage(), 250, 100); //display image 10/seconds and begin after 250 millisecond
		
		this.addComponentListener(this);
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



	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {

		this.spField.setDimX(this.getWidth());
		this.spField.setDimY(this.getHeight());
		image = new BufferedImage(spField.getDimX(), spField.getDimY(), BufferedImage.TYPE_INT_ARGB);			
		//this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
