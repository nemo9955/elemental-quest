package diverse ;

import game.GameplayState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.GameMenuState;
import states.MainMenuState;
import states.SplashState;

public class Main extends StateBasedGame{

	private final static String titlu = "Elemental Quest" ;
	
	public static int WIDTH = 800 ;
	public static int HEIGHT = 600 ;
	
	// declaram o variabila statica corespunzatoare fiecarui stagiu
	public static final int SPLASH = 0;
	public static final int MAINMENUSTATE = 1;
    public static final int GAMEMENUSTATE = 2;
    public static final int GAMEPLAYSTATE = 3;
    
	
	public Main(String name) {
		super(name);
	}

    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new Main(titlu));
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setTargetFrameRate(60);
        app.setShowFPS(false);
        app.setVSync(true);
        app.start();
   }
	
    // initializam stadiile
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState( new SplashState  ( SPLASH ) );
		addState( new GameMenuState( GAMEMENUSTATE ) );
		addState( new GameplayState( GAMEPLAYSTATE ) );
		addState( new MainMenuState( MAINMENUSTATE ) );
		 
	}

	
}