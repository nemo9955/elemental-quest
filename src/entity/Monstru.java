package entity;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Monstru extends Entitate {
	

	private int viataRate = 0;
	
	public Monstru(float x, float y ) throws SlickException{
		super(player);
		this.specie=1;
		this.img = new Image("res/entitati/monstru.png");
		this.x=x;
		this.y=y;
		poly = new Rectangle (x , y , img.getWidth() , img.getHeight());
		this.team = 10 ;
		this.viataMax = 60 ;
		this.viata = viataMax ;
	}
	
	public void upadte(GameContainer gc , int delta){
		Input input = gc.getInput();
		
		// initializarea sariturii
				if(!jumping && canjump && input.isKeyDown(Input.KEY_UP)){
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
				
				if(viataRate > 0)	viataRate += delta ;
				if(viataRate > 500)	viataRate = 0 ;
				
				if(poly.intersects( player.getPoy() ) && player.color==Color.blue && viataRate == 0){
					player.takeLife(9);
					viataRate=1;
				}
				
				if(poly.intersects( player.getPoy() ) && player.color==Color.green )
					takeLife(1);// TODO debuding
	}
	
}
