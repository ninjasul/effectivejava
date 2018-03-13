package chap06.enumeration;

import java.util.EnumMap;
import java.util.Map;

public class Rule033_EnumMap_Phase {
	public enum Phase {
		SOLID,		// ��ü 
		LIQUID, 	// ��ü
		GAS,		// ��ü
		PLASMA;		// �ö��
		
		public enum Transition {
			MELT, 			// ��ü -> ��ü
			FREEZE, 		// ��ü -> ��ü
			BOIL, 			// ��ü -> ��ü
			CONDENSE,		// ��ü -> ��ü
			SUBLIME, 		// ��ü -> ��ü
			DEPOSIT,		// ��ü -> ��ü
			IONIZATION,		// ��ü -> �ö��
			DEIONIZATION;	// �ö�� -> ��ü
			
			// ��ĩ �߸��Ͽ� �迭 ������ Ʋ������ �Ǹ� ArrayIndexOutOfBoundsException �̳� NullPointerException �� �߻��� �� ����.
			// �Դٰ� ���ο� ���°� �߰��Ǹ� �迭 ������ ������ �־�� �ϴµ� �� �������� ������ �߻��� �� ����.
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
		SOLID,		// ��ü 
		LIQUID, 	// ��ü
		GAS,		// ��ü
		
		// �̷��� ���ο� ���¸� �߰� �ϴ��� �ش� Transiton enum �׸�鸸 �߰� �� �ָ� ������ ����.
		PLASMA;		// �̿�ȭ �� ��ü		
		
		public enum Transition {
			MELT(SOLID, LIQUID), 		// ��ü -> ��ü
			FREEZE(LIQUID, SOLID), 		// ��ü -> ��ü
			BOIL(LIQUID, GAS), 			// ��ü -> ��ü
			CONDENSE(GAS, LIQUID),		// ��ü -> ��ü
			SUBLIME(SOLID, GAS), 		// ��ü -> ��ü
			DEPOSIT(GAS, SOLID),		// ��ü -> ��ü
			IONIZATION(GAS, PLASMA),	// ��ü -> �ö��
			DEIONIZATION(PLASMA, GAS);	// �ö�� -> ��ü
			
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
