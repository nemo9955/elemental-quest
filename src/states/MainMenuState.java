package states;

import diverse.Main;
import diverse.ManipImg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

	private Input input ;
	private Image bg = null ;
	private int ID;
	private ManipImg start ;
	private ManipImg exit ;
	
	
	public MainMenuState( int ID ) {
		this.ID = ID;
	}
	
	// stadiul meniului principal , care apare dupa pornirea jocului
	@Override
	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		input =gc.getInput();
		bg = new Image("res/fundal.jpg");
		start = new ManipImg( new Image("res/start.jpg") , 300 , 300) ;
		exit = new ManipImg( new Image("res/exit.jpg") , 300 , 400) ;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		
		if( start.inZon( input.getMouseX() , input.getMouseY() ) )
			if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON) )
				sb.enterState(Main.GAMEPLAYSTATE);

		if(exit.inZon(input.getMouseX(), input.getMouseY()))
			if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON) )
				gc.exit();

	}
	@Override
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)throws SlickException {
		bg.draw(0,0);
		start.render();
		exit.render();
	}

	public int getID() {
		return ID;
	}


}
