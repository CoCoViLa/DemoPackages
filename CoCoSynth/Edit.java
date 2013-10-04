public class Edit {
    /*@ specification Edit {
	String spec;
          boolean controlEdit;
	controlEdit -> spec {genSpec};
    }@*/
	
   String genSpec(boolean b) { 
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
