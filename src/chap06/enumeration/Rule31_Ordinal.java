package chap06.enumeration;

public class Rule31_Ordinal {
	public enum Ensemble {
		SOLO, DUET, TRIO, QUARTET, QUINTET, 
		SEXTET, SEPTET, OCTET, NONET, DECTET;
				
		public int numberOfMusicians() { return ordinal() + 1; }
	}
	
	public enum Ensemble2 {
		SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5), 
		SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
		NONET(9), DECTET(10), TRIPLE_QUARTET(12);
				
		private final int numberOfMusicians;
		
		Ensemble2(int size) {
			this.numberOfMusicians = size;
		}
		
		public int numberOfMusicians() { return numberOfMusicians; }
	}
	
	public static void main(String[] args) {
		for( Ensemble curEnsemble : Ensemble.values() ) {
			System.out.println(curEnsemble.numberOfMusicians());
		}
		
		for( Ensemble2 curEnsemble : Ensemble2.values() ) {
			System.out.println(curEnsemble.numberOfMusicians());
		}
	}
}
