package block;

import org.newdawn.slick.geom.Rectangle;

import entity.Entitate;

public class Block {
	
	protected String prop ;
	protected Rectangle zon ;
	protected boolean exist = false ;
	
	protected int ID ;
	
	// constructor in caz ca blocul e solid
	public Block(int id){
		this.ID=id;
	}
	
	public Block(boolean exist){
		this.exist=false;
	}
	
	// getters 
	
	public void efect(Entitate ent){
		
	}
	
	public int getID(){
		return ID ;
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
