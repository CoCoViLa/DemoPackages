import ee.ioc.cs.vsle.synthesize.SpecParseException;
import ee.ioc.cs.vsle.synthesize.Synthesizer;
import ee.ioc.cs.vsle.util.FileFuncs.MemoryStorage;

public class Exe {
    /*@ specification Exe {
			String algorithmCode, ready;
		  ee.ioc.cs.vsle.synthesize.Problem problem;
			String packagePath;
			packagePath, problem, algorithmCode -> ready {genSpec};
    }@*/
	
   String genSpec(String packagePath, ee.ioc.cs.vsle.synthesize.Problem problem, String code) {
  	 String mainClassSrc = Synthesizer.generateMainClassText(problem, code);
  	 try {
  		 System.out.println("mainClassSrc:\n" + mainClassSrc);
			Synthesizer.makeProgram(mainClassSrc, problem.getClassList(), problem.getMainClassName(), packagePath, new MemoryStorage());
		} catch (SpecParseException e) {
			throw new RuntimeException(e);
		}
	   return "done";
   }
}
