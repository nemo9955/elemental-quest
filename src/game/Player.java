package game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entitate{	
	
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
	public Player (float x , float y , int marime , GameContainer gc ) throws SlickException{
		super(blockmap);
		this.x=x;
		this.y=y;
		poly = new Rectangle (x , y, marime , marime);
		input = gc.getInput();
	}
	
    public void update( int delta){
		
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
		viata-=x;
		if(viata<0){
			viata=0;
			//TODO mori
			System.out.println("esti moooort");
		}
		// TODO adauga efect cand suferi damage
	}
}
