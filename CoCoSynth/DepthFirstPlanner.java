import ee.ioc.cs.vsle.synthesize.PlannerFactory;

class DepthFirstPlanner {
    /*@ specification DepthFirstPlanner {
      void plannerReady;
	    ee.ioc.cs.vsle.synthesize.Problem problem; 
	    ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm;
	    boolean computeAll;
	    problem, computeAll -> algorithm, plannerReady {planner};
    }@*/
	
	
	ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm planner(ee.ioc.cs.vsle.synthesize.Problem problem, boolean computeAll) {
		ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm = PlannerFactory.getInstance()
				.getCurrentPlanner().invokePlaning(problem, computeAll);
		return algorithm;
	}
 
}
