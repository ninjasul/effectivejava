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
		// synchronized ���� ������ �Ұ��� �޼���(alien method)�� ȣ���ϸ� �ȵ�.
		// �׷��� Lock �� �� ���¿��� ����Ʈ�� ������ ����, ���纻�� �۾��� �����ϵ��� �����ؾ� ��.
		synchronized(list) {
			Object result = initVal;
			for( Object o : list ) {
				result = f.apply(result, o);
			}
			return result;
		}
	}
	
	static Object reduceWithCopiedArray(List list, Function f, Object initVal ) {
		
		// 1.5���� ���������� list.toArray() �޼��带 ����ϴ� ���� �ٶ����� ���
		// list.toArray �޼���� �޼��� ���ο��� ����Ʈ�� ���� �ɾ���.
		Object[] snapshot = list.toArray();
		Object result = initVal;
		
		for( Object o : snapshot ) {
			result = f.apply(result,  o);
		}
		return result;
	}
	
	static <E> E reduceWithGenricAndCopiedArray(List<E> list, FunctionGeneric<E> f, E initVal ) {
		// Type mismatch: cannot convert from Object[] to E[] Exception �߻�
		// (E[]) �� ĳ���� ���ָ� Type safety: Unchecked cast from Object[] to E[] warning�� �߻�
		// �� warning�� ���� ���߿� E�� � ������ Ÿ���� ���� �� �� ���� ������ �߻��ϴ� ����.
		E[] snapshot = (E[]) list.toArray();		
		E result = initVal;
		
		for( E e : snapshot ) {
			result = f.apply(result,  e);
		}
		return result;
	}
	
	static <E> E reduceWithGenricAndList(List<E> list, FunctionGeneric<E> f, E initVal ) {
		// �ƿ� ����Ʈ�� ����ϴ� ������� �����ϸ� �ƹ��� ������ ��� �޽����� �߻����� ����.
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
		
		// runtime �ÿ� "java.lang.ArrayStoreException: java.lang.String" �� �߻� 
		Object[] objectArray = new Long[1];
		objectArray[0] = "I don't fit in";
		
		// compile �ÿ� ������ �߻�. List�� �� ������.
		//List<Object> ol = new ArrayList<Long>();
		//ol.add("I don't fit in");
	}
}
