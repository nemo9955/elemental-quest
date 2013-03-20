package game;

import nivel.Obiecte;
import nivel.Proprietati;

import org.newdawn.slick.geom.Rectangle;


public class Physics extends Proprietati{
	
	protected float x ;
	protected float y;
	protected float accel = 0;
	protected boolean jumping=false ;
	protected boolean canjump=true ;
	protected float grav;
	protected float moveSpeed;
	protected boolean moving = false ;
	
	public Rectangle poly ;
	
	protected float cadee=0 ;
	protected int viataMax = 100 ;
	protected int viata = viataMax ;
	protected int specie;

	public void setPhysics(float accelIni, float accelMod, float gravMax, float gravMod,float moveSpeedMax, float moveSpeedMod) {
		Physics.accelIni = accelIni;
		Physics.accelMod = accelMod;
		Physics.gravMax = gravMax;
		Physics.gravMod = gravMod;
		Physics.moveSpeedMax = moveSpeedMax;
		Physics.moveSpeedMod = moveSpeedMod;
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
    	for(float i=x ; i<=x+poly.getWidth() ; i+=(int)poly.getWidth()/Math.ceil(poly.getWidth()/32) )
    		for(float j=y ; j<=y+poly.getHeight() ; j+=(int)poly.getHeight()/Math.ceil(poly.getHeight()/32) )
    		{
    			if( blockmap.isBlock( (int)i/32 , (int)j/32 ) ){
    				Rectangle rec = blockmap.getBlock((int) i/32,(int) j/32);
    					if( rec.intersects(poly)){
    						if( blockmap.getProp((int) i/32,(int) j/32) == "void") takeLife( viata+1 );
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

		public void takeLife(float x){
			viata-=x;
			if(viata<=0){
				viata=0;
				elimina(specie);
			}
		}
		
		public float LifeLS(){
			return (float) viata/viataMax ;
		}
		public float getViata(){
			return viata ;
		}
		
		public void addLife(int x){
			viata+=x;
			if(viata>viataMax)viata=viataMax;
		}
		
		// TODO entitati
		protected void elimina(int specie2){
			switch(specie2){
			case 1 : Obiecte.monstru.remove( this ); break;
			case 2 : Obiecte.solaris.remove( this ); break;
			case 3 : Obiecte.shot.remove( this )   ; break;
			}
		}
}
