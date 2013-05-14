import ee.ioc.cs.vsle.synthesize.CodeGenerator;

class Generator {
    /*@ specification Generator {
	    ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm;
	    ee.ioc.cs.vsle.synthesize.Problem problem;
	    String out;
    }@*/
 
	public String generate(
					ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm, 
					ee.ioc.cs.vsle.synthesize.Problem problem, 
					String mainClassName) {
		
		CodeGenerator cg = new CodeGenerator( algorithm, problem, mainClassName );
        String algorithmCode = cg.generate();
        return algorithmCode;
	}
}
