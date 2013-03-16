package nivel;

import game.Player;

import org.newdawn.slick.tiled.TiledMap;

public class Proprietati {
	
//	private TiledMap map ;
	
	private float accelIni ;
	private float accelMod  ;
	private float gravMax ;
	private float gravMod ;
	private float moveSpeedMax ;
	private float moveSpeedMod ;
//	private GameplayState gameps  ;
//	private Player player  ;
	
	
	public Proprietati (TiledMap map, Player player){
//		this.map=map;
		Gravitatie(map.getMapProperty("gravitatie", "medie"));
		Viteza(map.getMapProperty("gravitatie", "normala"));
//		gameps.player.setPlayer(accelIni, accelMod, gravMax, gravMod, moveSpeedMax, moveSpeedMod);
		player.setPlayer(accelIni, accelMod, gravMax, gravMod, moveSpeedMax, moveSpeedMod);
	}

	
	private void Gravitatie(String grav){
		switch(grav){
			case "mica" : {
				accelIni = 20f;
				accelMod = 0.6f ;
				gravMax = 15f;
				gravMod = 0.5f;
			}break;
			case "medie" : {
				accelIni = 20f;
				accelMod = 0.6f ;
				gravMax = 15f;
				gravMod = 0.5f;
			}break;
			case "mare" : {
				accelIni = 20f;
				accelMod = 0.6f ;
				gravMax = 15f;
				gravMod = 0.5f;
			}break;
		}
	}
	
	private void Viteza (String vit){
		switch(vit){
		case "mica" :{
			moveSpeedMax=8f;
			moveSpeedMod=0.5f;
		}
		case "normala" :{
			moveSpeedMax=8f;
			moveSpeedMod=0.5f;
		}
			case "mare" :{
				moveSpeedMax=8f;
				moveSpeedMod=0.5f;
			}
		}
	}
	
}
