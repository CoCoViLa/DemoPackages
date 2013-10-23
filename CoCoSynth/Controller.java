import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import ee.ioc.cs.vsle.api.Subtask;
import ee.ioc.cs.vsle.editor.Editor;

public class Controller {
	/*@ specification Controller {
		void ready;
		String classPath;
		alias subtasksReady = (*.ready);
		alias spec = (*.spec);
		[spec -> subtasksReady], classPath -> ready {initGUI};
	}
	@*/
	
	public void initGUI(final Subtask subtask, String cp) {
		final CountDownLatch latch = new CountDownLatch(1);
		
		Runnable runEditor = new Runnable() {
			public void run() {
				Editor ed = Editor.createEditor();
				Runnable callback = new Runnable() {
					public void run() {
						latch.countDown();
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


