import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import ee.ioc.cs.vsle.api.Subtask;
import ee.ioc.cs.vsle.editor.Editor;
import ee.ioc.cs.vsle.editor.Editor.EditorBuilder;

public class Controller {
	/*@ specification Controller {
		void ready, pathChecked;
		String packageFile, schemeFile;
		packageFile = "";
		schemeFile = "";
		String _spec, _packagePath;
		alias runnerReady = (*.runnerReady);
		alias spec = (*.spec);
		alias packagePath = (*.packagePath);
		spec.length, _spec -> spec{fill};
		_packagePath -> _packagePath, pathChecked{checkPath};
		pathChecked, packagePath.length, _packagePath -> packagePath{fill};
		[_spec, _packagePath -> runnerReady], packageFile, schemeFile -> ready {initGUI};
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

	public void initGUI(final Subtask subtask, String _package, String _scheme) {
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
			.setPackageFile(_package == null || _package.trim().length() == 0 ? null : _package)
			.setSchemeFile(_scheme == null || _scheme.trim().length() == 0 ? null : _scheme)
			.setSubtask(subtask)
			.setCallback(callback)
			.create();
		
		try {
			System.out.println("Waiting for latch in Controller.initGUI()");
			latch.await();
			System.out.println("Latch released in Controller.initGUI()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}


