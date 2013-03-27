package nivel;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class BlockMap {

	private Block bloc[][];
	private int solidNo;
	private int voidNo;
	private int mHeight;
	private int mWidth;

	// consctuctorul care salveaza fiecare obiect al clasei Block intr-o matrice
	public BlockMap(TiledMap map) {

		bloc = new Block[map.getWidth()][map.getHeight()];
		mHeight=map.getHeight();
		mWidth=map.getWidth();
		solidNo = map.getLayerIndex("solid");
		voidNo = map.getLayerIndex("void");
//		System.out.println(voidNo);
		for (int j = 0; j < map.getHeight(); j++)
			for (int i = 0; i < map.getWidth(); i++) {

				if (voidNo>=0 && map.getTileId(i, j, voidNo) > 0 )
					bloc[i][j] = new Block("void", i, j);
				else if(solidNo>=0 && map.getTileId(i, j, solidNo) > 0)
					bloc[i][j] = new Block("solid", i, j);
				else
					bloc[i][j] = new Block();

			}

	}

	// functii / metode pentru accesarea informatiilor din matricea de blocuri
	public boolean isBlock(int x, int y) {
		if( x<0 || x>mWidth-1 || y<0 || y>mHeight-1)
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
