import java.io.*;
class DataWriter {
	
	/*@
	specification DataWriter {
		double[] a, b;
		void x,y;
		String fileName;
		a, fileName  -> x{writeFile};
		a, b, fileName -> y{writeFile};
	}@*/

	public void writeFile(double[] a, String s) {
		try {
			PrintWriter out = new PrintWriter (
			new BufferedWriter(new FileWriter(s+"1", true)));
			out.println(a[0]);
			for (int i = 1; i < a.length; i++) {
				out.print(", " + a[i]);
			}
			out.println();
			out.close();
		} catch (Exception e) {

		}	
	}

	public void writeFile(double[] a, double[] b, String s) {
		try {
			PrintWriter out = new PrintWriter (
			new BufferedWriter(new FileWriter(s+"2", true)));
			out.print(a[0]);
			for (int i = 1; i < a.length; i++) {
				out.print(", " + a[i]);
			}
			
			for (int i = 0; i < b.length; i++) {
				out.print(", " + b[i]);
			}
			out.println();
			out.close();
		} catch (Exception e) {

		}	
	}

}
