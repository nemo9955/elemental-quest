package entity;

import nivel.Obiecte;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Shot extends Entitate {
	
	private int lifeTime = 1500 ;
	private float ang;
	private boolean sus;
	
	public Shot(float x , float y  ,int team, float ang , boolean sus  ) throws SlickException {
		super(player);
		if(team == 5){
			this.team = 6 ;
			this.img = new Image ("res/entitati/shot_pl.png");
		}
		else if(team >= 10){
			this.team=7;
			this.img = new Image ("res/entitati/shot_en.png");
		}
		// ahh matematica asta
		if(ang > 0)
			this.ang=-90+(float)Math.toDegrees(ang);
		else
			this.ang=+90+(float)Math.toDegrees(ang);
		this.sus=sus;
//		System.out.println(this.ang);
		
		this.specie=3;
		this.x=x-img.getWidth()/2;
		this.y=y-img.getHeight()/2;
		poly = new Rectangle (this.x, this.y , img.getWidth() , img.getHeight());
	}
	
	public void upadte(GameContainer gc , int delta){
		
		if(sus){
			x+= 8 * Math.sin(Math.toRadians(ang));
  	 		y-= 8 * Math.cos(Math.toRadians(ang));
		}
  	 	else{
			x-= 8 * Math.sin(Math.toRadians(ang));
  	 		y+= 8 * Math.cos(Math.toRadians(ang));
  	 	}
		
		lifeTime-=delta;
		poly.setX(x);
		poly.setY(y);
		if(colid() || lifeTime<=0)
			elimina(specie);
		
		if(team == 6){
			if(hitInamic())
				elimina(specie);
		}
		else {
		//	if(hitPlayer());
			//	elimina(specie);
		}
	}
	
	private boolean hitInamic(){
		
		for(Monstru inamic : Obiecte.monstru)
			if(poly.intersects( inamic.poly )){
				inamic.takeLife(35);
				return true;
			}
		
		for(Solaris inamic : Obiecte.solaris)
			if(poly.intersects( inamic.poly )){
				inamic.takeLife(35);
				return true;
			}
		return false;
	}
	
	private boolean hitPlayer(){
		if(poly.intersects( player.poly )){
			player.takeLife(35);
				return true;
		}
		return false;
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}

}
