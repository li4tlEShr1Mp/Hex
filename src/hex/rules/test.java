package hex.rules;

import hex.resources.Pos;

public class test {
	public static void main(String[] args) {
		Pos pos=new Pos(5,5);
		pos=AIPawn.optimizedAlgorithm(pos);
	}	
}
