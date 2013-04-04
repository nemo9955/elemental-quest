package entity;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Monstru extends Entitate {

    private int viataRate = 0;
    private float maxx;
    private float minx;
    private boolean dr;

    // constructorul monstrului
    public Monstru(float x, float y, int range) throws SlickException {
        super(player);
        this.img = new Image("res/entitati/monstru.png");
        Random zar = new Random();
        this.x = x + zar.nextInt( range-((range>img.getWidth()) ? img.getWidth() : 1) );
        this.y = y;
        dr = zar.nextBoolean();
        poly = new Rectangle(this.x, y, img.getWidth(), img.getHeight());
        maxx = x + range - poly.getWidth();
        minx = x;
        this.team = 10;
        this.viataMax = 70;
        this.viata = viataMax;
    }

    // comportamentul monstrului
    public void upadte(GameContainer gc, int delta) {

        Gravitatie(delta);

        // miscarea
        if( !moving )
            moveSpeed = 0;
        moving = false;

        // miscarea stanga - dreapta

        if( x >= maxx ) {
            dr = false;
            moveSpeed = 1;
        }
        if( x <= minx ) {
            dr = true;
            moveSpeed = 1;
        }

        if( dr )
            moveD();
        else moveS();

        if( !jumping && canjump ) {
            accel = accelIni;
            jumping = true;
        }

        if( moveSpeed > moveSpeedMax )
            moveSpeed = moveSpeedMax;

        if( viataRate > 0 )
            viataRate += delta;
        if( viataRate > 300 )
            viataRate = 0;

        if( poly.intersects(player.getPoy()) && player.color == Color.blue && viataRate == 0 ) {
            player.takeLife(13, "monstru");
            viataRate = 1;
        }
    }

    // destul de ineficient facute da isi fac treaba
    private void moveD() {
        move(1);
    }

    private void moveS() {
        move(-1);
    }

    private void move(int d) {
        x += moveSpeed * d;
        poly.setX(x);
        if( colid() ) {
            x -= moveSpeed * d;
            poly.setX(x);
        }
        moveSpeed += moveSpeedMod;
        moving = true;
    }

}
