package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Solaris extends Entitate {
	
	public Solaris(float x, float y , int specie) throws SlickException{
		super(player);
		this.specie=specie;
		this.img = new Image("res/entitati/solaris.png");
		this.x=x;
		this.y=y;
		poly = new Rectangle (x , y , img.getWidth() , img.getHeight());
		this.team = 11 ;
	}
	
}
