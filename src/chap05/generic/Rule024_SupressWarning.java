package chap05.generic;

import java.util.Arrays;

public class Rule024_SupressWarning {
	
	public static <T> T[] toArray(T[] a, T[] b, int size ) {
		if( a.length < size ) {
			// ���� ���� Type safety: Unchecked cast from Object[] to T[] warning�� �߻���
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
			// T[] ���������� @SuppressWarnings �� ����Ͽ� ������ �ּ�ȭ
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
