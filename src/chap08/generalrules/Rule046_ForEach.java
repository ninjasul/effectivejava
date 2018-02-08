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
				// ranks ������ŭ i.next() �ڵ尡 �ҷ��� NoSuchElementException�� �߻���.
				deck.add( new Card( i.next(), j.next() ) );
		
		for( Card card : deck ) 
			System.out.println(card);
	}
	
	public static void doFaceWithIterator() {
		
		Collection<Face> faces = Arrays.asList(Face.values());
		
		for( Iterator<Face> i = faces.iterator(); i.hasNext(); )
			for( Iterator<Face> j = faces.iterator(); j.hasNext(); )
				// ���� �ǵ��� (ONE, ONE) ���� (SIX, SIX) ���� 36���� ���� ����� ���� ���� ������ �ǵ��̳�
				// i.next() �� �Ź� ȣ�� �ǹǷ� 6���� �ָ� ����� ���� �����.
				System.out.println( i.next() + " " + j.next() );
	}
	
	public static void doCardWithForEach() {
		
		List<Card> deck = new ArrayList<Card>();
		
		// Iterator ��� for-each �� ����ϸ� �ڵ尡 �ξ� ����� ��.
		// ����� for-each ���� Iterable �������̽��� �����ϴ� ��� ��ü���� �̿밡�� ��.
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
	 * - ����� for-each ���� Iterable �������̽��� �����ϴ� ��� ��ü���� �̿밡�� ��.
	 * - for-each ���� ����� �� ���� ���̽� -> for���� ���
	 * 		1. ���͸�		- �÷��� ��ȸ �� Ư�� ���� ������ �ؾ� �ϴ� ���. Iterator�� remove() �޼��带 ����ؾ� ��.
	 * 		2. ��ȯ			- Ư�� ���Ҹ� ���� �ؾ� �ϴ� ���. 
	 * 		3. ���� ��ȸ	- ���� ���� �÷����� ���������� ��ȸ�ϴ� ���.
	 **********************************************************************************************************************************/
}
