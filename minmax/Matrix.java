import java.util.*;

class Matrix
{
	/*@
	specification Matrix {
		int row, col, element;
		void m_ready;
		String[] tm;
		tm -> m_ready{parseMartix};
		row, col, m_ready -> element{getElement};
	}
	@*/
	public int[][] matrix;

	public int getElement(int i, int j) throws ArrayIndexOutOfBoundsException
	{
		return matrix[i][j];
	}
    public void parseMatrix(String[] tm) throws ArrayIndexOutOfBoundsException
    {
        int mrows = tm.length;
        int mcols = 0;
        ArrayList[] ar = new ArrayList[mrows];
        for(int i = 0; i < mrows; i++)
        {
            String[] temp = tm[i].split(",");
            ar[i] = new ArrayList();
            for(int j = 0; j < temp.length; j++)
            {                
                ar[i].add(temp[j].trim());
            } 
            mcols = ar[i].size();
        }
        matrix = new int[mrows][mcols];
        for(int i = 0; i < mrows; i++)
        {
            for (int j = 0; j < mcols; j++)
            {                
                matrix[i][j] = Integer.parseInt((String)ar[i].get(j));
            }
        }
    }
}
