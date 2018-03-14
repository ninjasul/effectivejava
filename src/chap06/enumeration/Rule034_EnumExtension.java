package chap06.enumeration;

import java.util.Arrays;
import java.util.Collection;

public class Rule034_EnumInterface {
	public interface Operation {
		double apply(double x, double y);
	}
	
	public enum BasicOperation implements Operation {
		PLUS("+") {
			public double apply(double x, double y) {
				return x + y;
			}
		},
		MINUS("-") {
			public double apply(double x, double y) {
				return x - y;
			}
		},
		TIMES("*") {
			public double apply(double x, double y) {
				return x * y;
			}
		},
		DIVIDE("/") {
			public double apply(double x, double y) {
				return x / y;
			}
		};
		
		// symbol 관련 코드는 BasicOperation 와 ExtendedOperation 이 중복됨.
		// Helper 클래스나 static helper method 를 이용해서 중복을 제거할 수 있음.
		private final String symbol;
		
		BasicOperation(String symbol) {
			this.symbol = symbol;
		}
		
		@Override
		public String toString() {
			return symbol;
		}	
	}
		
	public enum ExtendedOperation implements Operation {
		EXP("^") {
			public double apply(double x, double y) {
				return Math.pow(x, y);
			}
		},
		REMAINDER("%") {
			public double apply(double x, double y) {
				return x % y;
			}
		};
		
		private final String symbol;
		
		ExtendedOperation(String symbol) {
			this.symbol = symbol;
		}
		
		@Override
		public String toString() {
			return symbol;
		}	
	}
	
	public static void main(String[] args) {
		double x = Double.parseDouble("4.0");
		double y = Double.parseDouble("2.0");
		test(BasicOperation.class, x, y );
		test2(Arrays.asList(ExtendedOperation.values()), x, y );
	}
	
	// T extends Enum<T> & Operation: T는 enum 자료형이면서 Operation의 하위 자료형임.
	private static <T extends Enum<T> & Operation> void test( Class<T> opSet, double x, double y ) {
		for( Operation op : opSet.getEnumConstants() ) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x,  y));
		}
	}
	
	private static void test2( Collection<? extends Operation> opSet, double x, double y ) {
		for( Operation op : opSet ) {
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x,  y));
		}
	}
	
}
