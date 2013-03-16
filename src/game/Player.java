package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Player {	
	
	private float x ;
	private float y;
	private int  marime ;
	private float accel;
	private boolean jump=false ;
	private boolean canjump=true ;
	private float grav;
	private float moveSpeed;
	private boolean moving = false ;
	private Input input ;
	private Color color=Color.blue ;
	private Rectangle poly ;
	private BlockMap blockmap ;
	
	private float accelIni = 20f;
	private float accelMod = 0.6f ;
	private float gravMax = 15f;
	private float gravMod = 0.5f;
	private float moveSpeedMax = 8f;
	private float moveSpeedMod = 0.5f;
	
	private int viata = 100 ;
	private int cadee=0 ;

	// clasa principala a jucatorului 
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
		if(!jump && canjump && input.isKeyPressed(Input.KEY_W)){
			accel = accelIni;
			jump=true;
		}
		
		// "comportamentul" sariturii
		if(jump){
			y-=accel;
			poly.setY(y);
				if(colid()){
					jump=false;
					y+=accel;
					poly.setY(y);
					accel=0 ;
				}
			if(accel>0)
				accel -= accelMod ;
		}

		// gravitatia
		y+=grav;
		poly.setY(y);
		if(colid()){
			y-=grav;
			poly.setY(y);
			jump=false;
			canjump=true;
			grav=4f;
			if(cadee > 40)
				takeLife(cadee/2);
//			if(cadee > 5) System.out.println("cazu : " + cadee); TODO debug
			cadee=0;
		}else{
			canjump=false;
			grav+=gravMod;
			cadee++;
		}
		if(grav > gravMax) grav=gravMax;
		
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
				
		if(input.isKeyDown(Input.KEY_F5)){
			x=2400;
			poly.setX(x);
			y=500;
			poly.setY(y);
		}
    }
    
    
	private boolean colid(){
    	for(float i=x ; i<=x+marime ; i+=30 )
    		for(float j=y ; j<=y+marime ; j+=30 )
    		{
    			if(blockmap.isBlock((int) i/32,(int) j/32)){
    				Rectangle rec = blockmap.getBlock((int) i/32,(int) j/32);
    					if( rec.intersects(poly)){
    						return true ;
    					}
    			}
    		}
    	return false ;
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
	
	public void addLife(int x){
		viata+=x;
		if(viata>100)viata=100;
	}
	public void takeLife(int x){
		viata-=x;
		if(viata<0){
			viata=0;
			//TODO mori
			System.out.println("esti moooort");
		}
		// TODO adauga efect cand suferi damage
	}
}
