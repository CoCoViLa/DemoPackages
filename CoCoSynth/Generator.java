import ee.ioc.cs.vsle.synthesize.CodeGenerator;

class Generator {
    /*@ specification Generator {
	    ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm;
	    ee.ioc.cs.vsle.synthesize.Problem problem;            
	    String out;
             algorithm, problem -> out{generate};
    }@*/
 
	public String generate(ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm, 
			   ee.ioc.cs.vsle.synthesize.Problem problem) {
		
		CodeGenerator cg = new CodeGenerator( algorithm, problem, problem.getMainClassName() );
        String algorithmCode = cg.generate();
        return algorithmCode;
	}
}
