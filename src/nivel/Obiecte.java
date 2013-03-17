package nivel;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import entity.Monstru;
import entity.Solaris;

public class Obiecte {
	
	private String prop ;
	
	public List<Monstru> monstru = new ArrayList<Monstru>();
	public List<Solaris> solaris = new ArrayList<Solaris>();
	
	public Obiecte(TiledMap map) throws SlickException{
		
		for(int i=0 ; i<map.getObjectCount(0) ; i++){
			prop = map.getObjectName(0, i);
			switch(prop){
			case "monstru" : monstru.add(new Monstru ( map.getObjectX(0, i) , map.getObjectY(0, i))); break ;
			case "solaris"  : solaris.add(new Solaris ( map.getObjectX(0, i) , map.getObjectY(0, i))); break ;
			}
		}
	}

	public List<Monstru> getMonstru() {
		return monstru;
	}
	public List<Solaris> getSolaris() {
		return solaris;
	}
	
}
