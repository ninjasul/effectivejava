package chap05.generic;

import java.util.ArrayList;
import java.util.List;

public class Rule025_List {
	
	interface Function {
		Object apply(Object arg1, Object arg2);
	}
	
	interface FunctionGeneric<T> {
		T apply(T arg1, T arg2);
	}
	
	static Object reduce(List list, Function f, Object initVal ) {
		// synchronized 영역 내에서 불가해 메서드(alien method)를 호출하면 안됨.
		// 그러니 Lock 을 건 상태에서 리스트를 복사한 다음, 복사본에 작업을 수행하도록 수정해야 함.
		synchronized(list) {
			Object result = initVal;
			for( Object o : list ) {
				result = f.apply(result, o);
			}
			return result;
		}
	}
	
	static Object reduceWithCopiedArray(List list, Function f, Object initVal ) {
		
		// 1.5이하 버전에서는 list.toArray() 메서드를 사용하는 것이 바람직한 방법
		// list.toArray 메서드는 메서드 내부에서 리스트에 락을 걸어줌.
		Object[] snapshot = list.toArray();
		Object result = initVal;
		
		for( Object o : snapshot ) {
			result = f.apply(result,  o);
		}
		return result;
	}
	
	static <E> E reduceWithGenricAndCopiedArray(List<E> list, FunctionGeneric<E> f, E initVal ) {
		// Type mismatch: cannot convert from Object[] to E[] Exception 발생
		// (E[]) 로 캐스팅 해주면 Type safety: Unchecked cast from Object[] to E[] warning이 발생
		// 이 warning은 실행 도중에 E가 어떤 데이터 타입이 될지 알 수 없기 때문에 발생하는 것임.
		E[] snapshot = (E[]) list.toArray();		
		E result = initVal;
		
		for( E e : snapshot ) {
			result = f.apply(result,  e);
		}
		return result;
	}
	
	static <E> E reduceWithGenricAndList(List<E> list, FunctionGeneric<E> f, E initVal ) {
		// 아예 리스트를 사용하는 방식으로 변경하면 아무런 오류가 경고 메시지가 발생하지 않음.
		List<E> snapshot;
		synchronized(list) {
			snapshot = new ArrayList<E>(list);
		}
		E result = initVal;
		
		for( E e : snapshot ) {
			result = f.apply(result,  e);
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		// runtime 시에 "java.lang.ArrayStoreException: java.lang.String" 이 발생 
		Object[] objectArray = new Long[1];
		objectArray[0] = "I don't fit in";
		
		// compile 시에 오류가 발생. List가 더 안전함.
		//List<Object> ol = new ArrayList<Long>();
		//ol.add("I don't fit in");
	}
}
