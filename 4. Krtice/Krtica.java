package v4;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

	private int x,y,w,h;
	
	public Krtica(Rupa rupa) {
		super(rupa);
	}
	@Override
	protected void udarenaEfekat() {
		rupa.zaustavi();
	}
	@Override
	protected void pobeglaEfekat() {
			rupa.basta.smanjiPovrce();
	}
	@Override
	protected void crtajZapravo(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x,y,w,h);
	}
	protected /*synchronized*/ void postaviDimenzije(int x, int y,int w, int h) {
		this.x=x; this.y=y;
		 this.w=w; this.h=h;
	}
	
}

