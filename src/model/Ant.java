package model;

import java.awt.Point;
import java.util.Observable;

public class Ant extends Observable {
	
	
	private float posx;
	private float posy;
	private int foods;
	private int age;
	private int azimuthDegrees;
	private SpaceField spaceField;
	private String name;

	public Ant(SpaceField sp) {
		foods=5;
		age = 1;
		azimuthDegrees = 0;
		if (sp != null )  this.spaceField = sp;
		else this.spaceField = new SpaceField();
		
		this.spaceField.addAnt(this);
		this.azimuthDegrees = 30;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getPosx() {
		return Math.round(this.posx);
	}
	
	public void setPosx(int posx) {
		this.posx = (float) posx;
	}

	public int getPosy() {
		return Math.round(this.posy);
	}
	
	public void setPosy(int posy) {
		this.posy = (float) posy;
	}

	
	public Point getPosition() {
		return new Point(Math.round(posx),Math.round(posy));
	}

	public int getFoods() {
		return foods;
	}

	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (this.age != age) {
			this.age = age;
			this.setChanged();
			this.notifyObservers();
		}
	}


	private void turnLeft(int degrees){
		this.azimuthDegrees = (this.azimuthDegrees - degrees) % 360;
	}
	
	private void turnRight(int degrees){
		this.azimuthDegrees = (this.azimuthDegrees + degrees) % 360;
	}
	
	private void goHead(){
		double x, y;
		x = Math.sin(Math.toRadians(azimuthDegrees));
		y = Math.cos(Math.toRadians(azimuthDegrees)); 

		if ( this.posx + x < 0) {
			this.posx -= x;
			//change azimuth
			this.azimuthDegrees =  -1* this.azimuthDegrees;						
		}else if (this.posx + x > spaceField.getDimX()) {
			this.posx -= x;			
			this.azimuthDegrees =  -1* this.azimuthDegrees;					
		}else {
			this.posx += x;
		}
		
		if ( this.posy - y < 0) {
			this.posy += y;
			//change azimuth
			this.azimuthDegrees = (180 - this.azimuthDegrees) % 360;					
		}else if (this.posy - y > spaceField.getDimY()) {
			this.posy += y;			
			this.azimuthDegrees = (180 - this.azimuthDegrees) % 360;						
		}else{
			this.posy -= y;
		}
				
		//System.out.println(" x = " + this.getPosx() + ",  y=" + this.getPosy() + ", azimuth = " + this.azimuthDegrees);
		
		this.setChanged();
		this.notifyObservers();
			
	}
	

	public void simu() {
		//random behavior
		Thread t = new Thread() {

	        public void run() {
	        	
	        	int tpsMini = 10;
	        	int tpsMaxi = 50;
	        	int nbTpsMini = 10;
	        	int nbTpsMaxi = 30;
	        	int nbt=0, nbdir=0, tps=0, dir = 0;
	        	int nbDirMini = 3;
	        	int nbDirMaxi = 10;
	        	
	        	
	        	while (true) {
	        		
	        		if (nbt < 0) {
	        			tps = (int) (tpsMini + (tpsMaxi-tpsMini) *Math.random());
	        			nbt = (int) (nbTpsMini + (nbTpsMaxi-nbTpsMini) *Math.random());
	        		}
	        		
	        		if (nbdir < 0) {
	        			nbdir = (int) (nbDirMini + (nbDirMaxi-nbDirMini) *Math.random());
	        			dir = (int) ( Math.floor( Math.random() * 4)) ;
	        		}
	        		
					switch (dir) {
					case 1:
						turnLeft( (int) (Math.random()*120));
						dir = 0;
						break;
					case 2:
						turnRight( (int) (Math.random()*120));
						dir = 0;
						break;			
					}
					
					goHead();
					
					//wait ...
					try {
						Thread.sleep(tps);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					nbdir -= 1;
					nbt -= 1;
	        	}
	        }
		};
		t.start();
	
		
		
	}
	
	
	
}
