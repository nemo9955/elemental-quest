package nivel;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import entity.Entitate;
import entity.Monstru;
import entity.Shot;
import entity.Solaris;
import game.GameplayState;
import game.Player;

public class Obiecte extends Entitate {
	
	private String prop ;
	
	// TODO entitati
	public static List<Monstru> monstru = new ArrayList<Monstru>();
	public static List<Solaris> solaris = new ArrayList<Solaris>();
	public static List<Shot> shot = new ArrayList<Shot>();
	public static Rectangle finish  ;
	
	public Obiecte(TiledMap map , GameContainer gc) throws SlickException{
		super(player);
		monstru.clear();
		solaris.clear();
		shot.clear();
		finish=new Rectangle( map.getWidth()*32-52 , 32 , 20 , map.getHeight()*32-64 ) ;
		for(int i=0 ; i<map.getObjectCount(0) ; i++){
			prop = map.getObjectName(0, i);
			// TODO entitati
			switch(prop){
			case "monstru" : monstru.add(monstru.size() , new Monstru ( map.getObjectX(0, i) , map.getObjectY(0, i) )); break ;
			case "solaris" : solaris.add(solaris.size() , new Solaris ( map.getObjectX(0, i) , map.getObjectY(0, i) )); break ;
			case "player"  : GameplayState.player = new Player(map.getObjectX(0, i) , map.getObjectY(0, i) , gc) ; break;
			case "end"     : finish = new Rectangle( map.getObjectX(0, i) , map.getObjectY(0, i) ,  map.getObjectWidth(0, i) , map.getObjectHeight(0, i));break;
			}
		}
	}

	// TODO entitati
	public List<Monstru> getMonstru() {
		return monstru;
	}
	public List<Solaris> getSolaris() {
		return solaris;
	}
	public List<Shot> getShot() {
		return shot;
	}
}
