package nivel;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import block.B_Solid;
import block.B_Spike;
import block.B_Stopper;
import block.B_Void;
import block.Block;
import entity.Entitate;

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

        for( int j = 0; j < map.getHeight(); j++ )
            for( int i = 0; i < map.getWidth(); i++ ) {
                blockTip(map.getTileId(i, j, mainNo), i, j, map);
            }

    }

    // aici verific tipul block-ului
    private void blockTip(int id, int i, int j, TiledMap map) {
        if( id <= 0 ) {
            bloc[i][j] = new Block(false);
            return;
        }

        // TODO in caz de block nou , trebuie adaugat in switch
        for( int p = 0; p < map.getTileSetCount(); p++ )
            if( map.getTileSet(p).contains(id) ) {
                switch ( map.getTileSet(p).name ) {
                    case "void" :
                        bloc[i][j] = new B_Void("void", i, j);
                        return;

                    case "spike" :
                        bloc[i][j] = new B_Spike("spike", i, j);
                        return;

                    case "stopper" :
                        bloc[i][j] = new B_Stopper("stopper", i, j);
                        return;

                    default :
                        bloc[i][j] = new B_Solid("solid", i, j);
                        return;
                }
            }
    }

    // functii / metode pentru accesarea informatiilor din matricea de blocuri

    public void efect_block(int x, int y, Entitate ent) {
        bloc[x][y].efect(ent);
    }

    public boolean isBlock(int x, int y) {
        if( x < 0 || x > mWidth - 1 || y < 0 || y > mHeight - 1 )
            return false;
        return bloc[x][y].exists();
    }

    public Rectangle getBlock(int x, int y) {
        return bloc[x][y].getZon();
    }

    public String getProp(int x, int y) {
        return bloc[x][y].getProp();
    }

    public boolean is_solid(int x, int y) {
        return bloc[x][y].getSolis();
    }

}
