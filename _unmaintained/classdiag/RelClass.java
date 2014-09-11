import java.io.*;
class RelClass {
	/*@ 
	specification RelClass  {
		String parent, name;
		name -> parent {id};
	}
	@*/
	String id (String name) {
		return name;
	}
};