package entity;

import java.util.Random;

import nivel.Obiecte;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Solaris extends Entitate {
	
	private int ang=0 ;
	private int movex ;
	private int movey ;
	private int angspd ;
	private int firerate = 0 ;
	
	public Solaris(float x, float y) throws SlickException{
		super(player);
		this.specie=2;
		this.img = new Image("res/entitati/solaris.png");
		this.x=x;
		this.y=y;
		poly = new Rectangle (x , y , img.getWidth() , img.getHeight());
		this.team = 11 ;
		this.viataMax = 170 ;
		this.viata = viataMax ;
		Random zar =  new Random();
		movex  = 7 + zar.nextInt( 5 );
		movey  = 7 + zar.nextInt( 4 );
		angspd = 5 + zar.nextInt( 10 );
	}
	
	public void upadte(GameContainer gc , int delta) throws SlickException{
		
		// miscarea aproximativ circulara
		x+= movex * Math.sin(Math.toRadians(ang));
		y-= movey * Math.cos(Math.toRadians(ang));
		ang+=angspd;
		poly.setLocation(x,y);
		if(ang >= 360)ang=0;
		
		float dir = (float) Math.atan((poly.getCenterY() - player.getPoy().getCenterY()) / (poly.getCenterX() - player.getPoy().getCenterX()));
		
		// verific distanta dintre Solarys si jucator
		if( firerate == 0 ){
			if( Math.sqrt( Math.pow( player.getX()-x , 2)  + Math.pow( player.getY()-y , 2) ) < 550 ){
				firerate=1;
				boolean sus = (player.getPoy().getCenterY() < poly.getCenterY()) ? true : false ;
				Obiecte.shot.add(new Shot (poly.getCenterX(), poly.getCenterY(), team, dir , sus));
			}
		}
		if(firerate != 0)
			firerate += delta;
		if(firerate > 80)
			firerate = 0;
	}
	
}
