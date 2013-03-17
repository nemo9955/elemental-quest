package game;

import nivel.BlockMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entitate extends Physics {
	
	public Entitate(BlockMap blockmap) {
		super(blockmap);
	}

	protected Image img ;
	protected float x ;
	protected float y;
	
	public void upadte(GameContainer gc , int delta){
		
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}
	
}
