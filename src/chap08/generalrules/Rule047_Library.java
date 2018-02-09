package chap08.generalrules;

import java.util.Random;

// � ���̺귯���� �ִ��� �ľ��ϰ� ������ Ȱ���϶�
public class Rule047_Library {

	private static final Random rnd = new Random();
	
	// ���� ���� ������ ������ �� ���ɼ��� ����.
	private static int random( int n ) {		
		return Math.abs(rnd.nextInt()) % n;
	}
	
	// 1.2 ���� ���Ŀ� ������ �� ������ �����Լ�
	private static int random2( int n ) {
		return Math.abs( rnd.nextInt(n) );
	}
	
	public static void main(String[] args) {
		int n = 2 * (Integer.MAX_VALUE / 3 );
		
		int low = 0;
		int low2 = 0;
		// 100���� ���� ����
		for( int i = 0; i < 1000000; i++ ) {
			// ���� ���� �� ������ �������� ���� ��/ū �� �� ��� �ʿ� �򸮴� �� üũ
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
