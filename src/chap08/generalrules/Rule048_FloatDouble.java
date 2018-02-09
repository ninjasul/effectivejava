package chap08.generalrules;

import java.math.BigDecimal;

// Rule48 - 정확한 답이 필요하다면 float 과 double은 피하라.
public class Rule048_FloatDouble {
	
	private static void doFloatDouble() {
		double funds = 1.00;
		int itemsBought = 0;
		
		// double 을 매번 더하다 보면 원하는 결과 값이 나오지 않음.
		for( double price = .10; funds >= price; price += .10 ) {			
			System.out.println("funds: " + funds + ", price: " + price + ", itemsBought: " + itemsBought );
			
			funds -= price;
			itemsBought++;
			
		}
		
		System.out.println(itemsBought + " items bought.");
		System.out.println("Change: $" + funds);
	}
	
	// BigDecimal 은 정확한 결과 값을 얻을 수 있으나
	// 기본 사칙 연산자를 사용할 수 없고 속도가 느림.
	private static void doBigDecimal() {
		final BigDecimal TEN_CENTS = new BigDecimal(".10");
		
		int itemsBought = 0;
		BigDecimal funds = new BigDecimal("1.00");
		
		for( BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
			System.out.println("funds: " + funds + ", price: " + price + ", itemsBought: " + itemsBought );
			
			funds = funds.subtract(price);
			itemsBought++;
		}
			
		System.out.println(itemsBought + " items bought.");
		System.out.println("Money left over: $" + funds);
	}
	
	// 십진수 9자리 이하 연산은 int
	// 18자리 이하 연산은 long으로 해결 할 수 있음.
	private static void doIntLong() {
		int itemsBought = 0;
		int funds = 100;
		
		for( double price = 10; funds >= price; price += 10 ) {			
			System.out.println("funds: " + funds + ", price: " + price + ", itemsBought: " + itemsBought );
			
			funds -= price;
			itemsBought++;
			
		}
		
		System.out.println(itemsBought + " items bought.");
		System.out.println("Money left over: " + funds + " cents");
	}
	
	public static void main(String[] args) {
		doFloatDouble();			
		doBigDecimal();
		doIntLong();
	}
	
}
