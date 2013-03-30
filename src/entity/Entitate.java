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
	
	protected Image img ;
	protected int team ;
	
	public static Player player ;
	protected int viataMax = 100 ;
	protected int viata = viataMax ;

	
	public  Entitate(Player player){
		Entitate.player=player;
	}
	
	public void upadte(GameContainer gc , int delta) throws SlickException{
		Gravitatie();
	}
	
	public void render(GameContainer gc , Graphics g){
		img.draw(x , y);
		g.setColor(Color.red);
		g.drawRect(x, y-15, img.getWidth(), 3);
		g.setColor(Color.green);
		g.drawRect(x, y-15,  LifeLS()*img.getWidth()  , 3);
	}
	
	public void takeLife(float x , String sursa){
		viata-=x;
		if(viata<=0){
			viata=0;
			elimina();
		}
	}
	
	public float LifeLS(){
		return (float) viata/viataMax ;
	}
	public float getViata(){
		return viata ;
	}
	
	public void addLife(int x){
		viata+=x;
		if(viata>viataMax)viata=viataMax;
	}
	
	// TODO entitati
	protected void elimina(){
		Obiecte.entit.remove(this);
	}
	
}
