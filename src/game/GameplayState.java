package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class GameplayState extends BasicGameState {

	private int ID ;
	private Player player = null ;
	private Input input ;
	private TiledMap map;
	private int  mapW ;
	private int  mapH ;
	public Camera camera ;
	
	public GameplayState(int ID) {
		this.ID=ID;
	}
	
	// stadiul jocului insasi
	@Override
	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		System.out.println("Init game");
		map = new TiledMap("res/level/nivel.tmx");
		mapW= map.getWidth()*map.getTileWidth() ;
		mapH = map.getHeight()*map.getTileHeight();
		player = new Player (75 , 1400 , 60 , gc , map);
		camera = new Camera(mapW , mapH);
		input = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		player.update(gc, delta);
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
			sb.enterState(Main.GAMEMENUSTATE);
		
/*		TODO debug tool
		if(input.isKeyPressed(Input.KEY_F1))
			System.out.println(input.getMouseX() + "  " + input.getMouseY() );
			System.out.println(input.get + "  " + input.getAbsoluteMouseY() );
*/
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)throws SlickException {
		camera.translate(g, player);
		map.render(0, 0);
		player.render(gc, g);
	}
	
	public int getID() { 
		return ID;
	}

	
}
