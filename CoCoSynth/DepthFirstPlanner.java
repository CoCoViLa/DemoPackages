import ee.ioc.cs.vsle.synthesize.PlannerFactory;

class DepthFirstPlanner {
    /*@ specification DepthFirstPlanner {
    ee.ioc.cs.vsle.synthesize.Problem problem;
    ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm;
    boolean computeAll;
    problem, computeAll -> algorithm {planner1};
    }@*/
	
	
	ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm planner1(ee.ioc.cs.vsle.synthesize.Problem problem, boolean computeAll) {
		ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm = PlannerFactory.getInstance()
				.getCurrentPlanner().invokePlaning(problem, computeAll);
		return algorithm;
	}
 
}
