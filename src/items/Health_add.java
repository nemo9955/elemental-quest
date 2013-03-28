package items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Health_add extends Item {

	public Health_add(float x, float y) throws SlickException {
		super();
		img = new Image("res/items/health_add.png");
		this.poly = new Rectangle(x, y, img.getWidth(), img.getHeight());
		prop = "health_add";
		this.x = x;
		this.y = y;
	}

	public void upadte(GameContainer gc, int delta) {
		MiscSinUD();
	}

}
