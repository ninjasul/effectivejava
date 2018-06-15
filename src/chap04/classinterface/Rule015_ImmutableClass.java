package chap04.classinterface;

/***************************************************************************************************************************
 Rule 15 - ���� ���ɼ��� �ּ�ȭ�϶�

 1. immutable Ŭ������ �� ��ü�� ������ �� ���� Ŭ������.
 -> String, �⺻ �ڷ��� Ŭ����, BigInteger, BigDecimal ���� Ŭ������ immutable Ŭ������.

 2. immutable Ŭ���� ���� �� ��Ģ 5����
 2.1. ��ü ���¸� �����ϴ� �޼���(mutator �޼���) �� �������� ����.
 2.2. ��� �Ұ�: ���� Ŭ������ immutability �� �� ������ ����. -> final Ű����� ���� ����.
 2.3. ��� �ʵ带 final �� ����
 2.4. ��� �ʵ带 private���� ���� -> Ŭ���̾�Ʈ�� immutable Ŭ������ �ʵ尡 �����ϴ� ���� ���� ��ü�� ���� �������� ���ϰ� ����.
 2.5. ���� ���� ������Ʈ�� ���� ������ ���ٱ��� ������.

 2. ���� public Ŭ������ �ʵ���� public���� �����ϸ� ���� ǥ���� �����ϱ� �����.
 �ش� public Ŭ������ ����ϴ� Ŭ���̾�Ʈ �ڵ嵵 �����ؾ� �ϱ� ������.

 3. package-private Ŭ������ private nested Ŭ������ ������ �ʵ带 �����ϴ� ���� �ٶ����� ��찡 ����.
 -> �̷� Ŭ�������� �����ڸ� �������� �ʴ� ���� �ڵ尡 ����� ��찡 ����.
 -> �ش� Ŭ�������� Ŭ���̾�Ʈ �ڵ���� ���� ��Ű�� ���� �����ϴ� ���̹Ƿ� ���⵵�� ũ�� ����.

 4. java.awk.Point, java.awk.Dimension Ŭ������ public Ŭ�����ӿ��� �ұ��ϰ� �ʵ���� public���� �����ϰ� �ִµ�
 �̴� �������� �ʴ� ���� ����.

 5. public Ŭ������ ���� �ʵ� �� immutable �ʵ带 public ���� �����ϴ� ���� �ɰ����� ���ϹǷ� ����� ������.
 ***************************************************************************************************************************/
public class Rule015_ImmutableClass {
	public final class Complex {
		private final double re;
		private final double im;

		public Complex(double re , double im) {
			this.re = re;
			this.im = im;
		}

		// �����Ǵ� �����ڰ� ���� �����ڵ�
		public double realPart() { return re; }
		public double imaginaryPart() { return im; }

		public Complex add(Complex c) {
			return new Complex(re + c.re , im + c.im);
		}

		public Complex subtract(Complex c) {
			return new Complex(re - c.re , im - c.im);
		}

		public Complex multiply(Complex c) {
			return new Complex(re * c.re - im * c .im,
					re * c.im + im * c . re);
		}

		public Complex divide(Complex c) {
			double tmp = c.re * c.re + c.im * c.im;
			return new Complex((re * c . re + im * c.im) / tmp ,
					(im * c.re - re * c.im) / tmp);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;

			if (!(o instanceof Complex))
				return false;

			Complex c = (Complex) o;

			// == ��� compare�� ���� ������ ���ؼ��� 57�� ����
			return Double.compare(re , c.re) == 0 &&
					Double.compare(im , c.im) == 0;
		}

		@Override
		public int hashCode() {
			int result = 17 + hashDouble(re);
			result = 31 * result + hashDouble(im);
			return result;
		}

		private int hashDouble(double val) {
			long longBits = Double.doubleToLongBits(val);
			return (int) (longBits ^ (longBits >>> 32));
		}

		@Override
		public String toString() {
			return "(" + re + "  + " + im + "i)";
		}
	}
}
