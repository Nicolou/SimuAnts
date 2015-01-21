package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OldAnt extends AbstractInsect {

	static enum ORI {
		NORTH,
		EAST,
		WEST,
		SOUTH
	}
	private int posx;
	private int foods;
	private int age;
	private int posy;
	private ORI orientation;
	private SpaceField spaceField;
	private String name;
	private Image img;
	private Image imgOri;

	public OldAnt(SpaceField sp) {
		foods=5;
		age = 1;
		orientation = ORI.EAST;
		if (sp != null ) this.spaceField = sp;
		else this.spaceField = new SpaceField();
		this.spaceField.addInsect(this);
		
		try {
			this.imgOri = ImageIO.read(getClass().getClassLoader().getResource("resources/images/fourmis2.png"));
			this.img = ImageIO.read(getClass().getClassLoader().getResource("resources/images/fourmis2.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			imgOri = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = ((BufferedImage) img).createGraphics();
			g2.setColor(Color.RED);
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
	public ORI getOrientation() {
		return orientation;
	}
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	public Point getPosition() {
		return new Point(posx,posy);
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
	
	private void rotateImg(int angle) {
		
		Graphics2D g2 = ((BufferedImage) img).createGraphics();
		//clear the surface
		g2.setBackground(new Color (1,1,1,1)); //transparency backColor
		g2.clearRect(0, 0, img.getWidth(null), img.getHeight(null));
		//do the rotation
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(angle)/2,this.imgOri.getWidth(null)/2, this.imgOri.getHeight(null)/2 );
	    g2.setTransform(tx);
	    g2.drawImage(imgOri, tx, null);
		
		g2.dispose();		
	}
	
	private void turnLeft(){
		
		int angle=0;
		
		switch (orientation) {
		case EAST:
			this.orientation = ORI.NORTH;
			angle = 0;
			break;
		case NORTH:
			this.orientation = ORI.WEST;
			angle = -90;
			break;
		case SOUTH:
			this.orientation = ORI.EAST;
			angle = 90;
			break;
		case WEST:
			this.orientation = ORI.SOUTH;
			angle = 180;
			break;
		default:
			break;
		}
		this.rotateImg(angle);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	private void turnRight(){
		int angle=0;
		switch (orientation) {
		case EAST:
			this.orientation = ORI.SOUTH;
			angle = 180;
			break;
		case NORTH:
			this.orientation = ORI.EAST;
			angle = 90;
			break;
		case SOUTH:
			this.orientation = ORI.WEST;
			angle = -90;
			break;
		case WEST:
			this.orientation = ORI.NORTH;
			angle = 0;
			break;
		default:
			break;
		}
		//rotate img
		this.rotateImg(angle);
		
		this.setChanged();
		this.notifyObservers();
	}
	private void goHead(){
		switch (orientation) {
		case EAST:
			if (this.posx < this.spaceField.getDimX()) {
				this.posx += 1;
				this.setChanged();
				this.notifyObservers();
			}
			break;
		case NORTH:
			if (this.posy > 0) {
				this.posy -= 1;
				this.setChanged();
				this.notifyObservers();
			}
			break;
		case SOUTH:
			if (this.posy < this.spaceField.getDimY()) {
				this.posy += 1;
				this.setChanged();
				this.notifyObservers();
			}
			break;
		case WEST:
			if (this.posx > 0 ) {
				this.posx -= 1;
				this.setChanged();
				this.notifyObservers();
			}
			break;
		default:
			break;
		}
	}
	public void simu() {
		//random behavior
		Thread t = new Thread() {
			public void run() {
				int tpsMini = 10;
				int tpsMaxi = 500;
				int nbt=0, nbdir=0, tps=0, dir = 0;
				int nbDirMini = 3;
				int nbDirMaxi = 10;
				while (true) {
					if (nbt < 0) {
						tps = (int) (tpsMini + (tpsMaxi-tpsMini) *Math.random());
						nbt = (int) (10*Math.random());
					}
					if (nbdir < 0) {
						nbdir = (int) (nbDirMini + (nbDirMaxi-nbDirMini) *Math.random());
						dir = (int) ( Math.floor( Math.random() * 4)) ;
					}
					switch (dir) {
					case 1:
						turnLeft();
						dir = 0;
						break;
					case 2:
						turnRight();
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
