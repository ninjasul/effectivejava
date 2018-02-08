package chap08.generalrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// 규칙 45 - 지역 변수의 유효범위를 최소화 하라.
public class Rule045_LocalVariableScope {
	
	private static List<String> elements = new ArrayList<String>(Arrays.asList((new String[] {"A", "B", "C"})));
	private static List<String> elements2 = new ArrayList<String>(Arrays.asList((new String[] {"W", "X", "Y", "Z"})));
	
	public static void traverseCollection() {
		
		// 컬렉션 순회 시 다음과 같이 for-each 문을 사용하는 것이 바람직함.
		for( String s : elements ) {
			System.out.println(s);
		}
		
		// for-each 문이나 제네릭이 지원되지 않던 시절의 코드
		for( Iterator i = elements.iterator(); i.hasNext(); ) {
			System.out.println((String)i.next());
		}

		// 첫 번째 while 순회
		Iterator<String> i = elements.iterator();
		while( i.hasNext() ) {
			System.out.println(i.next());
		}
		
		// while 문을 쓰다보면 아래 처럼 복붙을 하다 보면 변수가 틀릴 수도 있음.
		Iterator<String> i2 = elements2.iterator();
		while( i.hasNext() ) {		// 이미 i를 모두 순회 했기 때문에 절대로 true 가 될 수 없음.
			System.out.println(i2.next());
		}
		
		// for문에서는 변수가 틀리면 컴파일 단계에서 오류가 발생하므로 while 보다는 for가 나음.
		for( Iterator<String> i3 = elements2.iterator(); i3.hasNext(); ) {
			System.out.println(i3.next());
		}
	}
	
	public static void main(String[] args) {
		traverseCollection();		
	}
	
}
