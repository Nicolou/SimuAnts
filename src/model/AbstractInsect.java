package model;

import java.awt.Image;
import java.awt.Point;
import java.util.Observable;

public abstract class AbstractInsect extends Observable{
	
	public abstract String getName();
	public abstract void setName(String name);	
	public abstract int getPosx();	
	public abstract void setPosx(int posx);
	public abstract int getPosy();	
	public abstract void setPosy(int posy);
	public abstract Point getPosition() ;
	public abstract int getFoods();	
	public abstract int getAge();
	public abstract void setAge(int age);
	public abstract Image getImage();
	public abstract void simu();
		
	
}
