package chap07.method;

import java.math.BigInteger;

public class Rule038_ParameterValidationCheck {
	/**
	 * (this mod m)인 Biglntger 값 반환. 이 메서드는 remainder 메서드와는 다르다.
	 * remainder 메서드는 항상 음수 아닌 BigInteger 객체를 반환힌다.
	 *
	 * @param mod 연t별 수행할 값 , 반드시 양수.
	 * @return this mod m
	 * @throws ArithmeticException (m <= 0월 때)
	 */
	public BigInteger mod(BigInteger m) {
		if (m.signum() <= 0) {
			throw new ArithmeticException("Modulus <= 0: " + m);
		}
		
		// ... 계산 수행
		
		return m;
	}
	
	private static void sort(long a[], int offset, int length) {
		assert a != null;
		assert offset >= 0 && offset <= a.length;
		assert length >= 0 && length <= a.length - offset;
		
		// ... 계산 수행
	}
	
	public static void main(String[] args) {
		
		sort( null, 0, 2 );
		
	}
	
}
