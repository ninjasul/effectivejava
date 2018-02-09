package chap08.generalrules;

import java.math.BigDecimal;

// Rule48 - ��Ȯ�� ���� �ʿ��ϴٸ� float �� double�� ���϶�.
public class Rule048_FloatDouble {
	
	private static void doFloatDouble() {
		double funds = 1.00;
		int itemsBought = 0;
		
		// double �� �Ź� ���ϴ� ���� ���ϴ� ��� ���� ������ ����.
		for( double price = .10; funds >= price; price += .10 ) {			
			System.out.println("funds: " + funds + ", price: " + price + ", itemsBought: " + itemsBought );
			
			funds -= price;
			itemsBought++;
			
		}
		
		System.out.println(itemsBought + " items bought.");
		System.out.println("Change: $" + funds);
	}
	
	// BigDecimal �� ��Ȯ�� ��� ���� ���� �� ������
	// �⺻ ��Ģ �����ڸ� ����� �� ���� �ӵ��� ����.
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
	
	// ������ 9�ڸ� ���� ������ int
	// 18�ڸ� ���� ������ long���� �ذ� �� �� ����.
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
