package chap06.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Rule035_Annotation_ExceptionTest {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)	
	public @interface ExceptionTest {
		Class<? extends Exception> [] value() default {Exception.class};
	}
		
	public static class Sample {
		@ExceptionTest({ArithmeticException.class})
		public static void m1() {
			int i = 0;
			i = i / i;
		}
		
		@ExceptionTest({ArithmeticException.class, IndexOutOfBoundsException.class})
		public static void m2() {
			int[] a = new int[0];
			int i = a[1];
		}
			
		@ExceptionTest({ArithmeticException.class})
		public static void m3() {}
		
		@ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
		public static void doublyBad() {
			List<String> list = new ArrayList<String>();			
			list.addAll(5, null);
		}
	}
		
	public static void main(String[] args) {
		int tests = 0;
		int passed = 0;
		
		Class testClass = Sample.class;
		for( Method m : testClass.getDeclaredMethods()) {
			if( m.isAnnotationPresent(ExceptionTest.class)) {
				tests++;
				
				try { 
					m.invoke(null);
					System.out.printf("Test %s failed: no exception%n", m);
				}
				catch (Throwable wrappedException) {
					Throwable exception = wrappedException.getCause();
					Class<? extends Exception> [] excTypes = m.getAnnotation(ExceptionTest.class).value();	
					int oldPassed = passed;
					
					for( Class<? extends Exception> excType : excTypes ) {
						if( excType.isInstance(exception) ) {
							passed++;
							break;
						}
					}
					
					if( passed == oldPassed ) {
						System.out.printf("Test %s failed: %s %n", m, exception);
					}
				}
			}
		}	
	}
}
