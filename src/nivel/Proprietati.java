package nivel;

import game.Player;

import org.newdawn.slick.tiled.TiledMap;

public class Proprietati {
	
	private float accelIni ;
	private float accelMod  ;
	private float gravMax ;
	private float gravMod ;
	private float moveSpeedMax ;
	private float moveSpeedMod ;
	
	
	public Proprietati (TiledMap map, Player player){
		Gravitatie(map.getMapProperty("gravity", "2"));
		Viteza(map.getMapProperty("speed", "2"));
		player.setPlayer(accelIni, accelMod, gravMax, gravMod, moveSpeedMax, moveSpeedMod);
		System.out.println("prop : "+accelIni +" "+ accelMod +" "+ gravMax +" "+ gravMod +"  "+ moveSpeedMax +" "+ moveSpeedMod);
	}

	
	private void Gravitatie(String grav){
		switch(grav){
			case "1" : {
				accelIni = 11f;
				accelMod = 0.1f ;
				gravMax = 4f;
				gravMod = 1f;
			}break;
			case "2" : {
				accelIni = 20f;
				accelMod = 0.5f ;
				gravMax = 20f;
				gravMod = 1.2f;
			}break;
			case "3" : {
				accelIni = 21f;
				accelMod = 1f ;
				gravMax = 26f;
				gravMod = 1.8f;
			}break;
		}
	}
	
	private void Viteza (String vit){
		switch(vit){
		case "1" :{
			moveSpeedMax=5.6f;
			moveSpeedMod=0.2f;
		}break;
		case "2" :{
			moveSpeedMax=8f;
			moveSpeedMod=0.5f;
		}break;
		case "3" :{
			moveSpeedMax=12f;
			moveSpeedMod=1f;
		}break;
		}
	}
	
}
