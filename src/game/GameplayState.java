package game;

import nivel.BlockMap;
import nivel.Obiecte;
import nivel.Proprietati;
import diverse.Main;
import entity.Entitate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class GameplayState extends BasicGameState {

	private int ID ;
	private boolean firstT ;
	private Input input ;
	public static TiledMap map;
	public static Camera camera ;
	private Image health;
	private float init;
	private float rap =0;
	public static Proprietati prop ;
	public BlockMap blockmap;
	public static Player player;
	private Obiecte obi ;
	private Entitate entit ;
	
	public GameplayState(int ID , String harta) {
		this.ID=ID;
	}

	// stadiul jocului insasi
	@Override
	public void init(GameContainer gc, StateBasedGame sb)throws SlickException {
		input = gc.getInput();
		prop = new Proprietati ();
		firstT = true;
	}
	
	public void startGen (GameContainer gc)throws SlickException {
		map = new TiledMap(Main.NIVEL);
		blockmap = new BlockMap(map);
		System.out.println(Main.NIVEL);
		prop.initProp(map,blockmap);
		obi = new Obiecte(map,gc);
		setEntit(new Entitate(player));
		camera = new Camera(map.getWidth()*map.getTileWidth() , map.getHeight()*map.getTileHeight());
		health=new Image("res/health_bar1.png");
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sb)throws SlickException {
		if(firstT){
			startGen(gc);
		}else{
			if (player.getViata() <= 0)
				startGen(gc);
		}
			
		
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)throws SlickException {
		player.update( delta , gc);
		
		//TODO entitati
		for(int i=0 ; i<obi.getMonstru().size() ; i++)
			Obiecte.monstru.get(i).upadte(gc, delta);
		for(int i=0 ; i<obi.getSolaris().size() ; i++)
			Obiecte.solaris.get(i).upadte(gc, delta);
		for(int i=0 ; i<obi.getShot().size() ; i++)
			Obiecte.shot.get(i).upadte(gc, delta);
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
			sb.enterState(Main.GAMEMENUSTATE);
		if(player.getViata() <= 0)
			sb.enterState(Main.DEATHSTATE);
		
//		TODO debug tool
		if(input.isKeyPressed(Input.KEY_F1))
			System.out.println(camera.getX() + "  " + camera.getY() );
//			System.out.println(input.get + "  " + input.getAbsoluteMouseY() );
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)throws SlickException {
		camera.translate(g, player);
		map.render(0, 0);
		
		//TODO entitati
		for(int i=0 ; i<obi.getMonstru().size() ; i++)
			Obiecte.monstru.get(i).render(gc, g);
		for(int i=0 ; i<obi.getSolaris().size() ; i++)
			Obiecte.solaris.get(i).render(gc, g);
		for(int i=0 ; i<obi.getShot().size() ; i++)
			Obiecte.shot.get(i).render(gc, g);
		
		renderHealthBar();
		player.render(gc, g);
	}

	private void renderHealthBar(){
		// afla cat trebuie sa randeze din bara de viata
		init= health.getWidth() - ( player.LifeLS() * health.getWidth() );
		
		// folosite pentru modificarea treptata marimii imaginii
		if( init > rap ) rap+=11;
		if(rap>init)     rap=init;
		
		Vector2f loc    = new Vector2f(-camera.getX()+25 ,-camera.getY()+20);
		Vector2f pozimg = new Vector2f(health.getWidth()-camera.getX()+25-rap , health.getHeight()-camera.getY()+20);
		Vector2f recini = new Vector2f(0 , 0);
		Vector2f recfin = new Vector2f(health.getWidth()-rap , health.getHeight());
		// desenez o parte din imagine ( in functie de viata jucatorului )
		health.draw(loc.x , loc.y , pozimg.x , pozimg.y , recini.x , recini.y ,recfin.x , recfin.y );
	}
	
	public int getID() { 
		return ID;
	}

	public Entitate getEntit() {
		return entit;
	}

	public void setEntit(Entitate entit) {
		this.entit = entit;
	}
	
}
