package block;

import org.newdawn.slick.geom.Rectangle;

public class B_Solid extends Block {
	
	public B_Solid(String prop, int pozX, int pozY ) {
		super();
		solid=true;
		this.prop=prop;
		exist=true;
		zon = new Rectangle (32*pozX+1 , 32*pozY , 31 , 31) ;
	}
}