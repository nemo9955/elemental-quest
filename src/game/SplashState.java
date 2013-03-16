package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

// TODO WIP
public class SplashState extends BasicGameState {

	private Image splash = null ;
	private int ID ;
	
	public SplashState(int ID){
		this.ID=ID;
	}
	
	// stadiul ecranului de incarcare
	@Override
	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		System.out.println("Init splash");
		splash = new Image("res/splash.jpg");
		render(gc, sb, null);
	}
	
	public void enter(GameContainer gc, StateBasedGame sb) throws SlickException {
		System.out.println("Entering state splash");
	}

	public void leave(GameContainer gc, StateBasedGame sb) throws SlickException {
		System.out.println("Leaving state splash");
	}
	

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		System.out.println("Update splash");
		sb.enterState(Main.MAINMENUSTATE);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)throws SlickException {
		System.out.println("Render splash");
		splash.draw(0,0);
	}


	public int getID() {
		return ID;
	}
	
	

}
