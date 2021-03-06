package entity;

import nivel.Obiecte;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import diverse.Main;

public class Shot extends Entitate {

    private int lifeTime;
    private float ang;
    private boolean sus;
    private int speed;
    private int damage;

    // constructorul shot-ului , face unele atribuiri in functie entitatea care
    // l-a tras
    public Shot(float x, float y, int team, float ang, boolean sus) throws SlickException {
        super(player);
        if( team == 5 ) {
            this.team = 6;
            speed = 10;
            lifeTime = 1400;
            damage = 35;
            this.img = new Image("res/entitati/shot_pl.png");
        } else if( team >= 10 ) {
            this.team = 7;
            speed = 7;
            damage = 1;
            lifeTime = 1750;
            this.img = new Image("res/entitati/shot_en.png");
        }
        // ahh matematica asta
        if( ang > 0 )
            this.ang = -90 + (float) Math.toDegrees(ang);
        else this.ang = +90 + (float) Math.toDegrees(ang);
        this.sus = sus;
        // System.out.println(this.ang);

        this.x = x - img.getWidth() / 2;
        this.y = y - img.getHeight() / 2;
        poly = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    }

    // Huston , avem o problema pe aici pe undeva
    // P.S. problema nu era aici

    public void upadte(GameContainer gc, int delta) {

        if( sus ) {
            x += speed * Math.sin(Math.toRadians(ang)) * delta/Main.del;
            y -= speed * Math.cos(Math.toRadians(ang)) * delta/Main.del;
        } else {
            x -= speed * Math.sin(Math.toRadians(ang)) * delta/Main.del;
            y += speed * Math.cos(Math.toRadians(ang)) * delta/Main.del;
        }

        lifeTime -= delta;
        poly.setX(x);
        poly.setY(y);
        if( colid() || lifeTime <= 0 )
            Obiecte.entit.remove(this);

        if( team == 6 ) {
            if( hitInamic() )
                elimina();
        } else {
            if( hitPlayer() )
                elimina();
        }
    }

    // in caz ca e tras de jucator
    private boolean hitInamic() {

        for( Entitate inamic : Obiecte.entit )
            if( inamic.team >= 10 )
                if( poly.intersects(inamic.poly) ) {
                    inamic.takeLife(damage, "shot");
                    return true;
                }

        return false;
    }

    // in caz ca e tras de solarys
    private boolean hitPlayer() {
        if( poly.intersects(player.poly) && player.color != Color.green ) {
            player.takeLife(damage, "shot");
            return true;
        }
        return false;
    }

    public void render(GameContainer gc, Graphics g) {
        img.draw(x, y);
    }

}
