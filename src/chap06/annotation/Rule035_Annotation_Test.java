package chap06.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Rule035_Annotation_Test {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)	
	public @interface Test {}
		
	public static class Sample {
		@Test
		public static void m1() {}
		
		public static void m2() {}
		
		@Test
		public static void m3() {
			throw new RuntimeException("Boom");
		}
		
		public static void m4() {}
		
		@Test
		public void m5() {}
		
		
		public static void m6() {}
		
		@Test
		public static void m7() {
			throw new RuntimeException("Crash");
		}
		
		public static void m8() {}
		
	}
	
	public static void main(String[] args) {
		int tests = 0;
		int passed = 0;
		
		Class testClass = Sample.class;
		for( Method m : testClass.getDeclaredMethods()) {
			if( m.isAnnotationPresent(Test.class)) {
				tests++;
				
				try { 
					m.invoke(null);
					passed++;
				}
				catch (InvocationTargetException wrappedException) {
					Throwable exc = wrappedException.getCause();
					System.out.println(m + " failed: " + exc);					
				}
				catch( Exception exc ) {
					System.out.println("INVALID @Test: " + m);
				}
			}
		}
		
		System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
	}
}
