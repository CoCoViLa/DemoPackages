public class Edit {
    /*@ specification Edit {
	String spec;
	-> spec {genSpec};
    }@*/
	
   String genSpec() { 
	   return "public class CoCo {\n" +
			   "  /*"+"@"+" specification CoCo {\n" +
			   "    Edit1 ed;" +
			   "  }"+"@"+"*"+"/" +
			   "}";
   }
}
