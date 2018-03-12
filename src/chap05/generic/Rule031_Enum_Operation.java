package chap05.generic;

import java.util.HashMap;
import java.util.Map;

public class Rule031_Enum_Operation {
	
  
	public enum Operation {
		PLUS,
		MINUS,
		TIMES,
		DIVIDE,
		CUSTOM_OPERATION
		;
		
		
		double apply( double x, double y ) {
			switch(this) {
				case PLUS: return x + y;
				case MINUS: return x - y;
				case TIMES: return x * y;
				case DIVIDE: return x / y;			
			}
			throw new AssertionError("Unknown op: " + this);
		}
	}
	
	public enum Operation2 {
		PLUS("+") 				{ double apply(double x, double y) { return x+y; }},
		MINUS("-")				{ double apply(double x, double y) { return x-y; }},
		TIMES("*")				{ double apply(double x, double y) { return x*y; }},
		DIVIDE("/")				{ double apply(double x, double y) { return x/y; }},
		CUSTOM_OPERATION("?")	{ double apply(double x, double y) { return x*x+y; }}
		;
		
		private final String symbol;
		Operation2(String symbol) { 
			this.symbol = symbol;
		}
		
		@Override
		public String toString() {
			return symbol;
		}
		
		// enum 자료형에 대한 fromString 메서드 구현
		private static final Map<String , Operation2> stringToEnum = new HashMap<String , Operation2>();
		
		// 상수 이름을 실제 상수로 대응시키는 맵 초기화
		static { 
			for (Operation2 op : values())	{
				stringToEnum.put(op.toString(), op);
			}
		}
			
		// 문자열이 주어지면 그에 대한 Operation 상수 반환. 잘못된 문자열이면 null 반환.
		public static Operation2 fromString(String symbol) {
			return stringToEnum.get(symbol);
		}

		abstract double apply( double x, double y );
	}
	
	public static void main(String[] args) {
		// Operation 과 같은 enum 정의 방식은 새로운 operation이 추가되어 호출하면 runtime 오류가 발생함.
		//System.out.println(Operation.CUSTOM_OPERATION.apply( 5.4, 2.3 ));
		
		// 상수별 메서드를 구현한 Operation2 와 같은 방식은 애초에 메서드 정의를 하지 않으면 compile 오류가 발생하므로 더 나은 방식임.
		System.out.println(Operation2.CUSTOM_OPERATION.apply( 5.4, 2.3 ));		
		
		double x = 2.0;
		double y = 4.0;
		for (Operation2 op : Operation2.values()) {
			System.out.printf("%f %s %f = %f%n" , x, op, y, op.apply(x, y));
		}
		
		for (Operation2 op : Operation2.values()) {
			System.out.println(Operation2.fromString(op.toString()));
		}
	}
}
