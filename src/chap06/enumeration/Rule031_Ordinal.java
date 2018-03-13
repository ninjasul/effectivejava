package chap06.enumeration;

public class Rule031_Ordinal {
	// 이 enum에서 처럼 ordinal() 메서드를 사용하면 유연한 코드를 만들 수 없음.
	public enum Ensemble {
		SOLO, DUET, TRIO, QUARTET, QUINTET, 
		SEXTET, SEPTET, OCTET, NONET, DECTET;
				
		public int numberOfMusicians() { return ordinal() + 1; }
	}
	
	// ordinal() 메서드 대신 인스턴스 필드를 사용해야 함.
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
