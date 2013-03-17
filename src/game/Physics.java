package game;

import nivel.BlockMap;

import org.newdawn.slick.geom.Rectangle;

public class Physics {

	protected float x ;
	protected float y;
//	protected int  marime ;
	protected float accel = 0;
	protected boolean jumping=false ;
	protected boolean canjump=true ;
	protected float grav;
	protected float moveSpeed;
	protected boolean moving = false ;
	
	protected Rectangle poly ;
	protected static BlockMap blockmap ;
	
	protected float accelIni = 20f;
	protected float accelMod = 0.6f ;
	protected float gravMax = 15f;
	protected float gravMod = 0.5f;
	protected float moveSpeedMax = 8f;
	protected float moveSpeedMod = 0.5f;
	
	protected int viata = 100 ;
	protected float cadee=0 ;
	
	public Physics(BlockMap blockmap){
		Physics.blockmap = blockmap ;
	}
	
	public void Gravitatie(){
		// "comportamentul" sariturii
				if(jumping){
					y-=accel;
					poly.setY(y);
						if(colid()){
							jumping=false;
							y+=accel;
							poly.setY(y);
							adapteaza(-1);
							accel=0 ;
						}
					if(accel>0)
						accel -= accelMod ;
					else
						jumping=false;
				}

				// gravitatia
				y+=grav;	poly.setY(y);
				if(colid()){
					y-=grav;	poly.setY(y);
					jumping=false; canjump=true;
					accel=0;
					adapteaza(1);
					grav=0f;
					if(cadee >  170 )
						takeLife( cadee/7.5f*gravMod );
//					if(cadee > 0) System.out.println("cazu : " + cadee);// TODO debug
					cadee=0;
				}else{
					canjump=false;
					grav+=gravMod;
					
					if(accel<=0 && gravMax>7)  cadee+=grav/3;
				}
				if(grav > gravMax) grav=gravMax;
				
	}
	
	public boolean colid(){
    	for(float i=x ; i<=x+poly.getWidth() ; i+=30 )
    		for(float j=y ; j<=y+poly.getHeight() ; j+=30 )
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
	
	 public void adapteaza(float cantitate){
	    	while( !colid() ){
				y+=cantitate;
				poly.setY(y);
	    	}
					y-=cantitate;
					poly.setY(y);
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
			}
		}
	 
}
