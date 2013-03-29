package block;

import org.newdawn.slick.geom.Rectangle;

import entity.Entitate;

public class B_Void extends Block {

	public B_Void(String prop, int pozX, int pozY , int id) {
		super(id);
		this.prop=prop;
		exist=true;
		zon = new Rectangle (32*pozX+10 , 32*pozY+10 , 10 , 10) ;
	}

	public void efect(Entitate ent) {
		ent.takeLife(1000f);
	}

}
