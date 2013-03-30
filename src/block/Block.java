package block;

import org.newdawn.slick.geom.Rectangle;

import entity.Entitate;

public class Block {
	
	protected String prop ;
	protected Rectangle zon ;
	protected boolean exist = false ;
	protected boolean solid = true ;

	
	// constructor in caz ca blocul e solid
	public Block(){
		
	}
	
	public Block(boolean exist){
		this.exist=false;
	}
	
	// getters 
	
	public void efect(Entitate ent){
		// daca blocul are un efect special , va fi adaugat in clasa lui
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

    public boolean getSolis() {
        return solid;
    }
}
