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
		// enum 상수별 데이터 취합 시 Set 배열을 사용하는 방법
		// 배열 크기 및 배열 index를 수동으로 관리해 주어야 하기 때문에 불편하고 오류 발생 가능성이 있음.
		//------------------------------------------------------------------------------------------------------------
		// Herb의 타입 별 Set 배열을 생성
		Set<Herb>[] herbsByTypeSet = (Set<Herb>[]) new Set[Herb.Type.values().length];
				
		for( int i = 0; i < herbsByTypeSet.length; i++ ) {
			herbsByTypeSet[i] = new HashSet<Herb>();
		}
		
		// Herb 배열을 Set에 넣어줌.
		for( Herb h : garden ) {
			herbsByTypeSet[h.type.ordinal()].add(h);
		}
		
		for( int i = 0; i < herbsByTypeSet.length; i++ ) {
			System.out.printf("%s: %s%n", Herb.Type.values()[i], herbsByTypeSet[i]);
		}
		//------------------------------------------------------------------------------------------------------------
		
		//------------------------------------------------------------------------------------------------------------
		// 배열 대신 EnumMap을 사용
		// 배열을 사용할 때 보다 코드가 더 깔끔하고 안전하며, ordinal을 이용하여 구현한 프로그램과 성능도 비등함.
		// * 주의: EnumMap의 생성자는 키의 class 객체를 인자로 받음.
		//------------------------------------------------------------------------------------------------------------
		Map<Herb.Type, Set<Herb>> herbsByTypeMap = new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
		for( Herb.Type t : Herb.Type.values() ) {
			// Map 의 키로 enum 을 집어 넣음.
			herbsByTypeMap.put( t, new HashSet<Herb>() );			
		}
		
		for( Herb h : garden ) {
			herbsByTypeMap.get(h.type).add(h);
		}
		System.out.println(herbsByTypeMap);
		//------------------------------------------------------------------------------------------------------------
	}
}
