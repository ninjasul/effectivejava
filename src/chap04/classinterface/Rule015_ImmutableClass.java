package chap04.classinterface;

/***************************************************************************************************************************
 Rule 15 - 변경 가능성을 최소화하라

 1. immutable 클래스는 그 객체를 수정할 수 없는 클래스임.
 -> String, 기본 자료형 클래스, BigInteger, BigDecimal 등의 클래스가 immutable 클래스임.

 2. immutable 클래스 생성 시 규칙 5가지
 2.1. 객체 상태를 변경하는 메서드(mutator 메서드) 를 제공하지 않음.
 2.2. 상속 불가: 하위 클래스가 immutability 를 깰 위험이 있음. -> final 키워드로 구현 가능.
 2.3. 모든 필드를 final 로 선언
 2.4. 모든 필드를 private으로 선언 -> 클라이언트가 immutable 클래스의 필드가 참조하는 변경 가능 객체를 직접 수정하지 못하게 막음.
 2.5. 변경 가능 컴포넌트에 대한 독점적 접근권을 보장함.

 2. 만약 public 클래스의 필드들을 public으로 공개하면 내부 표현을 변경하기 어려움.
 해당 public 클래스를 사용하는 클라이언트 코드도 변경해야 하기 때문임.

 3. package-private 클래스나 private nested 클래스는 데이터 필드를 공개하는 것이 바람직한 경우가 있음.
 -> 이런 클래스들은 접근자를 제공하지 않는 것이 코드가 깔끔한 경우가 있음.
 -> 해당 클래스들의 클라이언트 코드들은 같은 패키지 내에 존재하는 셈이므로 영향도가 크지 않음.

 4. java.awk.Point, java.awk.Dimension 클래스는 public 클래스임에도 불구하고 필드들을 public으로 공개하고 있는데
 이는 참고하지 않는 것이 좋음.

 5. public 클래스의 내부 필드 중 immutable 필드를 public 으로 선언하는 것은 심각성이 덜하므로 고려해 볼만함.
 ***************************************************************************************************************************/
public class Rule015_ImmutableClass {
	public final class Complex {
		private final double re;
		private final double im;

		public Complex(double re , double im) {
			this.re = re;
			this.im = im;
		}

		// 대응되는 수쟁자가 없는 접근자들
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

			// == 대신 compare룰 쓰는 이유에 대해서는 57쪽 참조
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
