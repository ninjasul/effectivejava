package chap08.generalrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Rule046_ForEach {
	
	public enum Suit { CLUB, DIAMOND, HEART, SPADE }
	public enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }	
	public enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }
	
	private static Collection<Suit> suits = Arrays.asList(Suit.values());
	private static Collection<Rank> ranks = Arrays.asList(Rank.values());
	
	private static class Card {
		private Suit suit;
		private Rank rank;
		
		public Card(Suit suit, Rank rank) {
			this.suit = suit;
			this.rank = rank;
		}

		@Override
		public String toString() {
			return "Card [suit=" + suit + ", rank=" + rank + "]";
		}
	}
	
	public static void doCardWithIterator() {
		
		List<Card> deck = new ArrayList<Card>();
		
		for( Iterator<Suit> i = suits.iterator(); i.hasNext(); )
			for( Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
				// ranks 개수만큼 i.next() 코드가 불려서 NoSuchElementException이 발생함.
				deck.add( new Card( i.next(), j.next() ) );
		
		for( Card card : deck ) 
			System.out.println(card);
	}
	
	public static void doFaceWithIterator() {
		
		Collection<Face> faces = Arrays.asList(Face.values());
		
		for( Iterator<Face> i = faces.iterator(); i.hasNext(); )
			for( Iterator<Face> j = faces.iterator(); j.hasNext(); )
				// 원래 의도는 (ONE, ONE) 부터 (SIX, SIX) 까지 36개의 쌍을 만들어 내는 것이 원래의 의도이나
				// i.next() 가 매번 호출 되므로 6개의 쌍만 만들어 내고 종료됨.
				System.out.println( i.next() + " " + j.next() );
	}
	
	public static void doCardWithForEach() {
		
		List<Card> deck = new ArrayList<Card>();
		
		// Iterator 대신 for-each 를 사용하면 코드가 훨씬 깔끔해 짐.
		// 참고로 for-each 문은 Iterable 인터페이스를 구현하는 모든 객체에서 이용가능 함.
		for( Suit suit : suits )
			for( Rank rank : ranks )
				deck.add( new Card( suit, rank ) );
		
		for( Card card : deck ) 
			System.out.println(card);
	}
	
	public static void main(String[] args) {
		doFaceWithIterator();
		//doCardWithIterator();
		doCardWithForEach();
	}
	
	/*********************************************************************************************************************************
	 * - 참고로 for-each 문은 Iterable 인터페이스를 구현하는 모든 객체에서 이용가능 함.
	 * - for-each 문을 사용할 수 없는 케이스 -> for문을 사용
	 * 		1. 필터링		- 컬렉션 순회 중 특정 원소 삭제를 해야 하는 경우. Iterator의 remove() 메서드를 사용해야 함.
	 * 		2. 변환			- 특정 원소를 변경 해야 하는 경우. 
	 * 		3. 병렬 순회	- 여러 개의 컬렉션을 병렬적으로 순회하는 경우.
	 **********************************************************************************************************************************/
}
