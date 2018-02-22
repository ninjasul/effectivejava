package chap05.generic;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/****************************************************************************************
1. List<String> 이렇게 자료형을 명시한 제네릭을 사용해라. 그냥 List 이렇게 쓰지 마라.

2. List<Object> 와 List 의 차이 -> unsafeAdd(), safeAdd() 메서드 참조
	- List<Object>: 아무 객체나 리스트에 들어갈 수 있다는 것을 컴파일러에게 명시적으로 알림. 바람직한 방법임.
	- List: 아예 형 검사 자체를 생략. 하면 안되는 방법.

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
		
		// 아래 
		System.out.println(numElementsInCommonWithRawType( set1, set2 ));
		System.out.println(numElementsInCommonWithParameterizedType( set1, set2 ));
	}
	
	// 아래와 같이 raw type List를 사용하면 경고는 뜨지만 컴파일이 되어 버리고 런타임에서 오류가 발생함. 위험.
	// Type safety: The method add(Object) belongs to the raw type List. References to generic type List<E> should be parameterized
	private static void unsafeAdd( List list, Object o ) {
		list.add(o);
	}
	
	// List<Object> 와 같이 parameterized type을 사용하면 아예 컴파일 단계에서 오류가 발생하므로 안전함.
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
