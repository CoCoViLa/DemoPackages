class Max
{
    /*@
	specification Max {
   		int arg, val, maxval;
		[arg->val] -> maxval, (java.lang.Exception){getMaxVal};
      }
	@*/
    public int getMaxVal(Subtask sbt) throws Exception {
        boolean ready = true;
        arg = 0;
        try {
            Object[] in = new Object[1];
            in[0] = new Integer(arg);
            Object[] out = sbt.run(in);

            maxval = ((Integer)out[0]).intValue();
            for(arg = 1; true; arg++) {
                ready = false;
                in[0] = new Integer(arg);
                out = sbt.run(in);
                val = ((Integer)out[0]).intValue();
                if(maxval < val) {
                    maxval = val;
                }
            }
        } catch (Exception ex) {
            if(ready) {				
                throw new Exception();
			}
			System.out.println("Max: " + maxval);
            return maxval;
        }
    }
}