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
		
		// enum �ڷ����� ���� fromString �޼��� ����
		private static final Map<String , Operation2> stringToEnum = new HashMap<String , Operation2>();
		
		// ��� �̸��� ���� ����� ������Ű�� �� �ʱ�ȭ
		static { 
			for (Operation2 op : values())	{
				stringToEnum.put(op.toString(), op);
			}
		}
			
		// ���ڿ��� �־����� �׿� ���� Operation ��� ��ȯ. �߸��� ���ڿ��̸� null ��ȯ.
		public static Operation2 fromString(String symbol) {
			return stringToEnum.get(symbol);
		}

		abstract double apply( double x, double y );
	}
	
	public static void main(String[] args) {
		// Operation �� ���� enum ���� ����� ���ο� operation�� �߰��Ǿ� ȣ���ϸ� runtime ������ �߻���.
		//System.out.println(Operation.CUSTOM_OPERATION.apply( 5.4, 2.3 ));
		
		// ����� �޼��带 ������ Operation2 �� ���� ����� ���ʿ� �޼��� ���Ǹ� ���� ������ compile ������ �߻��ϹǷ� �� ���� �����.
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
