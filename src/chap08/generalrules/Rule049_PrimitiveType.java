package chap08.generalrules;

import java.util.Comparator;

// Rule49. 객체화된 기본 자료형 대신 기본 자료형을 이용하라
public class Rule049_PrimitiveType {
	
	private static Integer i;	

	private static void doObjectComparision() {
		Comparator<Integer> naturalOrder = new Comparator<Integer>() {
			public int compare(Integer first, Integer second) {
				
				// first < second 표현식에서 Integer 객체 first, second가 int 형으로 자동 형변환 되어 값 비교가 일어남.
				// 그러나 first == second 는 값 비교가 아닌 포인터 비교이므로 서로 다른 객체인 경우 0이 아닌 1이 리턴됨.
				// 객체화 된 primitive type 에 == 연산자를 사용하는 것은 거의 항상 오류라고 봐야 함.
				return first < second ? -1 : (first == second ? 0 : 1 );
			}
		};
		
		
		// 같은 값을 비교하는 것이니 기대되는 결과 값은 0임.
		System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));
		
	}
	
	private static void doPrimitiveComparision() {
		Comparator<Integer> naturalOrder = new Comparator<Integer>() {
			public int compare(Integer first, Integer second) {
				// 주어진 객체 형 타입을 int 형으로 자동 형 변환 후 비교 작업 수행
				int f = first; 		// 자동 형 변환
				int s = second; 	// 자동 형 변환
				return f < s ? -1 : (f == s ? 0 : 1 );
			}
		};		
		
		System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));
		
	}
	
	private static void doNullPointerComparision() {
		
		// Integer 타입의 기본 값은 0이 아닌 null이므로 비교 작업 시 NullPointerException이 발생함.
		if( i == 42 ) {
			System.out.println("Unbelievable");
		}
	}
	
	private static void doSlowSummation() {
		//Long sum = 0L;
		long sum = 0L;
		
		for( long i = 0; i < Integer.MAX_VALUE; i++ ) {
			// 객체 형 변수에 값을 대입하는 작업을 반복함으로써 객체화와 비객체화가 반복됨.
			// 엄청난 속도 저하가 일어남.
			// Long 을 long으로 변경하여 해결
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
	 * - 객체화 된 기본 자료형을 사용해야하는 경우
	 * 	1. 컬렉션의 요소 및 키 값으로 사용하는 경우
	 * 	2. 제네릭이나 리플렉션을 사용해야 하는 경우 
	 ***********************************************************************************/
}
