package game;

import nivel.Proprietati;

import org.newdawn.slick.geom.Rectangle;

import diverse.Main;

import entity.Entitate;

public class Physics extends Proprietati {

    protected float x;
    protected float y;
    protected float accel = 0;

    protected boolean jumping = false;
    protected boolean canjump = true;
    protected float grav;

    protected float moveSpeed;
    protected boolean moving = false;
    protected float cadee = 0;

    public Rectangle poly;

    public void Gravitatie(int delta) {

        // "comportamentul" sariturii
        if( jumping ) {
            y -= accel * delta/Main.del;
            poly.setY(y);
            if( colid() ) {
                jumping = false;
                y += accel * delta/Main.del;
                poly.setY(y);
                adapteaza(-1);
                accel = 0;
            }
            if( accel > 0 )
                accel -= accelMod * delta/Main.del;
            else jumping = false;
        }

        // gravitatia
        y += grav * delta/Main.del;
        poly.setY(y);
        if( colid() ) {
            y -= grav * delta/Main.del;
            poly.setY(y);
            jumping = false;
            canjump = true;
            accel = 0;
            adapteaza(1);
            grav = 0f;
            if( cadee > 170 )
                takeLifeFromEnt(cadee / 7.5f * gravMod);
            // if(cadee > 0) System.out.println("cazu : " + cadee);// TODO debug
            cadee = 0;
        } else {
            canjump = false;
            grav += gravMod * delta/Main.del;

            if( accel <= 0 && gravMax > 7 )
                cadee += grav / 3;
        }

        if( grav > gravMax )
            grav = gravMax;

    }

    // detectorul de coliziuni
    public boolean colid() {
        for( float i = x; i <= x + poly.getWidth(); i += (int) poly.getWidth() / Math.ceil(poly.getWidth() / 32) )
            for( float j = y; j <= y + poly.getHeight(); j += (int) poly.getHeight() / Math.ceil(poly.getHeight() / 32) ) {
                if( blockmap.isBlock((int) i / 32, (int) j / 32) ) {
                    Rectangle rec = blockmap.getBlock((int) i / 32, (int) j / 32);
                    if( rec.intersects(poly) ) {
                        blockmap.efect_block((int) i / 32, (int) j / 32, (Entitate) this);
                        if( blockmap.is_solid((int) i / 32, (int) j / 32) )
                            return true;
                    }
                }
            }
        return false;
    }

    // adapteaza pozitia entitatii cand aterizeaza sau se loveste cu capul de ceva
    public void adapteaza(float cantitate) {
        while (!colid()) {
            y += cantitate;
            poly.setY(y);
        }
        y -= cantitate;
        poly.setY(y);
    }

    // metoda care imi permite sa mut multe variabile / metode din Phisics in Entitate , facand structura claselor mai logica
    private void takeLifeFromEnt(float f) {
        Entitate en = (Entitate) this;
        en.takeLife(f, "cade");
    }

}
