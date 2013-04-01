package block;

import org.newdawn.slick.geom.Rectangle;

import entity.Entitate;

public class B_Stopper extends Block {

    public B_Stopper(String prop, int pozX, int pozY) {
        super();
        solid = false;
        this.prop = prop;
        exist = true;
        zon = new Rectangle(32 * pozX + 1, 32 * pozY, 32, 32);
    }

    public void efect(Entitate ent) {
        ent.setCadee(0f);
    }

}
