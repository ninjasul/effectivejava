package chap08.generalrules;

// Rule51 - 문자열 연결 시 성능에 주의하라.
public class Rule051_StringConcatenation {
	
	private static String [] stringArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P" };
	
	private static String doStringConcatWithOperator() {
		String result = "";
		
		for( String s : stringArray ) {
			result += s;
		}
		
		return result;
	}
	
	private static String doStringConcatWithStringBuilder() {
		StringBuilder sb = new StringBuilder( stringArray.length );
		
		for( String s : stringArray ) {
			sb.append(s);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(doStringConcatWithOperator());
		System.out.println(doStringConcatWithStringBuilder());
	}
}
