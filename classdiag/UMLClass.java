import java.io.*;
class UMLClass {
	/*@ 
	specification UMLClass  {
		String name;
		String parent;
		String[] attrs;
		String[] methods;
		int done;
		int a,b;
		a = b;
		[a->b],name -> done {writeClass};
	}
	@*/
	int writeClass (Subtask st, String name) {
		try {
			PrintWriter out = new PrintWriter (
				new BufferedWriter(new FileWriter(name+".java", true)));
			if (parent != null) {
				out.println("public class "+name +" extends "+parent + " {\n");
			} else
				out.println("public class "+name + " {\n");
			if (attrs!= null) {
				for (int i = 0; i < attrs.length; i++) {
					out.print("    "+attrs[i]+";\n");
				}
			}
			if (methods!= null) {
				for (int i = 0; i < methods.length; i++) {
					out.print("    "+methods[i]+" { \n");
					out.print("    "+" } \n");
				}
			}

			out.println("}");
			out.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}			
	}
};