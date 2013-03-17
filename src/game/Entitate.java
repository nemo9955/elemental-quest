package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Entitate extends Physics {
	
	private Image img ;
	private float x ;
	private float y;
	
	public Entitate(Image img, float x, float y) {
		super();
		this.img = img;
		this.x=x;
		this.y=y;
		poly = new Rectangle (y,x,img.getWidth(),img.getHeight());
	}
	
	
	public void upadte(GameContainer gc , int delta){
		
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}
	
}
