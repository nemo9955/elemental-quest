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
	public static List<Entitate> entit = new ArrayList<Entitate>();
	public static List<Rectangle> finish = new ArrayList<Rectangle>()  ;
	
	public Obiecte(TiledMap map , GameContainer gc) throws SlickException{
		super(player);
		monstru.clear();
		solaris.clear();
		shot.clear();
		finish.clear();
		for(int i=0 ; i<map.getObjectCount(0) ; i++){
			prop = map.getObjectName(0, i);
			// TODO entitati
			switch(prop){
			case "monstru" : entit.add(new Monstru ( map.getObjectX(0, i), map.getObjectY(0, i) , map.getObjectWidth(0,i) )); break ;
			case "solaris" : entit.add(new Solaris ( map.getObjectX(0, i) , map.getObjectY(0, i) )); break ;
			case "player"  : GameplayState.player = new Player(map.getObjectX(0, i) , map.getObjectY(0, i) , gc) ; break;
			case "end"     : finish.add(new Rectangle( map.getObjectX(0, i) , map.getObjectY(0, i) ,  map.getObjectWidth(0, i) , map.getObjectHeight(0, i))); break ;
			}
		}
		if(finish.size()==0)
			finish.add(new Rectangle( map.getWidth()*32-52 , 32 , 20 , map.getHeight()*32-64 )) ;
	}

	// TODO entitati

	public List<Rectangle> getFinish() {
		return finish;
	}
}
