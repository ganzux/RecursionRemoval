
public class Recursion {

	public static void main( String[] args ) throws Exception {

		int times = 14;

		for (int i = 0; i <= times; i++) {
			for (int j = 0; j <= times; j++) {
				int a = f(i, j);
				int b = f2(i, j);
				int c = f3(i, j);
				if (a != b || a != c)
					throw new Exception("Error en el programa: " + a + ", " + b + ", " + c);
				else
					System.out.println("OK para " + i + ", " + j + ": " + a );
			}
		}

		// BASE CASE
		long init = System.currentTimeMillis();

		for (int i = 0; i <= times; i++)
			for (int j = 0; j <= times; j++)
				f(i, j);

		long end = System.currentTimeMillis();

		System.out.println("Tiempo para funcion inicial: " + (end-init) );
		// END OF BASE CASE

		// MULTIPLE RECURSIVITY REMOVED BY MEMORY
		init = System.currentTimeMillis();

		for (int i = 0; i <= times; i++)
			for (int j = 0; j <= times; j++)
				f2(i, j);

		end = System.currentTimeMillis();

		System.out.println("Tiempo con eliminacion de la recursividad multiple por memorizacion: " + (end-init) );
		// END OF MULTIPLE RECURSIVITY REMOVED BY MEMORY

		// MULTIPLE RECURSIVITY REMOVED BY TABULATION
		init = System.currentTimeMillis();

		for (int i = 0; i <= times; i++)
			for (int j = 0; j <= times; j++)
				f3(i, j);

		end = System.currentTimeMillis();

		System.out.println("Tiempo con eliminacion de la recursividad multiple por tabulacion: " + (end-init) );
		// END OF MULTIPLE RECURSIVITY REMOVED BY TABULATION
	}

	private static int f(int x, int y){
		if (x == 0)
			return y;
		else if (y == 0)
			return x;
		else 
			return f(x, y-1) + f(x-1, y) + f(x-1, y-1);
	}

	private static int f2(int x, int y){

		int [][] combs = new int[x+1][y+1];
		for ( int i = 0; i < x; i++)
			for ( int j = 0; j < y; j++)
				combs[i][j] = 0;

		fMem(x, y, combs);

		return combs[x][y];
	}

	private static void fMem(int x, int y, int [][] combs) {

		// If the solution hasnt been calculated
		if(combs[x][y] == 0){

			// 1st base case
			if (x == 0) {
				combs[x][y] =  y;
			}

			// 2nd base case
			else if (y == 0) {
				combs[x][y] =  x;
			}

			else{
				// Recursion calls
				fMem(x, y-1,	combs);
				fMem(x-1, y, 	combs);
				fMem(x-1, y-1,	combs);
				// and the calc of the cell
				combs[x][y] = combs[x][y-1] + combs[x-1][y] + combs[x-1][y-1];
			}
		}
	}


	private static int f3(int x, int y){

		int [][] combs = new int[x + 1][y + 1];

		// 1st Base case
		for (int i = 0;i <= x;i++)
			combs[i][0] = i;

		// 2 nd Base case
		for (int i = 0;i <= y;i++)
			combs[0][i] = i;

		// Calc of the rest of values according with the base cases.
		// Very important the beginning of the aux variables i and j!
		for (int i = 1;i <= x;i++)
			for (int j = 1;j <= y;j++)
				combs[i][j] = combs[i][j-1] + combs[i-1][j] + combs[i-1][j-1];

		return combs[x][y];
	}
}
