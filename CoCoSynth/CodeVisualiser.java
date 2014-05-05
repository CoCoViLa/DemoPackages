import java.awt.*;
import javax.swing.*; 
class CodeVisualiser {
    /*@ specification CodeVisualiser {
    boolean visualizerDone;
    String code;
    code -> visualizerDone {visualise};
    }@*/
	boolean visualise(String code) {
		JFrame frame = new JFrame("Synthesized code");
//		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		JEditorPane textPane = new JEditorPane();
		textPane.setEditable(false);
		textPane.setText(code);
		JScrollPane pane = new JScrollPane(textPane);
		pane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setPreferredSize(new Dimension(550, 1145));
		pane.setMinimumSize(new Dimension(10, 10));
		frame.add(pane);
		frame.pack();
           	frame.setVisible(true);
		return true;
	}
}
  
