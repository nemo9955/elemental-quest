package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMenuState extends BasicGameState {

	private int ID;
	
	private Input input ;
	private Image bg = null ;
	
	private Meniu resume ;
	private Meniu exit ;
	
	
	// stadiul meniului secundar
	@Override
	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		input = gc.getInput();
		bg = new Image("res/gm_fundal.png");
		resume = new Meniu( new Image("res/resume.jpg") , 300 , 300) ;
		exit = new Meniu( new Image("res/exit.jpg") , 300 , 400) ;
	}


	public GameMenuState(int ID) {
		this.ID=ID;
	}

	// verifica daca un "buton" a fost apasat
	public void update(GameContainer gc, StateBasedGame sb, int delta){
		if(input.isKeyPressed(Input.KEY_ESCAPE))
			sb.enterState(Main.GAMEPLAYSTATE);
		
		if( resume.inZon( input.getMouseX() , input.getMouseY() ) )
			if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON) )
				sb.enterState(Main.GAMEPLAYSTATE);
		
		if( exit.inZon( input.getMouseX() , input.getMouseY() ))
			if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON) )
				gc.exit();
	}

	public void render(GameContainer gc, StateBasedGame sb, Graphics g){
		bg.draw( gc.getWidth()/2-bg.getWidth()/2 , gc.getHeight()/2-bg.getHeight()/2 );
		resume.render();
		exit.render();

	}

	public int getID() {
		return ID;
	}


}
