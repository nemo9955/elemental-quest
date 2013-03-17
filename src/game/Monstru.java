package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Monstru extends Entitate {
	
	public Monstru(float x, float y) throws SlickException{
		this.img = new Image("res/entitati/monstru.jpg");
		this.x=x;
		this.y=y;
		poly = new Rectangle (y,x,img.getWidth(),img.getHeight());
	}

}
