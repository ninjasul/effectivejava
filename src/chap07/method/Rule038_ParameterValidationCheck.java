package chap07.method;

import java.math.BigInteger;

public class Rule038_ParameterValidationCheck {
	/**
	 * (this mod m)�� Biglntger �� ��ȯ. �� �޼���� remainder �޼���ʹ� �ٸ���.
	 * remainder �޼���� �׻� ���� �ƴ� BigInteger ��ü�� ��ȯ����.
	 *
	 * @param mod ��t�� ������ �� , �ݵ�� ���.
	 * @return this mod m
	 * @throws ArithmeticException (m <= 0�� ��)
	 */
	public BigInteger mod(BigInteger m) {
		if (m.signum() <= 0) {
			throw new ArithmeticException("Modulus <= 0: " + m);
		}
		
		// ... ��� ����
		
		return m;
	}
	
	private static void sort(long a[], int offset, int length) {
		assert a != null;
		assert offset >= 0 && offset <= a.length;
		assert length >= 0 && length <= a.length - offset;
		
		// ... ��� ����
	}
	
	public static void main(String[] args) {
		
		sort( null, 0, 2 );
		
	}
	
}
