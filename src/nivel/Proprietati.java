package nivel;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Proprietati{
	
	protected static float accelIni = 20f;
	protected static float accelMod = 0.6f ;
	protected static float gravMax = 15f;
	protected static float gravMod = 0.5f;
	protected static float moveSpeedMax = 8f;
	protected static float moveSpeedMod = 0.5f;
	
	public static TiledMap map ;
	public static BlockMap blockmap ;
	
	public void initProp(TiledMap map , BlockMap blockmap) throws SlickException{
		Proprietati.map=map;
		Proprietati.blockmap=blockmap;
		Gravitatie(map.getMapProperty("gravity", "2"));
		Viteza(map.getMapProperty("speed", "2"));
//		System.out.println("prop : "+accelIni +" "+ accelMod +" "+ gravMax +" "+ gravMod +"  "+ moveSpeedMax +" "+ moveSpeedMod);
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
