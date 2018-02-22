package chap05.generic;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/****************************************************************************************
1. List<String> �̷��� �ڷ����� ����� ���׸��� ����ض�. �׳� List �̷��� ���� ����.

2. List<Object> �� List �� ���� -> unsafeAdd(), safeAdd() �޼��� ����
	- List<Object>: �ƹ� ��ü�� ����Ʈ�� �� �� �ִٴ� ���� �����Ϸ����� ��������� �˸�. �ٶ����� �����.
	- List: �ƿ� �� �˻� ��ü�� ����. �ϸ� �ȵǴ� ���.

3. 
 ****************************************************************************************/
public class Rule023_RawType {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		//unsafeAdd( strings, new Integer(42));
		//safeAdd( strings, new Integer(42));
		//String s = strings.get(0);
		
		Set<String> set1 = new HashSet<String>(Arrays.asList(new String[]{ "a", "b", "c", "d", "e" }));
		Set<String> set2 = new HashSet<String>(Arrays.asList(new String[]{ "b", "d", "f", "g", "h" }));
		
		// �Ʒ� 
		System.out.println(numElementsInCommonWithRawType( set1, set2 ));
		System.out.println(numElementsInCommonWithParameterizedType( set1, set2 ));
	}
	
	// �Ʒ��� ���� raw type List�� ����ϸ� ���� ������ �������� �Ǿ� ������ ��Ÿ�ӿ��� ������ �߻���. ����.
	// Type safety: The method add(Object) belongs to the raw type List. References to generic type List<E> should be parameterized
	private static void unsafeAdd( List list, Object o ) {
		list.add(o);
	}
	
	// List<Object> �� ���� parameterized type�� ����ϸ� �ƿ� ������ �ܰ迡�� ������ �߻��ϹǷ� ������.
	// The method safeAdd(List<Object>, Object) in the type Rule023_RawType is not applicable for the arguments (List<String>, Integer)
	private static void safeAdd( List<Object> list, Object o ) {
		list.add(o);
	}

	private static int numElementsInCommonWithRawType( Set s1, Set s2 ) {
		int result = 0;
		
		for( Object o1 : s1 ) {
			if( s2.contains(o1)) {
				result++;
			}			
		}
		
		return result++;
		
	}
	
	private static int numElementsInCommonWithParameterizedType( Set<?> s1, Set<?> s2 ) {
		int result = 0;
		
		for( Object o1 : s1 ) {
			if( s2.contains(o1)) {
				result++;
			}			
		}
		
		return result++;
		
	}
}
