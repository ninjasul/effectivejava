package chap06.enumeration;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Rule033_EnumMap_Herb {
	static class Herb {
		enum Type { ANNUAL, PERENNIAL, BIENNIAL }
		
		final String name;
		final Type type;
		
		Herb(String name, Type type) {
			this.name = name;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	public static void main(String[] args) {
		Herb[] garden = { new Herb( "annual", Herb.Type.ANNUAL ), new Herb( "perennial", Herb.Type.PERENNIAL ), new Herb( "perennial2", Herb.Type.PERENNIAL ) }; 
		
		//------------------------------------------------------------------------------------------------------------
		// enum ����� ������ ���� �� Set �迭�� ����ϴ� ���
		// �迭 ũ�� �� �迭 index�� �������� ������ �־�� �ϱ� ������ �����ϰ� ���� �߻� ���ɼ��� ����.
		//------------------------------------------------------------------------------------------------------------
		// Herb�� Ÿ�� �� Set �迭�� ����
		Set<Herb>[] herbsByTypeSet = (Set<Herb>[]) new Set[Herb.Type.values().length];
				
		for( int i = 0; i < herbsByTypeSet.length; i++ ) {
			herbsByTypeSet[i] = new HashSet<Herb>();
		}
		
		// Herb �迭�� Set�� �־���.
		for( Herb h : garden ) {
			herbsByTypeSet[h.type.ordinal()].add(h);
		}
		
		for( int i = 0; i < herbsByTypeSet.length; i++ ) {
			System.out.printf("%s: %s%n", Herb.Type.values()[i], herbsByTypeSet[i]);
		}
		//------------------------------------------------------------------------------------------------------------
		
		//------------------------------------------------------------------------------------------------------------
		// �迭 ��� EnumMap�� ���
		// �迭�� ����� �� ���� �ڵ尡 �� ����ϰ� �����ϸ�, ordinal�� �̿��Ͽ� ������ ���α׷��� ���ɵ� �����.
		// * ����: EnumMap�� �����ڴ� Ű�� class ��ü�� ���ڷ� ����.
		//------------------------------------------------------------------------------------------------------------
		Map<Herb.Type, Set<Herb>> herbsByTypeMap = new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
		for( Herb.Type t : Herb.Type.values() ) {
			// Map �� Ű�� enum �� ���� ����.
			herbsByTypeMap.put( t, new HashSet<Herb>() );			
		}
		
		for( Herb h : garden ) {
			herbsByTypeMap.get(h.type).add(h);
		}
		System.out.println(herbsByTypeMap);
		//------------------------------------------------------------------------------------------------------------
	}
}
