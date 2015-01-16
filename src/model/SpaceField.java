package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SpaceField extends Observable implements Observer {
	private int dimX;
	private int dimY;
	private ArrayList<Ant> listOfAnt;

	public SpaceField() {
		this.listOfAnt = new ArrayList<Ant>();
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
	public void addAnt(Ant a) {
		if (! this.listOfAnt.contains(a)) {
			a.addObserver(this);
			this.listOfAnt.add(a);
		}
	}
	
	public Object  getObject(Point p) {
		
		for (Ant b : listOfAnt) {
			if (b.getPosition().equals(p)) {
				return b;
			}
				
		}
		return null;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Ant a = null;
		a = (Ant) arg0;
		if ( a != null) {
			this.setChanged();
			this.notifyObservers(a);
		}
		
		
	}
	
}
