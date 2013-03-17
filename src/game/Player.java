package game;

import nivel.BlockMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Player extends Physics{	
	
/*	private int  marime ;
	private boolean jumping=false ;
	private boolean canjump=true ;
	private float moveSpeed;
	private boolean moving = false ;
	private float accelIni = 20f;
	private float moveSpeedMax = 8f;
	private float moveSpeedMod = 0.5f;*/
	
	private Color color=Color.blue ;
	private Input input ;
	private int viata = 100 ;

	public void setPlayer(float accelIni, float accelMod, float gravMax, float gravMod,float moveSpeedMax, float moveSpeedMod) {
		this.accelIni = accelIni;
		this.accelMod = accelMod;
		this.gravMax = gravMax;
		this.gravMod = gravMod;
		this.moveSpeedMax = moveSpeedMax;
		this.moveSpeedMod = moveSpeedMod;
	}

	// constructorul principal al jucatorului din gamestate
	public Player (float x , float y , int marime , GameContainer gc , TiledMap map) throws SlickException{
		this.x=x;
		this.y=y;
		blockmap = new BlockMap(map);
		this.marime=marime;
		poly = new Rectangle (x , y, marime , marime);
		input = gc.getInput();
	}
	
    public void update(GameContainer gc, int delta){
		
    	// initializarea sariturii
		if(!jumping && canjump && input.isKeyPressed(Input.KEY_W)){
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
		
		// TODO debug
/*		if(input.isKeyDown(Input.KEY_S)){
			y+=moveSpeed;
			poly.setY(y);
			if(colid()){
				y-=moveSpeed;
				poly.setY(y);
			}
		}
		if(input.isKeyDown(Input.KEY_W)){
			y-=moveSpeed;
			poly.setY(y);
			if(colid()){
				y+=moveSpeed;
				poly.setY(y);
			}
		}*/
		
		if(input.isKeyPressed(Input.KEY_SPACE))
			if(color==Color.blue)
				color=Color.green;
			else
				color=Color.blue;
				
		//TODO debug
		if(input.isKeyPressed(Input.KEY_F1))
			System.out.println(x + "  " + y );
		if(input.isKeyPressed(Input.KEY_F2))
			System.out.println(marime);
		if(input.isKeyPressed(Input.KEY_F3))
			System.out.println("viata : " + viata);
		if(input.isKeyPressed(Input.KEY_F4))
			System.out.println(blockmap.getProp((int)x/32 , (int)y/32 ));
				
		if(input.isKeyDown(Input.KEY_F6))
			addLife(10);
		if(input.isKeyDown(Input.KEY_F5)){
			x=2400;
			poly.setX(x);
			y=500;
			poly.setY(y);
		}
    }
    
    


    public void render(GameContainer gc, Graphics g){
		g.setColor(color);
		g.setLineWidth(3);
		g.draw(poly);
/*		g.setColor(Color.cyan);  TODO debug
		for(int j=0 ; j<map.getHeight() ; j++)
			for(int i=0 ; i<map.getWidth() ; i++){
				if(blockmap.isBlock(i, j)){
					rec = blockmap.getBlock(i, j);
					g.draw(rec);
				}
			}*/
		
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

	public int getMarime() {
		return marime;
	}

	public void setMarime(int marime) {
		this.marime = marime;
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
		viata-=x;
		if(viata<0){
			viata=0;
			//TODO mori
			System.out.println("esti moooort");
		}
		// TODO adauga efect cand suferi damage
	}
}
