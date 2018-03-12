package chap05.generic;

public class Rule031_Enum_PayrollDay {
	enum PayrollDay {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, HOLIDAY;
		
		private static final int HOURS_PER_SHIFT = 8;
		
		double pay(double hoursWorked , double payRate) {
			double basePay = hoursWorked * payRate;
		
			// 초과근무수당계산
			double overtimePay; 
			
			switch(this) {
				case SATURDAY: 
				case SUNDAY:
					overtimePay = hoursWorked * payRate / 2;
					break;
				
				default: // Weekdays
					overtimePay = hoursWorked <= HOURS_PER_SHIFT ?
							0 : (hoursWorked - HOURS_PER_SHIFT) * payRate / 2;					
			}
			
			return basePay + overtimePay;
		}
	}
	
	enum PayrollDay2 {
		MONDAY(PayType.WEEKDAY), 
		TUESDAY(PayType.WEEKDAY), 
		WEDNESDAY(PayType.WEEKDAY), 
		THURSDAY(PayType.WEEKDAY), 
		FRIDAY(PayType.WEEKDAY), 
		SATURDAY(PayType.WEEKEND), 
		SUNDAY(PayType.WEEKEND);
		
		private final PayType payType;
		PayrollDay2(PayType payType) {
			this.payType = payType;
		}
		
		private enum PayType {
			WEEKDAY {
				double overtimePay(double hours, double payRate) {
					return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
				}
			},			
			WEEKEND {
				double overtimePay(double hours, double payRate) {
					return hours * payRate / 2;
				}
			};
			
			private static final int HOURS_PER_SHIFT = 8;
			abstract double overtimePay(double hrs , double payRate);
			
			double pay(double hoursWorked , double payRate) {
				double basePay = hoursWorked * payRate;
				return basePay + overtimePay(hoursWorked , payRate);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
