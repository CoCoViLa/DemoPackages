class Min
{
    /*@
	specification Min {
   		int arg, val, minval;
		[arg->val] -> minval{getMinVal};
      }
	@*/
        public int getMinVal(Subtask sbt) throws Exception {
            boolean ready = true;
            arg = 0;
            try {
                Object[] in = new Object[1];
                in[0] = new Integer(arg);
                Object[] out = sbt.run(in);
                minval = ( (Integer) out[0]).intValue();

                for(arg = 1;true; arg++) {
                    ready = false;
                    in[0] = new Integer(arg);
                    out = sbt.run(in);
                    val = ( (Integer) out[0]).intValue();

                    if(minval > val) {
                        minval = val;
                    }
                }
            } catch (Exception ex) {
                if(ready)
                    throw new Exception();
                return minval;
            }
        }
}