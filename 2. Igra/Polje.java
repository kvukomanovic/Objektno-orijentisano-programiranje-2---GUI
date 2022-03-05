package v2;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas{
	
	public enum Status{SLOBODNO,IZABRANO};
	private Mreza mreza;
	private int broj;
	private Label natpis;
	Status status=Status.SLOBODNO;
	
	public Label dohvNatpis() {
		return natpis;
	}
	
	public int dohvBroj() {
		return broj;
	}
	public void postaviStatus(Status s) {
		status=s;
	}
	public Status dohvStatus() {
		return status;
	}

	public Polje(Mreza mreza,int broj) {
		setSize(75,75);
		setBackground(Color.orange);
		setVisible(true);
		this.mreza=mreza;
		this.broj=broj;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				status = (status==Status.IZABRANO ? Status.SLOBODNO : Status.IZABRANO);
				mreza.javipromenuStatusa(Polje.this);
				repaint();
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				repaint();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		if (status == Status.IZABRANO) {
			g.setColor(Color.blue);
			g.fillOval(0, 0, getWidth(), getHeight());
			crtajBroj(Color.white);
		} else  {
			crtajBroj(Color.black);
		}
	}
	private void crtajBroj(Color boja) {
		Graphics g=this.getGraphics();
		g.setColor(boja);
		int fontSize=(int)(Math.min(this.getHeight(), this.getWidth())/3);
		g.setFont(new Font("Comic Sans MS",Font.PLAIN,fontSize));
		String text="";
		text+=broj;
		FontMetrics metrics=g.getFontMetrics();
		int velicina=(int)((getWidth()<getHeight()?getWidth():getHeight())/3);
		g.setFont(new Font("Comic Sans MS",Font.BOLD,velicina));
		FontMetrics font=g.getFontMetrics();
		int x=(getWidth()-font.stringWidth(text))/2;
		int y= (getHeight()-font.getHeight()) /2 + font.getAscent();
		g.drawString(text, x, y);
	}
}
