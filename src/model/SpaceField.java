package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

public class SpaceField extends Observable {
	private int dimX;
	private int dimY;
	private ArrayList<AbstractInsect> listOfInsect;

	public SpaceField() {
		this.listOfInsect = new ArrayList<AbstractInsect>();
		dimX = dimY = 500;
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
			this.listOfInsect.add(a);
		}
	}
	
	public ArrayList<AbstractInsect> getList() {
		return this.listOfInsect;
	}

	
	public Object  getObject(Point p) {
		
		for (AbstractInsect b : listOfInsect) {
			if (b.getPosition().equals(p)) {
				return b;
			}
				
		}
		return null;
	}
	
	
}
