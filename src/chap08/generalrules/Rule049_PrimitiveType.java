package chap08.generalrules;

import java.util.Comparator;

// Rule49. ��üȭ�� �⺻ �ڷ��� ��� �⺻ �ڷ����� �̿��϶�
public class Rule049_PrimitiveType {
	
	private static Integer i;	

	private static void doObjectComparision() {
		Comparator<Integer> naturalOrder = new Comparator<Integer>() {
			public int compare(Integer first, Integer second) {
				
				// first < second ǥ���Ŀ��� Integer ��ü first, second�� int ������ �ڵ� ����ȯ �Ǿ� �� �񱳰� �Ͼ.
				// �׷��� first == second �� �� �񱳰� �ƴ� ������ ���̹Ƿ� ���� �ٸ� ��ü�� ��� 0�� �ƴ� 1�� ���ϵ�.
				// ��üȭ �� primitive type �� == �����ڸ� ����ϴ� ���� ���� �׻� ������� ���� ��.
				return first < second ? -1 : (first == second ? 0 : 1 );
			}
		};
		
		
		// ���� ���� ���ϴ� ���̴� ���Ǵ� ��� ���� 0��.
		System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));
		
	}
	
	private static void doPrimitiveComparision() {
		Comparator<Integer> naturalOrder = new Comparator<Integer>() {
			public int compare(Integer first, Integer second) {
				// �־��� ��ü �� Ÿ���� int ������ �ڵ� �� ��ȯ �� �� �۾� ����
				int f = first; 		// �ڵ� �� ��ȯ
				int s = second; 	// �ڵ� �� ��ȯ
				return f < s ? -1 : (f == s ? 0 : 1 );
			}
		};		
		
		System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));
		
	}
	
	private static void doNullPointerComparision() {
		
		// Integer Ÿ���� �⺻ ���� 0�� �ƴ� null�̹Ƿ� �� �۾� �� NullPointerException�� �߻���.
		if( i == 42 ) {
			System.out.println("Unbelievable");
		}
	}
	
	private static void doSlowSummation() {
		//Long sum = 0L;
		long sum = 0L;
		
		for( long i = 0; i < Integer.MAX_VALUE; i++ ) {
			// ��ü �� ������ ���� �����ϴ� �۾��� �ݺ������ν� ��üȭ�� ��üȭ�� �ݺ���.
			// ��û�� �ӵ� ���ϰ� �Ͼ.
			// Long �� long���� �����Ͽ� �ذ�
			sum += i;
		}
		
		System.out.println(sum);
	}
	
	public static void main(String[] args) {
		doObjectComparision();
		doPrimitiveComparision();
		//doNullPointerComparision();
		doSlowSummation();
	}
		
	/***********************************************************************************
	 * - ��üȭ �� �⺻ �ڷ����� ����ؾ��ϴ� ���
	 * 	1. �÷����� ��� �� Ű ������ ����ϴ� ���
	 * 	2. ���׸��̳� ���÷����� ����ؾ� �ϴ� ��� 
	 ***********************************************************************************/
}
