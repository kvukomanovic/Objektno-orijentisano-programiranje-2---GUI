package v2;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import v2.Polje.Status;

public class Mreza extends Panel{

	private Polje[][] polja;
	private ArrayList<Polje> izabranaPolja;
	private int m,n;
	private Igra igra;
	
	public Mreza(Igra i) {
		this(4,5,i);
	}
	public Mreza(int m, int n,Igra ig) {
		this.m=m; this.n=n;
		izabranaPolja=new ArrayList<>();
		polja=new Polje[m][n];
		igra=ig;
		setBackground(Color.black);
		setLayout(new GridLayout(m, n, 3, 3));
		int id=0;
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++) {
				Polje p=new Polje(this,id++);
				polja[i][j]=p;
				add(p);
			}
		setVisible(true);
	}
	
	public ArrayList<Polje> dohvIzabrana(){
		return izabranaPolja;
	}
	
	public HashSet<Integer> set(){
		HashSet<Integer> set=new HashSet<>();
		for(Polje p:izabranaPolja)
			set.add(p.dohvBroj());
		return set;
		
	}
	public void javipromenuStatusa(Polje p) {
		if (p.dohvStatus()==Status.IZABRANO) {
			izabranaPolja.add(p);
		}
		else {
			izabranaPolja.remove(p);
		}
		igra.javipromenu();
	}
}
