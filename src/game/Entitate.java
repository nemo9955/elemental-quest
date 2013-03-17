package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Entitate extends Physics {
	
	protected Image img ;
	
	public void upadte(GameContainer gc , int delta){
		Input input = gc.getInput();
		
		// initializarea sariturii
				if(!jumping && canjump && input.isKeyPressed(Input.KEY_UP)){
					accel = accelIni;
					jumping=true;
				}
				
				Gravitatie();
				
				// miscarea
				if(!moving) moveSpeed=0;
				moving=false;

				// dreapta
				if(input.isKeyDown(Input.KEY_RIGHT)){
					x+=moveSpeed;
					poly.setX(x);
					if(colid()){
						x-=moveSpeed;
						poly.setX(x);
					}
					moveSpeed+=moveSpeedMod;
					moving=true;
				}
				// stanga
				if(input.isKeyDown(Input.KEY_LEFT)){
					x-=moveSpeed;
					poly.setX(x);
					if(colid()){
						x+=moveSpeed;
						poly.setX(x);
					}
					moveSpeed+=moveSpeedMod;
					moving=true;
				}
				if(moveSpeed > moveSpeedMax) moveSpeed = moveSpeedMax ;
				
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
	}
	
}
