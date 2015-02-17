import javax.swing.*;

// This statement imports methods rerun, terminate, setFileldValue, etc.
// Without the static import only long names can be used: ProgramContext.rerun(), ...
import static ee.ioc.cs.vsle.api.ProgramContext.*;

public class Global {
    /*@ specification Global {
        int init;
        int a, b, c;
        void done;

        // some simple and meaningless formulas
        init = 7;
        init = a + b;

        init, cocovilaSpecObjectName -> a {askA};

        c = a * b;

        b, c, cocovilaSpecObjectName -> done {confirm};
    } @*/

    int askA(int init, final String objectName) {
        // The generated program runs in a separate thread, therefore
        // everything Swing-related including JOptionPanes must be
        // created using SwingUtilities.invoke*!
        final String[] v = new String[1];
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    v[0] = JOptionPane.showInputDialog("a = ?");
                    setValue(objectName, "a", v[0]);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            terminate();
        }
        return Integer.parseInt(v[0]);
    }

    void confirm(final int b, final int c, String objectName) {
        setValue(objectName, "b", Integer.toString(b));
        setValue(objectName, "c", Integer.toString(c));

        final int[] rv = new int[1];

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    rv[0] = JOptionPane.showConfirmDialog(null,
                        "Is c = " + c + " OK?", "Confirm c",
                        JOptionPane.YES_NO_OPTION);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            terminate();
        }

        if (rv[0] == JOptionPane.YES_OPTION) {
            terminate();
        } else {
            // Clear all the fields and rerun the program to recalculate
            // everything
            setValue(objectName, "a", null);
            setValue(objectName, "b", null);
            setValue(objectName, "c", null);
            rerun();
        }
    }

    void setValue(String objectName, String fieldName, String value) {
        setFieldValue(objectName, fieldName, value);
        // To make the new value visible on the scheme immediately
        ProgramContext.getScheme().repaint();
    }
}


