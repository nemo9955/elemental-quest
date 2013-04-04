package diverse;

import game.GameplayState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.AlegeLVL;
import states.DeathState;
import states.GameMenuState;
import states.HelpState;
import states.MainMenuState;
import states.SplashState;
import states.WinState;

/**
 * 
 * Lead Programmer Mogoi Adrian aka nemo9955
 * Grafic designer Caramete Elvis aka Keylos
 * Level developer Udrea Dragos aka dadu dad
 * 
 */

// modificare pentru test

public class Main extends StateBasedGame {

    private final static String titlu = "Elemental Quest";
    private final static String icons[] = { "res/icon16.png", "res/icon32.png", "res/icon24.png", "res/icon64.png" };

    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public static int del=18 ;

    // declaram o variabila statica corespunzatoare fiecarui stagiu
    public static String NIVEL = "res/level/nivel (1).tmx";
    public static String mesaj;

    public static final int SPLASH = 0;
    public static final int MAINMENUSTATE = 1;
    public static final int GAMEMENUSTATE = 2;
    public static final int GAMEPLAYSTATE = 3;
    public static final int DEATHSTATE = 4;
    public static final int ALEGELVL = 5;
    public static final int WINSTATE = 6;
    public static final int HELPSTATE = 7;

    public Main(String name) {
        super(name);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main(titlu));
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setTargetFrameRate(60);
        app.setResizable(true);
        app.setIcons(icons);
        app.setShowFPS(true);
        app.setVSync(true);
        app.start();
    }

    // initializam stadiile
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new SplashState(SPLASH));
        addState(new GameMenuState(GAMEMENUSTATE));
        addState(new GameplayState(GAMEPLAYSTATE));
        addState(new MainMenuState(MAINMENUSTATE));
        addState(new DeathState(DEATHSTATE));
        addState(new AlegeLVL(ALEGELVL));
        addState(new WinState(WINSTATE));
        addState(new HelpState(HELPSTATE));

    }

    public static void setNivel(String nivel) {
        NIVEL = nivel;
    }
}