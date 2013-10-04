import ee.ioc.cs.vsle.api.Subtask;
import ee.ioc.cs.vsle.editor.Editor;

public class Controller {
	/*@ specification Controller {
		void ready;
		String classPath;
		alias genOut = (*.genOut);
		alias spec = (*.spec);
		[spec.0 -> genOut], classPath -> ready {initGUI};
	}
	@*/
	
	public void initGUI(Subtask callback, String cp) {
		Editor ed = Editor.createEditor();
		ed.setSubtask(callback);
		ed.setVisible(true);
	}
	
}


