class Table {
	/*@
	specification Table {
		int x, y, first, step, last;
		boolean result;


		[x ->y], first, step, last -> result {calc};
	}
	@*/
			//first = 100;
		//step = 200;
		//last = 10000;
	boolean calc (Subtask stm, int f, int s, int l) {
		System.out.println("f " + f + " s " + s + " l " + l);
		for (int i = f; i < l ; i += s )
		{
			Object[] in = new Object[1];
			in[0] = new Integer(i);
			Object[] out = null;
			try
			{
				out = stm.run(in);
			}
			catch (Exception e)
			{
			}
			
			System.out.println("Out " + ((Integer)out[0]).intValue());
		}
		return true;
	}
}
