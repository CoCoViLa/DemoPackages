import ee.ioc.cs.vsle.ccl.RunnerClassLoader;
import ee.ioc.cs.vsle.editor.ProgramRunner;
import ee.ioc.cs.vsle.synthesize.SpecParseException;
import ee.ioc.cs.vsle.synthesize.Synthesizer;
import ee.ioc.cs.vsle.util.FileFuncs.MemoryStorage;

public class Runner {
    /*@ specification Runner {
      void runnerReady;
			String algorithmCode;
		  ee.ioc.cs.vsle.synthesize.Problem problem;
			String packagePath;
			packagePath, problem, algorithmCode -> runnerReady {genSpec};
    }@*/
	
	public void genSpec(String packagePath, ee.ioc.cs.vsle.synthesize.Problem problem, String code) {
		
		String mainClassSrc = Synthesizer.generateMainClassText(problem, code);
		try {
			System.out.println("mainClassSrc:\n" + mainClassSrc);
			MemoryStorage storage;
			Synthesizer.makeProgram(mainClassSrc, problem.getClassList(),
					problem.getMainClassName(), packagePath, storage = new MemoryStorage());
			
			Object getObj = ProgramRunner.compile(storage, RunnerClassLoader.create(storage, packagePath), problem.getMainClassName());
                               long start = System.currentTimeMillis();
			ProgramRunner.run(getObj);
                               long exeTime = System.currentTimeMillis() - start;
System.out.println("exeTime = " + exeTime);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
