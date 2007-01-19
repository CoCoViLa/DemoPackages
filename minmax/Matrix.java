class Matrix
{
	/*@
	specification Matrix {
		int row, col, element;
		String[] tm;
		int[][] matrix;
		tm -> matrix{parseMatrix};
		matrix, row, col -> element{getElement};
	}
	@*/

	public int getElement(int[][] m, int i, int j) throws ArrayIndexOutOfBoundsException
	{
		return m[i][j];
	}

	public int[][] parseMatrix(String[] tm) throws ArrayIndexOutOfBoundsException
	{
		int[][] matrix = new int[tm.length][];

		for( int i = 0; i < tm.length; i++ )
		{
			String[] temp = tm[i].split(",");
			int[] cols = new int[temp.length];

			for( int j = 0; j < temp.length; j++ )
			{     
				cols[j] = Integer.parseInt( temp[j].trim() );
			} 
			matrix[i] = cols;
		}
		return matrix;
	}
}

