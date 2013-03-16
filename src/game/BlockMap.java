package game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class BlockMap {

	private static Block bloc[][] ;
	private int solidNo ;

	// consctuctorul care salveaza fiecare obiect al clasei Block intr-o matrice
	public BlockMap(TiledMap map) throws SlickException{

		bloc = new Block [map.getWidth()] [map.getHeight()] ;

		solidNo = map.getLayerIndex("solid");

		bloc[2][48]= new Block ("solid" , 2, 48) ;
		
		for(int j=0 ; j<map.getHeight() ; j++)
			for(int i=0 ; i<map.getWidth() ; i++){

				int tileID = map.getTileId(i, j, solidNo);
//				String prop = map.getTileProperty(tileID, "solid", "nimic");
					
//				if(! "nimic".equals(prop)){
				if(tileID != 0)
					bloc[i][j] = new Block ("solid" , i, j) ;
				else
					bloc[i][j] = new Block ();
			}
		
	}
	
	// functii / metode pentru accesarea informatiilor din matricea de blocuri
	public boolean isBlock(int x , int y){
		return bloc[x][y].exists();
	}
	
	public Rectangle getBlock(int x , int y){
		return bloc[x][y].getZon();
	}

	public String getProp(int x , int y){
		return bloc[x][y].getProp();
	}
	
}
