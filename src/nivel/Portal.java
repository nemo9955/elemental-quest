package nivel;

import game.Player;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.RoundedRectangle;

public class Portal {

    private RoundedRectangle port;
    private int to;
    private Random zar = new Random();
    private Color col;
    private boolean canTele;
    private int index;

    public Portal(int x, int y, int wi, int he, String index, String to) {
        port = new RoundedRectangle(x, y, wi, he, 1000);

        try {
            canTele = true;
            this.to = Integer.parseInt(to);
        } catch (Exception e) {
            canTele = false;
        }

        /*
         * if( to == "") canTele = false; else { this.to = Integer.parseInt(to);
         * }
         */

        this.index = Integer.parseInt(index);
        col = new Color(zar.nextInt(225), zar.nextInt(225), zar.nextInt(225));
    }

    public void update(GameContainer gc, int delta) {

        if( index == Player.player.getPrevPort() )
            if( !port.contains(Player.player.getPoy().getCenterX(), Player.player.getPoy().getCenterY()) )
                Player.player.setInPortal(false);

        if( port.contains(Player.player.getPoy().getCenterX(), Player.player.getPoy().getCenterY()) && Player.player.isInPortal() == false && canTele ) {
            Player.player.setX(Obiecte.portal.get(to).port.getCenterX() - Player.player.poly.getWidth() / 2);
            Player.player.setY(Obiecte.portal.get(to).port.getCenterY() - Player.player.poly.getHeight() / 2);
            Player.player.poly.setX(Player.player.getX());
            Player.player.poly.setY(Player.player.getY());
            Player.player.setInPortal(true);
            Player.player.setPrevPort(to);
        }
    }

    public void render(GameContainer gc, Graphics g) {

        // g.texture(port, img);
        g.setColor(col);
        g.fill(port);
    }

}
