package view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewerLand land;
	
	public Window() throws HeadlessException {
		
		this.setTitle("Ants Simulation");
		this.land = null;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void setLand( model.SpaceField sp) {
		this.land = new ViewerLand(sp);
		this.getContentPane().add(land, BorderLayout.CENTER);
		this.pack();
	}


}
