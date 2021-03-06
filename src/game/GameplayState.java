package game;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nivel.BlockMap;
import nivel.Obiecte;
import nivel.Proprietati;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import diverse.Main;
import entity.Entitate;

public class GameplayState extends BasicGameState {

    private int ID;
    private boolean firstT;
    private String curent;;
    private Input input;
    public static TiledMap map;
    public static Camera camera;
    private Image health;
    private Image finFill;
    private float init;
    private float rap = 0;
    public static Proprietati prop;
    public BlockMap blockmap;
    public static Player player;
    private Obiecte obi;
    private Entitate entit;
    public long time = 0;
    private int wallLayerNo;
    private Music music ;

    public GameplayState(int ID) {
        this.ID = ID;
    }

    // stadiul jocului insasi
    @Override
    public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
        input = gc.getInput();
        prop = new Proprietati();
        firstT = true;
        finFill = new Image("res/finish_fill.png");
        music = new Music("res/sound/bg_music.wav");
    }

    // reincarca clasele atunci cand se schimba nivelul
    public void startGen(GameContainer gc) throws SlickException {
        curent = Main.NIVEL;
        time = 0;
        map = new TiledMap(Main.NIVEL);
        blockmap = new BlockMap(map);
        // System.out.println(Main.NIVEL);
        prop.initProp(map, blockmap);
        obi = new Obiecte(map, gc);
        setEntit(new Entitate(player));
        camera = new Camera(map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight());
        health = new Image("res/health_bar1.png");
        wallLayerNo = map.getLayerIndex("wall");
    }

    // verifica daca e prima data cand e incarcat un nivel
    @Override
    public void enter(GameContainer gc, StateBasedGame sb) throws SlickException {
        music.loop();
        if( firstT ) {
            startGen(gc);
            firstT = false;
        } else {
            if( player.getViata() <= 0 || Main.NIVEL != curent )
                startGen(gc);
        }
    }
    
    @Override
    public void leave(GameContainer gc, StateBasedGame sb){
        music.stop();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
        time += delta;
        player.update(delta, gc);

        for( int i = 0; i < Obiecte.entit.size(); i++ ){
           Obiecte.entit.get(i).upadte(gc, delta);
        }
        
        for( int i = 0; i < Obiecte.items.size(); i++ )
            Obiecte.items.get(i).upadte(gc, delta);
        
        for( int i = 0; i < Obiecte.portal.size(); i++ )
            Obiecte.portal.get(i).update(gc, delta);

        if( input.isKeyPressed(Input.KEY_ESCAPE) ) {
            player.setFocRate(1);
            sb.enterState(Main.GAMEMENUSTATE);
        }
        if( player.getViata() <= 0 ) {
            Main.mesaj = convTime("Ai rezistat eroic");
            sb.enterState(Main.DEATHSTATE);
        }
        for( int i = 0; i < obi.getFinish().size(); i++ )
            if( player.poly.intersects(Obiecte.finish.get(i)) ) {
                Main.mesaj = convTime("Ai terminat in doar");
                sb.enterState(Main.WINSTATE);
            }

        // cand apesi p face screenshot
        if( input.isKeyPressed(Input.KEY_P) ) {
            Image target = new Image(gc.getWidth(), gc.getHeight());
            gc.getGraphics().copyArea(target, 0, 0);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH,mm,ss");
            Date date = new Date();

            // in caz ca fisierul nu exista il creaza automat
            File x = new File("screenshot");
            if( !x.exists() )
                x.mkdir();

            String pozaTit = String.format("screenshot/screen%s.jpg", dateFormat.format(date));
            ImageOut.write(target.getFlippedCopy(false, false), pozaTit, false);
            target.destroy();
        }

        /*
         * TODO debug tool if(input.isKeyPressed(Input.KEY_F1))
         * System.out.println(camera.getX() + "  " + camera.getY() ); //
         * System.out.println(input.get + "  " + input.getAbsoluteMouseY() );
         */
        Main.WIDTH = gc.getWidth();
        Main.HEIGHT = gc.getHeight();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        camera.translate(g, player);
        for( int i = 0; i < map.getLayerCount(); i++ )
            if( wallLayerNo != i )
                map.render(0, 0, i);

        g.setColor(Color.white);

        for( int i = 0; i < Obiecte.portal.size(); i++ )
            Obiecte.portal.get(i).render(gc, g);

        for( int i = 0; i < Obiecte.entit.size(); i++ )
            Obiecte.entit.get(i).render(gc, g);
        for( int i = 0; i < Obiecte.items.size(); i++ )
            Obiecte.items.get(i).render(gc, g);
        for( int i = 0; i < obi.getFinish().size(); i++ )
            g.texture(Obiecte.finish.get(i), finFill);

        player.render(gc, g);
        try {
            map.render(0, 0, wallLayerNo);
        } catch (Exception e) {
        }
        renderHealthBar();
    }

    // formateaza timpul in ceva "citibil"
    private String convTime(String beg) {
        // System.out.println(time);
        time /= 1000;
        if( time / 60 >= 2 ) {
            if( time % 60 >= 20 )
                return String.format("%s %d minute si %d de secunde", beg, (int) time / 60, (int) time % 60);
            else if( time % 60 >= 2 )
                return String.format("%s %d minute si %d secunde", beg, (int) time / 60, (int) time % 60);
            else return String.format("%s %d minute si o secunda", beg, (int) time / 60);
        } else if( time / 60 == 1 ) {
            if( time % 60 >= 20 )
                return String.format("%s un minut si %d de secunde", beg, (int) time % 60);
            else if( time % 60 >= 2 )
                return String.format("%s un minut si %d secunde", beg, (int) time % 60);
            else return String.format("%s un minut si o secunda", beg);
        } else {
            if( time % 60 >= 20 )
                return String.format("%s %d de secunde", beg, (int) time % 60);
            else if( time % 60 >= 2 )
                return String.format("%s %d secunde", beg, (int) time % 60);
            else return String.format("%s o secunda", beg);
        }
    }

    // clasa separata pentru a randa bara de viata pentru ca sunt destul de
    // multe de intra aici
    private void renderHealthBar() {
        // afla cat trebuie sa randeze din bara de viata
        init = health.getWidth() - (player.LifeLS() * health.getWidth());

        // folosite pentru modificarea treptata marimii imaginii
        if( init > rap )
            rap += 11;
        if( rap > init )
            rap = init;

        Vector2f loc = new Vector2f(-camera.getX() + 25, -camera.getY() + 20);
        Vector2f pozimg = new Vector2f(health.getWidth() - camera.getX() + 25 - rap, health.getHeight() - camera.getY() + 20);
        Vector2f recini = new Vector2f(0, 0);
        Vector2f recfin = new Vector2f(health.getWidth() - rap, health.getHeight());
        // desenez o parte din imagine ( in functie de viata jucatorului )
        health.draw(loc.x, loc.y, pozimg.x, pozimg.y, recini.x, recini.y, recfin.x, recfin.y);
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
