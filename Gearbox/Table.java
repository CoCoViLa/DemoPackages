import javax.swing.*;
import java.awt.*;
class Table {
	/*@
	specification Table {
		int first, step, last;
		double P, n, y;
		boolean result;
		alias (double) x = (P, n);
		[x ->y], first, step, last -> result {calc};
	}
	@*/
	boolean calc (Subtask stm, int f, int s, int l) {
		System.out.println("f " + f + " s " + s + " l " + l);
		double[] in2 = new double[2];
		Object[] out = new Object[1];

        		Object[][] tableData = new Object[(int)(l/s)][(int)(l/s)];
		Object[]   colNames = new Object[(int)(l/s)-1];
		for (int i = f; i < l ; i += s ) {
			for (int j = f; j < l ; j += s ) {
				in2[0] = i;
				in2[1] = j;
				Object[] in = new Object[]{in2};
				try	{
					out = stm.run(in);
				}
				catch (Exception e)	{
				e.printStackTrace();
				}
                
				Double d_out = (Double)(out[0]);
				tableData[(int)(j/s)-1][(int)(i/s)-1] = d_out;
				colNames[(int)(j/s)-1] = new Double(j);
				//System.out.println("d_out: " + d_out + " j: " + j );
			
			}
		}

		for (int i = 0; i< colNames.length ; i++) {
			Double d = (Double)colNames[i];
			//System.out.println("dd "+ d);
		}
		JTable table = new JTable(tableData, colNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 170));
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel jp = new JPanel(new GridLayout(1,0));
		jp.add(scrollPane);
		jp.setOpaque(true);
		JFrame jf = new JFrame("Result table");
		jf.setContentPane(jp);
		jf.pack();
		jf.setVisible(true);

		return true;
	}
}





