package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Shot extends Entitate {

	public Shot(int x , int y  ,int team) throws SlickException {
		super(player);
		if(team == 5){
			this.team = 6 ;
			this.img = new Image ("res/entitati/shot_pl.jpg");
		}
		else if(team >= 10){
			this.team=7;
			this.img = new Image ("res/entitati/shot_en.jpg");
		}
	}
	
	public void upadte(GameContainer gc , int delta){
		x+=10;
		poly.setX(x);
		if(colid())
			elimina(delta);
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}

}
