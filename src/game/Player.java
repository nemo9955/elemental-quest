package game;

import entity.Entitate;
import entity.Shot;

import nivel.Obiecte;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entitate {

    public Color color = Color.blue;
    private Input input;
    private boolean dre = true;
    private boolean ent = true;
    private int focRate = 1;
    private int spikeRate = 0;

    private boolean justTele = false;
    private int teleRate = 0;

    // constructorul principal al jucatorului din gamestate
    public Player(float x, float y, GameContainer gc) throws SlickException {
        super(player);
        this.img = new Image("res/entitati/player.png");
        this.x = x;
        this.y = y;
        poly = new Rectangle(x, y, img.getWidth(), img.getHeight());
        input = gc.getInput();
        this.team = 5;
    }

    public void update(int delta, GameContainer gc) throws SlickException {

        // initializarea sariturii
        if( !jumping && canjump && input.isKeyDown(Input.KEY_W) ) {
            accel = accelIni;
            jumping = true;
        }

        Gravitatie();

        // miscarea
        if( !moving ) moveSpeed = 0;
        moving = false;

        // dreapta
        if( input.isKeyDown(Input.KEY_D) ) {
            dre = true;
            x += moveSpeed;
            poly.setX(x);
            if( colid() ) {
                x -= moveSpeed;
                poly.setX(x);
            }
            moveSpeed += moveSpeedMod;
            moving = true;
        }
        // stanga
        if( input.isKeyDown(Input.KEY_A) ) {
            dre = false;
            x -= moveSpeed;
            poly.setX(x);
            if( colid() ) {
                x += moveSpeed;
                poly.setX(x);
            }
            moveSpeed += moveSpeedMod;
            moving = true;
        }
        if( moveSpeed > moveSpeedMax ) moveSpeed = moveSpeedMax;

        if( dre != ent ) {
            img = img.getFlippedCopy(true, false);
            ent = !ent;
        }

        if( input.isKeyPressed(Input.KEY_F11) ) if( color == Color.blue )
            color = Color.green;
        else color = Color.blue;

        // TODO debug
        if( input.isKeyPressed(Input.KEY_F1) ) System.out.println(x + "  " + y);
        if( input.isKeyPressed(Input.KEY_F2) ) System.out.println(poly.getWidth());
        if( input.isKeyPressed(Input.KEY_F3) ) System.out.println("viata : " + viata);
        if( input.isKeyPressed(Input.KEY_F4) ) System.out.println(blockmap.getProp((int) x / 32, (int) y / 32));
        if( input.isKeyPressed(Input.KEY_F6) ) addLife(10);
        if( input.isKeyPressed(Input.KEY_F5) ) {
            x = 2400;
            poly.setX(x);
            y = 500;
            poly.setY(y);
        }

        if( focRate > 0 ) focRate += delta;
        if( focRate > 200 ) focRate = 0;

        if( spikeRate > 0 ) spikeRate += delta;
        if( spikeRate > 500 ) spikeRate = 0;

        if( justTele ) teleRate += delta;
        if( teleRate > 2000 ) {
            justTele = false;
            teleRate = 0;
        }

        if( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && focRate == 0 ) {
            float ang, mx, my;
            boolean sus;
            mx = input.getMouseX() - GameplayState.camera.getX();
            my = input.getMouseY() - GameplayState.camera.getY();
            // System.out.println( blockmap.getId((int) mx/32,(int) my/32) );
            ang = (float) Math.atan((poly.getCenterY() - my) / (poly.getCenterX() - mx));
            // System.out.println(Math.toDegrees(ang));
            sus = (my < poly.getCenterY()) ? true : false;
            Obiecte.entit.add(new Shot(poly.getCenterX(), poly.getCenterY(), team, ang, sus));
            focRate = 1;
        }
    }

    public void render(GameContainer gc, Graphics g) {
        g.setColor(color);
        g.setLineWidth(2);
        if( color == Color.green ) g.draw(poly);
        img.draw(x, y);
    }

    public void setFocRate(int x) {
        focRate = x;
    }

    public Rectangle getPoy() {
        return poly;
    }

    public float LifeLS() {
        return viata / 100f;
    }

    public float getViata() {
        return viata;
    }

    public void addLife(int x) {
        viata += x;
        if( viata > 100 ) viata = 100;
    }

    public boolean isJustTele() {
        return justTele;
    }

    public void setJustTele(boolean justTele) {
        this.justTele = justTele;
    }

    public void takeLife(float x, String sursa) {

        switch ( sursa ) {

            case "spike" :
                if( spikeRate == 0 && color == Color.blue ) {
                    viata -= x;
                    spikeRate = 1;
                }
                break;

            case "void" :
                viata -= x;
                break;

            case "shot" :
                viata -= x;
                moveSpeed = 0;
                break;

            case "cade" :
                viata -= x;

                break;

            case "monstru" :
                viata -= x;
                break;
        }

        if( viata < 0 ) {
            viata = 0;
        }
    }
}
