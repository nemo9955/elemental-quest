package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Shot extends Entitate {

	public Shot(float x , float y  ,int team  ) throws SlickException {
		super(player);
		if(team == 5){
			this.team = 6 ;
			this.img = new Image ("res/entitati/shot_pl.jpg");
		}
		else if(team >= 10){
			this.team=7;
			this.img = new Image ("res/entitati/shot_en.jpg");
		}
		this.specie=3;
		this.x=x;
		this.y=y;
		poly = new Rectangle (x , y , img.getWidth() , img.getHeight());
	}
	
	public void upadte(GameContainer gc , int delta){
		x+=8;
		poly.setX(x);
		if(colid())
			elimina(specie);
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}

}
