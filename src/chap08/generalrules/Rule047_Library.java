package chap08.generalrules;

import java.util.Random;

// 어떤 라이브러리가 있는지 파악하고 적절히 활용하라
public class Rule047_Library {

	private static final Random rnd = new Random();
	
	// 낮은 수의 난수를 생성해 낼 가능성이 높음.
	private static int random( int n ) {		
		return Math.abs(rnd.nextInt()) % n;
	}
	
	// 1.2 버전 이후에 릴리즈 된 개선된 랜덤함수
	private static int random2( int n ) {
		return Math.abs( rnd.nextInt(n) );
	}
	
	public static void main(String[] args) {
		int n = 2 * (Integer.MAX_VALUE / 3 );
		
		int low = 0;
		int low2 = 0;
		// 100만번 루프 수행
		for( int i = 0; i < 1000000; i++ ) {
			// 난수 생성 후 절반을 기준으로 작은 쪽/큰 쪽 중 어느 쪽에 쏠리는 지 체크
			if( random(n) < n/2 ) {
				low++;
			}
			
			if( random2(n) < n/2 ) {
				low2++;
			}
		}
		
		System.out.println(low);
		System.out.println(low2);		
	}
}
