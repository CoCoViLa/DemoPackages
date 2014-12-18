
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
//Substitutions is a collection of pairs that redefine the bound variables of a rel after optimization.
    	HashMap<String, String> substitutions = new HashMap<String, String>();
		return optimizeInternal(problem.getAssumptions(), problem.getCurrentContext().getAllGoals(), substitutions, algorithm);
	}
	
    private EvaluationAlgorithm  optimizeInternal(java.util.Collection<Var> goalInputs, java.util.Collection<Var> goalOutputs, 
    	HashMap<String, String> substitutions, EvaluationAlgorithm algorithm){
    	EvaluationAlgorithm result = new EvaluationAlgorithm();   	
        //goalOutputs.addAll(goalInputs);    //??
 System.out.println("***I am optimizer1*** with goals: " + goalInputs + " -> " + goalOutputs);
    	for ( PlanningResult alg : algorithm ) {
    		Rel rel = alg.getRel();
 System.out.println(rel.getDeclaration());
    		Var firstOutput = rel.getFirstOutput();
 System.out.println(firstOutput.getFullName());
    		boolean isGoal = goalOutputs.contains(firstOutput);
 //For an equality, add a new element into HashMap substitutions.   		
    		if (rel.getType() == ee.ioc.cs.vsle.synthesize.RelType.TYPE_EQUATION & rel.getInputs().isEmpty()){
				result.addRel(rel);
    		}
    		else if(rel.getType() == ee.ioc.cs.vsle.synthesize.RelType.TYPE_EQUATION & isEquality(rel)) {
    			
    			StringTokenizer varsPair=new StringTokenizer(rel.getDeclaration(),"=,;");
    			varsPair.nextToken();//skip first
    			String first = firstOutput.getFullName();
    			String second = rel.getInputs().isEmpty() ? varsPair.nextToken() : rel.getFirstInput().getFullName();
System.out.println("***first= "+first+"    second= "+second);
				//first := second;
    			expandSubst(substitutions,first,second);
//If the equality computes a goal, then add it as a new step into optimized algorithm.    			
    			if(isGoal) {
    				result.addRel(rel);
System.out.println("*** goal = "+firstOutput.getFullName()+ " ***  ");
    			}
//    			System.out.println((isGoal ? "***goal   " : "***not a goal***  ") +rel.getDeclaration());
    		} 
    		else {
    			rel.addSubstitutions(substitutions);
//If method with subtasks, call optimizer recursively for every subtask.   			
				if (rel.getType() == ee.ioc.cs.vsle.synthesize.RelType.TYPE_METHOD_WITH_SUBTASK) {
System.out.println("************** SUBTASK  ***  ");

					PlanningResult resultWithSubtasks = new PlanningResult(rel, true);
					
					for (SubtaskRel subtask : rel.getSubtasks()) {
						HashMap<String, String> substitutionsCopy = new HashMap<String, String>(substitutions);
System.out.println("All substitutionsCopy: " + substitutionsCopy);					
						EvaluationAlgorithm subtaskAlgorithm = optimizeInternal(subtask.getInputs(), subtask.getOutputs(), substitutionsCopy, alg.getSubtaskAlgorithm( subtask ));
			
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
    
    void expandSubst(HashMap<String, String> substitutions, String output, String input){
    	
    	String existingSubstitution = substitutions.get(input);
    	if(existingSubstitution == null) {
    		substitutions.put(output, input);
    	}
    	else {
    		substitutions.put(output, existingSubstitution);
    	}
    }
 
}
    
