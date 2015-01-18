package model;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SpaceField extends Observable implements Observer {
	private int dimX;
	private int dimY;
	private ArrayList<AbstractInsect> listOfInsect;
	private Image image;

	public SpaceField() {
		this.listOfInsect = new ArrayList<AbstractInsect>();
		dimX = dimY = 500;
		image = new BufferedImage(dimX, dimY, BufferedImage.TYPE_INT_ARGB);
	}
	
	public int getDimX() {
		return dimX;
	}
	public int getDimY() {
		return dimY;
	}
	public void setDimX(int dimX) {
		this.dimX = dimX;
	}
	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
	public void addInsect(AbstractInsect a) {
		if (! this.listOfInsect.contains(a)) {
			a.addObserver(this);
			//this.listOfInsect.add(a);
		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Object  getObject(Point p) {
		
		for (AbstractInsect b : listOfInsect) {
			if (b.getPosition().equals(p)) {
				return b;
			}
				
		}
		return null;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		AbstractInsect a = null;
		if ( arg0 instanceof AbstractInsect ) a = (AbstractInsect) arg0;
		if ( a != null) {
			this.setChanged();
			this.notifyObservers(a);
		}
		
		
	}
	
}
