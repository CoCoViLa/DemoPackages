import ee.ioc.cs.vsle.synthesize.ClassList;
import ee.ioc.cs.vsle.synthesize.ProblemCreator;
import ee.ioc.cs.vsle.synthesize.SpecParser;

public class Parser {
	/*@ specification Parser { 
		 String packagePath;
		 ClassList classList; 
		 String spec; 
		 ee.ioc.cs.vsle.synthesize.Problem problem; 
		 String mainClassName;
		 spec -> mainClassName {getMainClassName};
		 spec, mainClassName, packagePath -> classList {makeClasslist}; 
		 classList, mainClassName -> problem {makeProblem}; 
	 }@*/

	public String getMainClassName(String spec) {
		return SpecParser.getClassName( spec );
	}
	
	public ClassList makeClasslist(String spec, String mainClassName, String packagePath) {
		try {
			return SpecParser.parseSpecification( spec, mainClassName, null, packagePath );
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public ee.ioc.cs.vsle.synthesize.Problem makeProblem(ClassList classList, String mainClassName) {
		ee.ioc.cs.vsle.synthesize.Problem problem;
		
		try {
			problem = new ProblemCreator(classList, mainClassName).makeProblem();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		return problem;
	}
}
