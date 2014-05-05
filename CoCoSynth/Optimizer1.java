
import ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm;
import ee.ioc.cs.vsle.synthesize.PlanningResult;
import ee.ioc.cs.vsle.synthesize.Rel;
import ee.ioc.cs.vsle.synthesize.Problem;
import ee.ioc.cs.vsle.synthesize.RelType;
import ee.ioc.cs.vsle.synthesize.SubtaskRel;
import ee.ioc.cs.vsle.synthesize.Var;

import java.util.*;

public class Optimizer1 {
	/**
	 * Deletes unnecessary assignments caused by equalities.
	 * At present should work correctly for a linear branch.
	 */
    /*@ specification Optimizer1 {
       ee.ioc.cs.vsle.synthesize.Problem problem; 
       ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm in, out;
       problem, in -> out{optimize};
    }@*/	
    EvaluationAlgorithm  optimize(ee.ioc.cs.vsle.synthesize.Problem problem, EvaluationAlgorithm algorithm){
    	EvaluationAlgorithm result = new EvaluationAlgorithm();
    	HashMap<String, String> substitutions = new HashMap<String, String>();
    	System.out.println("***I am optimizer1***");
    	java.util.Set<Var> goals= problem.getCurrentContext().getAllGoals();
    	
    	for ( PlanningResult alg : algorithm ) {
    		Rel rel = alg.getRel();
    		Var output = rel.getFirstOutput();
    		boolean isGoal = goals.contains(output);
    		
    		if (isEquality(rel)) {
    			StringTokenizer varsPair=new StringTokenizer(rel.getDeclaration(),"=,;");
    			varsPair.nextToken();//skip first
    			String first = output.getFullName();
    			String second = rel.getInputs().isEmpty() ? varsPair.nextToken() : rel.getFirstInput().getFullName();
//    			System.out.println("***first= "+first+"    second= "+second);
    			expandSubst(substitutions,first,second);
    			
    			if(isGoal) {
    				result.addRel(rel);
    				System.out.println("*** goal = "+output.getFullName()+ " ***  ");
    			}
//    			System.out.println((isGoal ? "***goal   " : "***not a goal***  ") +rel.getDeclaration());
    		} 
    		else {
    			rel.addSubstitutions(substitutions);
    			
				if (rel.getType() == ee.ioc.cs.vsle.synthesize.RelType.TYPE_METHOD_WITH_SUBTASK) {

					PlanningResult resultWithSubtasks = new PlanningResult(rel, true);
					
					for (SubtaskRel subtask : rel.getSubtasks()) {
						EvaluationAlgorithm subtaskAlgorithm = optimize(problem, alg.getSubtaskAlgorithm( subtask ));
						resultWithSubtasks.addSubtaskAlgorithm(subtask, subtaskAlgorithm);
					}
					
					result.add(resultWithSubtasks);
				}
				else {
					result.addRel(rel);
				}
    		}
    	}
	   return result; 
    	
    }
    boolean isEquality(Rel rel){
    	String s=rel.getDeclaration();
    	String[] ops = {"(","+","-","*","/","^"};
    	for(int i=0; i<ops.length;i++){
    		if(s.indexOf(ops[i]) != -1){
    			return false;};
    	};    	
    	return true;   	
    }
    
//    boolean isGoal(String var1){
//    	return false;
//    }
    
    HashMap<String,String> expandSubst(HashMap<String, String> substitutions,String var1,String var2){
    	String s; 
    	s = substitutions.get(var1);
    	if(s != null){
    		substitutions.put(var2,s);
    		System.out.println("added"+var2+""+s);
    	}; 
    	s = substitutions.get(var2);    	
    	if(s != null){
    		substitutions.put(var1,s);
    	}; 
    	return substitutions;
    }
    

 
}
    
