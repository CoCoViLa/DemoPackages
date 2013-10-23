public class Exe {
    /*@ specification Exe {
	String algorithmCode, ready;
          boolean controlExe;
	controlExe, algorithmCode -> ready {genSpec};
    }@*/
	
   String genSpec(boolean b, String code) { 
	System.out.println("Exe: " + code);
	   return "done";
   }
}
