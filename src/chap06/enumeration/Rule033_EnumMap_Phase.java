package chap06.enumeration;

import java.util.EnumMap;
import java.util.Map;

public class Rule033_EnumMap_Phase {
	public enum Phase {
		SOLID,		// 고체 
		LIQUID, 	// 액체
		GAS,		// 기체
		PLASMA;		// 플라즈마
		
		public enum Transition {
			MELT, 			// 고체 -> 액체
			FREEZE, 		// 액체 -> 고체
			BOIL, 			// 액체 -> 기체
			CONDENSE,		// 기체 -> 액체
			SUBLIME, 		// 고체 -> 기체
			DEPOSIT,		// 기체 -> 고체
			IONIZATION,		// 기체 -> 플라즈마
			DEIONIZATION;	// 플라즈마 -> 기체
			
			// 자칫 잘못하여 배열 정보가 틀어지게 되면 ArrayIndexOutOfBoundsException 이나 NullPointerException 이 발생할 수 있음.
			// 게다가 새로운 상태가 추가되면 배열 정보를 수정해 주어야 하는데 이 과정에서 오류가 발생할 수 있음.
/*			private static final Transition[][] TRANSITIONS = {
				{	null, 		MELT, 		SUBLIME 	},
				{	FREEZE, 	null, 		BOIL 		},
				{	DEPOSIT, 	CONDENSE, 	null 		}
			};
*/
			private static final Transition[][] TRANSITIONS = {
				{	null, 		MELT, 		SUBLIME, 		null 			},
				{	FREEZE, 	null, 		BOIL,			null			},
				{	DEPOSIT, 	CONDENSE, 	null, 			IONIZATION 		},
				{	null, 		null, 		DEIONIZATION, 	null 		},
			};

			
			public static Transition from(Phase src, Phase dst) {
				return TRANSITIONS[src.ordinal()][dst.ordinal()];
			}
		}
	}
	
	public enum Phase2 {
		SOLID,		// 고체 
		LIQUID, 	// 액체
		GAS,		// 기체
		
		// 이렇게 새로운 상태를 추가 하더라도 해당 Transiton enum 항목들만 추가 해 주면 문제가 없음.
		PLASMA;		// 이온화 된 기체		
		
		public enum Transition {
			MELT(SOLID, LIQUID), 		// 고체 -> 액체
			FREEZE(LIQUID, SOLID), 		// 액체 -> 고체
			BOIL(LIQUID, GAS), 			// 액체 -> 기체
			CONDENSE(GAS, LIQUID),		// 기체 -> 액체
			SUBLIME(SOLID, GAS), 		// 고체 -> 기체
			DEPOSIT(GAS, SOLID),		// 기체 -> 고체
			IONIZATION(GAS, PLASMA),	// 기체 -> 플라즈마
			DEIONIZATION(PLASMA, GAS);	// 플라즈마 -> 기체
			
			private final Phase2 src;
			private final Phase2 dst;
			
			Transition(Phase2 src, Phase2 dst) {
				this.src = src;
				this.dst = dst;
			}
			
			private static final Map<Phase2, Map<Phase2, Transition>> m = new EnumMap<Phase2, Map<Phase2, Transition>>(Phase2.class);
			
			static {
				for( Phase2 p : Phase2.values() ) {
					m.put( p,  new EnumMap<Phase2, Transition>(Phase2.class));
				}
				
				for( Transition t : Transition.values() ) {
					m.get(t.src).put(t.dst, t);
				}
			}
			
			public static Transition from(Phase2 src, Phase2 dst) {
				return m.get(src).get(dst);
			}
		}
	}
	
	public static void main(String[] args) {
		for( Phase src : Rule033_EnumMap_Phase.Phase.values() ) {
			for( Phase dst : Rule033_EnumMap_Phase.Phase.values() ) {
				System.out.println(Rule033_EnumMap_Phase.Phase.Transition.from( src, dst ));
			}
		}
		
		for( Phase2 src : Rule033_EnumMap_Phase.Phase2.values() ) {
			for( Phase2 dst : Rule033_EnumMap_Phase.Phase2.values() ) {
				System.out.println(Rule033_EnumMap_Phase.Phase2.Transition.from( src, dst ));
			}
		}
	}
}
