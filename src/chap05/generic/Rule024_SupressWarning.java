package chap05.generic;

import java.util.Arrays;

public class Rule024_SupressWarning {
	
	public static <T> T[] toArray(T[] a, T[] b, int size ) {
		if( a.length < size ) {
			// 리턴 문에 Type safety: Unchecked cast from Object[] to T[] warning이 발생함
			return (T[]) Arrays.copyOf( b,  size, a.getClass() );			
		}
		System.arraycopy(b,  0,  a,  0,  size);
		
		if( a.length > size ) {
			a[size] = null;			
		}
		
		return a;
	}
	
	
	public static <T> T[] toArrayWithSuppressWarning(T[] a, T[] b, int size ) {
		if( a.length < size ) {
			// T[] 지역변수에 @SuppressWarnings 를 사용하여 범위를 최소화
			@SuppressWarnings("unchecked")
			T[] result = (T[])Arrays.copyOf( b,  size, a.getClass() );
			return result;			
		}
		System.arraycopy(b,  0,  a,  0,  size);
		
		if( a.length > size ) {
			a[size] = null;			
		}
		
		return a;
	}
}
