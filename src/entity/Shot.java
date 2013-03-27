package entity;

import nivel.Obiecte;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Shot extends Entitate {

	private int lifeTime;
	private float ang;
	private boolean sus;
	private int speed;
	private int damage;

	public Shot(float x, float y, int team, float ang, boolean sus)
			throws SlickException {
		super(player);
		if (team == 5) {
			this.team = 6;
			speed = 10;
			lifeTime = 1500;
			damage = 35;
			this.img = new Image("res/entitati/shot_pl.png");
		} else if (team >= 10) {
			this.team = 7;
			speed = 7;
			damage = 1;
			lifeTime = 1600;
			this.img = new Image("res/entitati/shot_en.png");
		}
		// ahh matematica asta
		if (ang > 0)
			this.ang = -90 + (float) Math.toDegrees(ang);
		else
			this.ang = +90 + (float) Math.toDegrees(ang);
		this.sus = sus;
		// System.out.println(this.ang);

		this.specie = 3;
		this.x = x - img.getWidth() / 2;
		this.y = y - img.getHeight() / 2;
		poly = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
	}

	public void upadte(GameContainer gc, int delta) {

		if (sus) {
			x += speed * Math.sin(Math.toRadians(ang));
			y -= speed * Math.cos(Math.toRadians(ang));
		} else {
			x -= speed * Math.sin(Math.toRadians(ang));
			y += speed * Math.cos(Math.toRadians(ang));
		}

		lifeTime -= delta;
		poly.setX(x);
		poly.setY(y);
		if (colid() || lifeTime <= 0)
			elimina(specie);

		if (team == 6) {
			if (hitInamic())
				elimina(specie);
		} else {
			if (hitPlayer())
				elimina(specie);
		}
	}

	private boolean hitInamic() {

		for (Entitate inamic : Obiecte.entit)
			if (inamic.team >= 10)
				if (poly.intersects(inamic.poly)) {
					inamic.takeLife(damage);
					return true;
				}

		return false;
	}

	private boolean hitPlayer() {
		if (poly.intersects(player.poly) && player.color != Color.green) {
			player.takeLife(damage);
			return true;
		}
		return false;
	}

	public void render(GameContainer gc, Graphics g) {
		img.draw(x, y);
	}

}
