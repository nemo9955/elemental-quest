package entity;

import game.Physics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entitate extends Physics {
	
	protected Image img ;
	protected int team ;

	public void upadte(GameContainer gc , int delta){
		Gravitatie();	
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}
	
}
