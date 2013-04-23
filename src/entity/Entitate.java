package entity;

import game.Physics;
import game.Player;
import nivel.Obiecte;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entitate extends Physics {

    protected Image img;
    protected int team;

    public static Player player;
    protected int viataMax = 100;
    protected int viata = viataMax;

    public Entitate(Player player) {
        Entitate.player = player;
    }

    public void upadte(GameContainer gc, int delta) throws SlickException {
        Gravitatie(delta);
    }

    // modul default de randare a entitatiilor
    public void render(GameContainer gc, Graphics g) {
        img.draw(x, y);
        g.setColor(Color.red);
        g.drawRect(x, y - 15, img.getWidth(), 3);
        g.setColor(Color.green);
        g.drawRect(x, y - 15, LifeLS() * img.getWidth(), 3);
    }

    // da dam,age entitatii
    public void takeLife(float x, String sursa) {
        viata -= x;
        if( viata <= 0 ) {
            viata = 0;
            elimina();
        }
    }

    // getters and setters
    public float LifeLS() {
        return (float) viata / viataMax;
    }

    public float getViata() {
        return viata;
    }

    public void addLife(int x) {
        viata += x;
        if( viata > viataMax )
            viata = viataMax;
    }

    protected void elimina() {
        Obiecte.entit.remove(this);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getCadee() {
        return cadee;
    }

    public void setCadee(float cadee) {
        this.cadee = cadee;
    }

    public float getGrav() {
        return grav;
    }

    public void setGrav(float grav) {
        this.grav = grav;
    }

    public float getAccel() {
        return accel;
    }

    public void setAccel(float accel) {
        this.accel = accel;
    }
    
    

}
