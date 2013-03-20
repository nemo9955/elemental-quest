package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import diverse.Main;
import diverse.ManipImg;

public class AlegeLVL extends BasicGameState  {

	private int ID;
	private ManipImg img[] = new ManipImg [20];
	private Input input ;
	private Image imgf ;

	@Override
	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		input=gc.getInput();
		int index = 1 ;
		imgf = new Image("res/lvl_pick.png");
		for(float i=0 ; i<=gc.getWidth() ; i+=gc.getWidth()/5)
			for(float j=0 ; j<=gc.getHeight() ; j+=gc.getHeight()/4){
				if(index <= 20){
					img[index-1] = new ManipImg( new Image( String.format("res/img/imagine (%d).png", index) ), j+50 , i+10);
					index++;
				}
			}
		}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		for(int i=0 ; i<20 ; i++){
			if( img[i].inZon( input.getMouseX() , input.getMouseY() ) ){
				if( input.isMousePressed( Input.MOUSE_LEFT_BUTTON) ){
					Main.NIVEL = String.format("res/level/nivel (%d).tmx" , i+1) ;
					sb.enterState( Main.GAMEPLAYSTATE);
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)throws SlickException {
		imgf.draw(0,0);
		
		for(int i=0 ; i<20 ; i++)
			img[i].render();
				
	}

	@Override
	public int getID() {
		return ID;
	}

	public AlegeLVL(int ID){
		this.ID=ID;
	}

}
