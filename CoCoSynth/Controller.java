import java.awt.event.*;
import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ee.ioc.cs.vsle.api.Subtask;
import ee.ioc.cs.vsle.editor.*;
import ee.ioc.cs.vsle.editor.Editor.EditorBuilder;
import ee.ioc.cs.vsle.packageparse.PackageXmlProcessor;
import ee.ioc.cs.vsle.vclass.*;

public class Controller {
	/*@ specification Controller {
		void ready, pathChecked;
		//Initial configuration
		boolean showAlgorithm;
		showAlgorithm = false;
		boolean computeAll;
		computeAll = true;
		boolean runProgram;
		runProgram = true;
		boolean generate;
		generate = false;
		
		String packageFile, schemeFile;
		packageFile = "";
		schemeFile = "";
		String _spec, _packagePath;
		
		alias computeAlls = (*.computeAll);
		computeAlls.length, computeAll -> computeAlls{fill};
		
		alias spec = (*.spec);
		alias packagePath = (*.packagePath);
		
		spec.length, _spec -> spec{fill};
		_packagePath -> _packagePath, pathChecked{checkPath};
		pathChecked, packagePath.length, _packagePath -> packagePath{fill};
		
		alias runnerReady = (*.runnerReady);
		alias visualizerReady = (*.visualizerReady);
		alias generatorReady = (*.genOut);
		
		[_spec, _packagePath -> runnerReady], 
		[_spec, _packagePath -> visualizerReady], 
		[_spec, _packagePath -> generatorReady],
		packageFile, schemeFile, showAlgorithm, computeAll, runProgram, generate
			-> ready {initGUI};
	}
	@*/
	
	public String checkPath(String path) {
		File packageFile = new File(path);
		if(packageFile.isFile())
			path = packageFile.getParentFile().getAbsolutePath() + File.separator;
		return path;
	}
	
	public Object[] fill(int length, Object input) {
		Object[] result = new Object[length];
		java.util.Arrays.fill(result, input);
		return result;
	}

	public void initGUI(final Subtask runProgramSt, final Subtask showAlgorithmSt, final Subtask generateSt, 
			String _package, String _scheme, boolean showAlgorithm, boolean computeAll, boolean runProgram, boolean generate) {
		
		if(runProgram) {
			final CountDownLatch latch = new CountDownLatch(1);

			final EditorBuilder builder = new EditorBuilder();

			Runnable callback = new Runnable() {
				public void run() {
					latch.countDown();
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							builder.getEditor().setVisible(true);
							builder.getEditor().dispose();
						}
					});
				}
			};

			builder
				.setOnExitAction(callback)
				.setPackageFile(getStringOrNull(_package))
				.setSchemeFile(getStringOrNull(_scheme))
				.setWindowTitle("CCV")
				.addEditorAction( Menu.RUN, new EditorAction() {
                    public void onAction( String action, Editor editor, ActionEvent e ) {
                        Canvas canv = editor.getCurrentCanvas();
                        final ee.ioc.cs.vsle.vclass.Scheme scheme = canv.getScheme();
                        final String schemeName = canv.getSchemeName();
                        final String path = canv.getPackage().getPath();
                        final String spec = 
                                SpecGenFactory.getInstance()
                                    .getCurrentSpecGen().generateSpec(scheme, schemeName);
                        
                        new Thread(new Runnable() {
                            public void run() {
                                runProgramSt.run(new Object[]{spec, path});
                            }
                        }).start();
                    }
                })
				.create();

			try {
				System.out.println("Waiting for latch in Controller.initGUI()");
				latch.await();
				System.out.println("Latch released in Controller.initGUI()");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(generate) {
			VPackage pkg;
			File packFile = new File(_package);
      if((pkg = PackageXmlProcessor.load(packFile)) != null ) {
      	SchemeContainer container = new SchemeContainer( pkg, packFile.getParent() + File.separator );
      	container.loadScheme( new File(_scheme) );
      	String spec = SpecGenFactory.getInstance().getCurrentSpecGen().generateSpec(container.getScheme(), container.getSchemeName());
      	
      	generateSt.run(new Object[] {spec, _package});
      }
			
		};
		
		if(showAlgorithm) {
			VPackage pkg;
			File packFile = new File(_package);
      if((pkg = PackageXmlProcessor.load(packFile)) != null ) {
      	SchemeContainer container = new SchemeContainer( pkg, packFile.getParent() + File.separator );
      	container.loadScheme( new File(_scheme) );
      	String spec = SpecGenFactory.getInstance().getCurrentSpecGen().generateSpec(container.getScheme(), container.getSchemeName());
      	
      	showAlgorithmSt.run(new Object[] {spec, _package});
      }
		}
	}
	
	private String getStringOrNull(String s) {
	    return s == null || s.trim().length() == 0 ? null : s;
	}
}


