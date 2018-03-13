package chap06.enumeration;

import java.util.EnumSet;
import java.util.Set;

import chap06.enumeration.Rule032_EnumSet.Text2.Style;

public class Rule032_EnumSet {
	public static class Text {
		public static final int STYLE_BOLD 				= 1 << 0;
		public static final int STYLE_ITALIC			= 1 << 1;
		public static final int STYLE_UNDERLINE			= 1 << 2;
		public static final int STYLE_STRIKETHROUGH		= 1 << 3;
		
		public static void applyStyles(int styles) {
			System.out.println(styles);	
		}
	}
	
	public static class Text2 {
		
		public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }
		
		// EnumSet<Set> 타입으로 인자를 받아도 되지만 가급적이면 인터페이스 자료형을 사용하는 것이 나음.
		public static void applyStyles(Set<Style> styles) {
			System.out.println(styles);
		}
	}
	
	public static void main(String[] args) {		
		Rule032_EnumSet.Text2.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
	}
}
