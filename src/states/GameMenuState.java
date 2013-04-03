package states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import diverse.Main;
import diverse.ManipImg;

public class GameMenuState extends BasicGameState {

    private int ID;

    private Input input;
    private Image bg = null;

    private ManipImg resume;
    private ManipImg mainM;
    private ManipImg exit;

    // stadiul meniului secundar
    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
        input = gc.getInput();
        bg = new Image("res/gm_fundal.png");
        resume = new ManipImg(new Image("res/resume.png"), 300, 200);
        mainM = new ManipImg(new Image("res/mainM.png"), 300, 300);
        exit = new ManipImg(new Image("res/exit.png"), 300, 400);
    }

    public GameMenuState(int ID) {
        this.ID = ID;
    }

    // verifica daca un "buton" a fost apasat
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        if( input.isKeyPressed(Input.KEY_ESCAPE) )
            sb.enterState(Main.GAMEPLAYSTATE);

        if( mainM.inZon(input.getMouseX(), input.getMouseY()) )
            if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
                sb.enterState(Main.MAINMENUSTATE);

        if( resume.inZon(input.getMouseX(), input.getMouseY()) )
            if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
                sb.enterState(Main.GAMEPLAYSTATE);

        if( exit.inZon(input.getMouseX(), input.getMouseY()) )
            if( input.isMousePressed(Input.MOUSE_LEFT_BUTTON) )
                gc.exit();
    }

    public void render(GameContainer gc, StateBasedGame sb, Graphics g) {
        g.setBackground(Color.black);
        bg.draw(0,0);
        resume.render();
        exit.render();
        mainM.render();

    }

    public int getID() {
        return ID;
    }

}
