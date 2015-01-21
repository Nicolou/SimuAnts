package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ant extends AbstractInsect {
	
	
	private float posx;
	private float posy;
	private int foods;
	private int age;
	private int azimuthDegrees;
	private SpaceField spaceField;
	private String name;
	private Image img;
	private Image imgOri;
	
	
	public Ant(SpaceField sp) {
		foods=5;
		age = 1;
		azimuthDegrees = 0;
		if (sp != null )  this.spaceField = sp;
		else this.spaceField = new SpaceField();
		
		this.spaceField.addInsect(this);
		this.azimuthDegrees = 30;
		
		try {
			this.img = ImageIO.read(getClass().getClassLoader().getResource("resources/images/fourmis1.png"));
			this.imgOri = ImageIO.read(getClass().getClassLoader().getResource("resources/images/fourmis1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			imgOri = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = ((BufferedImage) img).createGraphics();
			g2.setColor(Color.BLUE);
			g2.fillRect(4, 4, 1, 1);
			g2.dispose();
			this.img = new BufferedImage(this.imgOri.getWidth(null), this.imgOri.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		}
		
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
	@Override
	public Image getImage() {
		return img;
	}
	
	private void rotateImg() {
		
		Graphics2D g2 = ((BufferedImage) img).createGraphics();
		//clear the surface
		g2.setBackground(new Color (1,1,1,1)); //transparency backColor
		g2.clearRect(0, 0, img.getWidth(null), img.getHeight(null));
		//do the rotation
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(this.azimuthDegrees)/2,this.imgOri.getWidth(null)/2, this.imgOri.getHeight(null)/2 );
	    g2.setTransform(tx);
	    g2.drawImage(imgOri, tx, null);		
		g2.dispose();		
	}

	private void turnLeft(int degrees){
		this.azimuthDegrees = (this.azimuthDegrees - degrees) % 360;
		this.rotateImg();
	}
	
	private void turnRight(int degrees){
		this.azimuthDegrees = (this.azimuthDegrees + degrees) % 360;
		this.rotateImg();
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
