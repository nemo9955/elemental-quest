package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

// clasa creata pentru manipularea imaginilor
public class ManipImg {
	
	private Rectangle zon = null ;
	private Image img = null ;
	private Vector2f poz = null ;
	private float raport = 1 ;
	
	// construim un buton in functie de imaginea acestuia si pozitia 
	public ManipImg(Image imag , float pozX , float pozY) {
		img = imag;
		zon = new Rectangle (pozX , pozY , imag.getWidth() , imag.getHeight());
		poz = new Vector2f(pozX , pozY);
	}
	
	public void render(){
		img.draw(poz.x , poz.y , raport ) ;
	}
	
	// verifica daca butonul a fost apasat 
	public boolean inZon(float x , float y){
		if(zon.contains(x, y)){
			raport=1.1f;
			return true;
		}else{
			raport=1f;
			return false;
		}
	}
}
