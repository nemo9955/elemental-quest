package nivel;

import org.newdawn.slick.geom.Rectangle;

public class Block {
	
	private String prop ;
	private Rectangle zon ;
	private boolean exist ;
	
	// constructor in caz ca blocul e solid
	public Block ( String prop , int pozX , int pozY ){
//		System.out.println("Block " + pozX + " " + pozY);//TODO debug
		this.prop=prop;
		if(prop != "void")
			zon = new Rectangle (32*pozX+1 , 32*pozY , 31 , 31) ;
		else
			zon = new Rectangle (32*pozX+2 , 32*pozY+1 , 29 , 29) ;
		exist = true ;
	}
	
	// constructor in caz ca blocul nu exista
	public Block(){
		prop=null;
		zon=null;
		exist=false ;
	}
	// getters 
	public String getProp() {
		return prop;
	}

	public Rectangle getZon() {
		return zon;
	}

	public boolean exists() {
		return exist;
	}
}
