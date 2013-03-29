package nivel;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

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

		if (id > 178 && id <= 182) {
			bloc[i][j] = new B_Void("void", i, j);
			return;
		} else if (id > 0 && id <= 178) {
			return;
		}

		bloc[i][j] = new Block();
	}

	// functii / metode pentru accesarea informatiilor din matricea de blocuri
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
