package entity;

import game.Physics;
import game.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entitate extends Physics {
	
	protected Image img ;
	protected int team ;
	
	protected static Player player ;
	
	public  Entitate(Player player){
		Entitate.player=player;
	}
	
	public void upadte(GameContainer gc , int delta){
		Gravitatie();	
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}
	
}