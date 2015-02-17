import java.util.*;

class Delayer {
	/*@ specification Delayer {
			int in, out;
			in -> out{calc};
		}
	@*/
	ArrayList a = new ArrayList();
	int calc (int input) {
		a.add(new Integer(input));
		a.add(new Integer(input));
		Integer  r = (Integer)a.get(0);
		a.remove(0);
		return r.intValue();
	}
}
