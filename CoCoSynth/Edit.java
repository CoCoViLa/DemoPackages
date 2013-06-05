public class Edit {
    /*@ specification Edit {
	String spec;
	-> spec {genSpec};
    }@*/
	
   String genSpec() { 
	   return "public class CoCo {\n" +
			   "  /*"+"@"+" specification CoCo {\n" +
			   "    Edit1 ed;\n" +
"    int a, b;\n" +
"    a=2;\n" +
"    a=b-2;\n" +			   
"  }"+"@"+"*"+"/" +
			   "}";
   }
}
