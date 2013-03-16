package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import diverse.Main;

public class Camera {
	
	private int transX;
	private int transY;
	private int mapWidth, mapHeight;
	private Vector2f cen ;

	public Camera(int  mapWidth , int mapHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		transX = 0;
		transY = 0;
	}
	
	public void translate (Graphics g, Player player) {
		
		cen = new Vector2f ( player.getX()+player.getMarime()/2 , player.getY()+player.getMarime()/2 ) ;
		
		/*
		 *  verifica fiecare axa in parte astfel incat
		 *  camera sa fie centrata pe jucator cand este posibil , dar sa nu afiseze in afara hartii
		 */
		
		// axa x
		if(cen.x - Main.WIDTH/2 < 0)
			transX = 0 ;
		else if( cen.x + Main.WIDTH/2 > mapWidth )
			transX = -mapWidth + Main.WIDTH ;
		else
    		transX = (int)-cen.x+Main.WIDTH/2;
		
		// axa y
		if(cen.y - Main.HEIGHT/2 < 0)
			transY = 0 ;
		else if( cen.y + Main.HEIGHT/2 > mapHeight )
			transY = -mapHeight + Main.HEIGHT ;
		else
			transY = (int)-cen.y+Main.HEIGHT/2;

 
    	g.translate(transX, transY);
    	
	}
	
	public int getX(){
		return transX;
	}
	public int getY(){
		return transY;
	}
	
}
