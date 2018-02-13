package chap04.classinterface;

public class Rule017_Interface {
	
	public interface Singer {
		
		// �������̽��� �ʵ�� public static final �� ����. 
		public static final int testField = 1;
				
		// �������̽��� �޼ҵ�� public, abstract, default, static, strictfp �� ����.
		void sing();
	}
	
	public interface SongWriter {
		void compose();
	}
	
	public interface SingerSongWriter extends Singer, SongWriter {
		void strum();
		void actSesitive();
	}
	
	public static class SingerSongWriterClass implements SingerSongWriter {
		
		//private static final int testField = 2;
		
		@Override
		public void sing() {
			// ��� �ʵ�鵵 �״�� ��ӵ�.
			System.out.println("sing() - " + testField);
		}

		@Override
		public void compose() {
			// TODO Auto-generated method stub
			System.out.println("compose() - " + testField);
		}

		@Override
		public void strum() {
			// TODO Auto-generated method stub
			System.out.println("strum() - " + testField);
		}

		@Override
		public void actSesitive() {
			// TODO Auto-generated method stub
			System.out.println("actSesitive() - " + testField);
		}
	}
	
	public static void main(String[] args) {
		Rule017_Interface.SingerSongWriterClass test = new Rule017_Interface.SingerSongWriterClass();
		test.sing();		
	}
}
