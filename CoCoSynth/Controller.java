import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import ee.ioc.cs.vsle.api.Subtask;
import ee.ioc.cs.vsle.editor.Editor;

public class Controller {
	/*@ specification Controller {
		void ready;
		String classPath;
		String _spec, _packagePath;
		alias subtasksReady = (*.ready);
		alias spec = (*.spec);
		alias packagePath = (*.packagePath);
		spec.length, _spec -> spec{fill};
		packagePath.length, _packagePath -> packagePath{fill};
		[_spec, _packagePath -> subtasksReady], classPath -> ready {initGUI};
	}
	@*/
	
	public Object[] fill(Object input, int length) {
		Object[] result = new Object[length];
		java.util.Arrays.fill(result, input);
		return result;
	}

	public void initGUI(final Subtask subtask, String cp) {
		final CountDownLatch latch = new CountDownLatch(1);
		
		Runnable runEditor = new Runnable() {
			public void run() {
				final Editor ed = Editor.createEditor();
				Runnable callback = new Runnable() {
					public void run() {
						latch.countDown();
						SwingUtilities.invokeLater(new Runnable() {
            						public void run() {
            							ed.dispose();
            						}
            					});
					}
				};
				ed.setSubtask(subtask, callback);
				ed.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(runEditor);
		
		try {
			System.out.println("Waiting for latch in Controller.initGUI()");
			latch.await();
			System.out.println("Latch released in Controller.initGUI()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}


