import java.io.*;
class RelClass {
	/*@ 
	specification RelClass  {
		String parentName, childsParent;
		parentName -> childsParent {id};

	}
	@*/
	String id (String name) {
		return name;
	}
};