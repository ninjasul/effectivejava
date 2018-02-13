package chap04.classinterface;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Rule016_Extends {
	
	public static class InstrumentedHashSet<E> extends HashSet<E> {
		private int addCount = 0;
		
		public InstrumentedHashSet() {			
		}
		
		public InstrumentedHashSet( int initCap, float loadFactor ) {
			super( initCap, loadFactor );
		}
		
		@Override
		public boolean add( E e ) {
			addCount++;
			return super.add(e);
		}
		
		@Override
		public boolean addAll(Collection<? extends E> c ) {
			addCount += c.size();
			return super.addAll(c);
		}
		
		public int getAddCount() {
			return addCount;
		}		
	}
	
	// Set �� implements �� ForwardingSet ����
	public static class ForwardingSet<E> implements Set<E> {
		private final Set<E> s;
		
		public ForwardingSet( Set<E> s ) { this.s = s; }
		
		public void clear()										{ s.clear(); }
		public boolean contains(Object o) 						{ return s.contains(o); }
		public boolean isEmpty() 								{ return s.isEmpty(); }
		public int size() 										{ return s.size(); }
		public Iterator<E> iterator() 							{ return s.iterator(); }
		public boolean add(E e) 								{ return s.add(e); }
		public boolean remove(Object o) 						{ return s.remove(o); }
		public boolean containsAll(Collection<?> c)				{ return s . containsAll(c); }
		public boolean addAll(Collection<? extends E> c)		{ return s.addAll(c); }
		public boolean removeAll(Collection<?> c)				{ return s.removeAll(c); }
		public boolean retainAll(Collection<?> c)				{ return s.retainAll(c); }
		public Object[] toArray() 								{ return s.toArray(); }
		public <T> T[] toArray(T[] a) 							{ return s.toArray(a); }
		@Override public boolean equals(Object o)				{ return s.equals(o); }
		@Override public int hashCode() 						{ return s.hashCode(); }
		@Override public String toString() 						{ return s.toString(); }
	}
	
	public static class InstrumentedSet<E> extends ForwardingSet<E> {
		private int addCount = 0;
		
		public InstrumentedSet(Set<E> s) {
			super(s);
		}
		
		@Override
		public boolean add(E e) {
			addCount++;
			return super.add(e);
		}
		
		@Override
		public boolean addAll(Collection<? extends E> c ) {
			addCount += c.size();
			return super.addAll(c);
		}
	
		public int getAddCount() {
			return addCount;
		}		
	}
	
	public static void main(String[] args) {
		Rule016_Extends.InstrumentedHashSet<String> s = new Rule016_Extends.InstrumentedHashSet<String>();
		s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
		
		// 3���� add �����Ƿ� addCount�� 3�� �� ������ ���
		// ������ HashSet.addAll() �޼��� ���ο��� HashSet.add() �޼��带 ȣ����.
		// HashSet.add() �޼���� �������̵� �� InstrumentedHashSet.add() �޼��带 ȣ���ϹǷ� ������Ʈ ������ŭ addCount�� ���� ��.
		// ����, �����δ� 6�� ��µ�.
		// �̴� ���� Ŭ������ ���� ��Ŀ� ���� ���� Ŭ������ ������ �޴� �������� ��ʶ�� �� �� ����.
		System.out.println(s.getAddCount());
		
		Rule016_Extends.InstrumentedSet<String> s2 = new Rule016_Extends.InstrumentedSet<String>(new HashSet<String>());
		s2.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
		System.out.println(s2.getAddCount());
	}
}
