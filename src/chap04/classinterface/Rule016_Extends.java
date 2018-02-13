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
	
	// Set 을 implements 한 ForwardingSet 정의
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
		
		// 3개를 add 했으므로 addCount는 3이 될 것으로 기대
		// 하지만 HashSet.addAll() 메서드 내부에서 HashSet.add() 메서드를 호출함.
		// HashSet.add() 메서드는 오버라이드 된 InstrumentedHashSet.add() 메서드를 호출하므로 엘리먼트 개수만큼 addCount를 더해 줌.
		// 따라서, 실제로는 6이 출력됨.
		// 이는 상위 클래스의 구현 방식에 따라 하위 클래스가 영향을 받는 전형적인 사례라고 할 수 있음.
		System.out.println(s.getAddCount());
		
		Rule016_Extends.InstrumentedSet<String> s2 = new Rule016_Extends.InstrumentedSet<String>(new HashSet<String>());
		s2.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
		System.out.println(s2.getAddCount());
	}
}
