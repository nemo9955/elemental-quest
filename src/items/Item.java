package items;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Item {

    protected Image img;
    protected Rectangle poly;
    protected float x;
    protected float y;
    protected String prop;
    protected int time = 0;

    public Item() {
        Random zar = new Random();
        time = zar.nextInt(360);
    }

    public void upadte(GameContainer gc, int delta) {
        MiscSinUD();
    }

    public void render(GameContainer gc, Graphics g) {
        img.draw(x, y);
    }

    protected void MiscSinUD() {
        time += 6;
        if( time >= 360 )
            time = 0;
        y += Math.sin(Math.toRadians(time));
        // System.out.println( Math.sin(Math.toRadians(time)));
    }

}
