package block;

import org.newdawn.slick.geom.Rectangle;

public class Block {
	
	protected String prop ;
	protected Rectangle zon ;
	protected boolean exist ;
	
	// constructor in caz ca blocul e solid
	public Block ( String prop , int pozX , int pozY ){
//		System.out.println("Block " + pozX + " " + pozY);//TODO debug
		this.prop=prop;
		if(prop != "void")
			zon = new Rectangle (32*pozX+1 , 32*pozY , 31 , 31) ;
		else
			zon = new Rectangle (32*pozX+10 , 32*pozY+10 , 10 , 10) ;
		exist = true ;
	}
	
	// constructor in caz ca blocul nu exista
	public Block(){
		exist=false ;
	}
	
	// getters 
	
	public void efect(){
		
	}
	
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
