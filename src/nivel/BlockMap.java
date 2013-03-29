package nivel;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import entity.Entitate;

import block.B_Solid;
import block.B_Void;
import block.Block;

public class BlockMap {

	private Block bloc[][];
	private int mainNo;
	private int mHeight;
	private int mWidth;

	// consctuctorul care salveaza fiecare obiect al clasei Block intr-o matrice
	public BlockMap(TiledMap map) {

		bloc = new Block[map.getWidth()][map.getHeight()];
		mHeight = map.getHeight();
		mWidth = map.getWidth();
		mainNo = map.getLayerIndex("solid");
		// System.out.println(voidNo);
		for (int j = 0; j < map.getHeight(); j++)
			for (int i = 0; i < map.getWidth(); i++) {
				blockTip(map.getTileId(i, j, mainNo), i, j);
			}

	}

	private void blockTip(int id, int i, int j) {

		if (id >= 175 && id <= 178) {
			
			bloc[i][j] = new B_Void("void", i, j , id);
			return;
			
		} else if (id >= 1 ) {
			
			bloc[i][j] = new B_Solid("solid", i, j , id);
			return;
			
		}
		
		bloc[i][j]=new Block (id);
		
	}

	// functii / metode pentru accesarea informatiilor din matricea de blocuri
	
	public int getId(int x, int y){
		return bloc[x][y].getID();
	}
	
	public void efect_block(int x, int y , Entitate ent){
		bloc[x][y].efect(ent);
	}
	
	public boolean isBlock(int x, int y) {
		if (x < 0 || x > mWidth - 1 || y < 0 || y > mHeight - 1)
			return false;
		return bloc[x][y].exists();
	}

	public Rectangle getBlock(int x, int y) {
		return bloc[x][y].getZon();
	}

	public String getProp(int x, int y) {
		return bloc[x][y].getProp();
	}

}
