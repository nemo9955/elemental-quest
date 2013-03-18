package game;

import entity.Entitate;

import nivel.Obiecte;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Player extends Entitate{	
	
	public Color color=Color.blue ;
	private Input input ;
	
	private int viataRate = 0;
	private int focRate = 1;

	// constructorul principal al jucatorului din gamestate
	public Player (float x , float y , GameContainer gc ) throws SlickException{
		super(player);
		this.img=new Image("res/entitati/player.png");
		this.x=x;
		this.y=y;
		poly = new Rectangle (x , y, img.getWidth() , img.getHeight());
		input = gc.getInput();
		this.team = 5 ;
	}
	
    public void update( int delta) throws SlickException{
		
    	// initializarea sariturii
		if(!jumping && canjump && input.isKeyDown(Input.KEY_W)){
			accel = accelIni;
			jumping=true;
		}
		
		Gravitatie();
		
		// miscarea
		if(!moving) moveSpeed=0;
		moving=false;

		// dreapta
		if(input.isKeyDown(Input.KEY_D)){
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
		if(input.isKeyDown(Input.KEY_A)){
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
		
		if(input.isKeyPressed(Input.KEY_SPACE))
			if(color==Color.blue)
				color=Color.green;
			else
				color=Color.blue;
				
		//TODO debug
		if(input.isKeyPressed(Input.KEY_F1))
			System.out.println(x + "  " + y );
		if(input.isKeyPressed(Input.KEY_F2))
			System.out.println(poly.getWidth());
		if(input.isKeyPressed(Input.KEY_F3))
			System.out.println("viata : " + viata);
		if(input.isKeyPressed(Input.KEY_F4))
			System.out.println(blockmap.getProp((int)x/32 , (int)y/32 ));
		if(input.isKeyPressed(Input.KEY_F6))
			addLife(10);
		if(input.isKeyPressed(Input.KEY_F5)){
			x=2400;
			poly.setX(x);
			y=500;
			poly.setY(y);
		}
		
		if(viataRate > 0)		viataRate += delta ;
		if(viataRate > 1000)	viataRate = 0 ;
		if(focRate > 0)		focRate += delta ;
		if(focRate > 300)	focRate = 0 ;
		
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)  && focRate==0){
			Obiecte.focShot( x, y, team );
			focRate=1;
		}
    }

    public void render(GameContainer gc, Graphics g){
		g.setColor(color);
		g.setLineWidth(2);
		g.draw(poly);
		img.draw(x, y);
    }
    
    public Rectangle getPoy(){
    	return poly;
    }
    
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float LifeLS(){
		return viata/100f ;
	}
	public float getViata(){
		return viata ;
	}
	
	public void addLife(int x){
		viata+=x;
		if(viata>100)viata=100;
	}
	public void takeLife(float x){
		if(viataRate == 0){
			viata-=x;
			viataRate=1;
			if(viata<0){
				viata=0;
			}
		}
	}
}
