package v2;

import java.util.Random;

public class Generator {
	private Random random=new Random();
	
	public int broj(int donja, int gornja) {
		return donja+ random.nextInt(gornja-donja+1);
	}
}
