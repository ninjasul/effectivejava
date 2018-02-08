package chap08.generalrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// ��Ģ 45 - ���� ������ ��ȿ������ �ּ�ȭ �϶�.
public class Rule045_LocalVariableScope {
	
	private static List<String> elements = new ArrayList<String>(Arrays.asList((new String[] {"A", "B", "C"})));
	private static List<String> elements2 = new ArrayList<String>(Arrays.asList((new String[] {"W", "X", "Y", "Z"})));
	
	public static void traverseCollection() {
		
		// �÷��� ��ȸ �� ������ ���� for-each ���� ����ϴ� ���� �ٶ�����.
		for( String s : elements ) {
			System.out.println(s);
		}
		
		// for-each ���̳� ���׸��� �������� �ʴ� ������ �ڵ�
		for( Iterator i = elements.iterator(); i.hasNext(); ) {
			System.out.println((String)i.next());
		}

		// ù ��° while ��ȸ
		Iterator<String> i = elements.iterator();
		while( i.hasNext() ) {
			System.out.println(i.next());
		}
		
		// while ���� ���ٺ��� �Ʒ� ó�� ������ �ϴ� ���� ������ Ʋ�� ���� ����.
		Iterator<String> i2 = elements2.iterator();
		while( i.hasNext() ) {		// �̹� i�� ��� ��ȸ �߱� ������ ����� true �� �� �� ����.
			System.out.println(i2.next());
		}
		
		// for�������� ������ Ʋ���� ������ �ܰ迡�� ������ �߻��ϹǷ� while ���ٴ� for�� ����.
		for( Iterator<String> i3 = elements2.iterator(); i3.hasNext(); ) {
			System.out.println(i3.next());
		}
	}
	
	public static void main(String[] args) {
		traverseCollection();		
	}
	
}
