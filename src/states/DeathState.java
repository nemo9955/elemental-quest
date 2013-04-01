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

public class DeathState extends BasicGameState {

    private Image death = null;
    private int ID;
    private Input input;

    public DeathState(int ID) {
        this.ID = ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
        death = new Image("res/death.png");
        input = gc.getInput();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
        if( input.isKeyDown(Input.KEY_SPACE) )
            sb.enterState(Main.MAINMENUSTATE);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        death.draw(0, 0);
        g.setColor(Color.white);
        g.drawString(Main.mesaj, 30, 500);
        // g.drawString("Press SPACE to restart." , 300 , 300);
    }

    public int getID() {
        return ID;
    }

}
