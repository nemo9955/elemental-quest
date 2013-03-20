package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import diverse.Main;

public class WinState extends BasicGameState{
	
	private int ID;
	private Image fin ;
	private Input input ;
	
	public int getID() {
		return ID;
	}
	
	public WinState(int ID){
		this.ID=ID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		input = gc.getInput();
		fin=new Image ("res/finish.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)throws SlickException {
		fin.draw(0,0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		if(input.isKeyPressed(Input.KEY_SPACE))
			sb.enterState(Main.MAINMENUSTATE);
	}
	

	
}
