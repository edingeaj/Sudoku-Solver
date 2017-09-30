import java.io.File;
import java.util.Scanner;

/**
 * Loads an unsolved sudoku puzzle formatted as a .txt document and uses a
 * recursive backtracking algorithm to find a solution. The solution is then
 * printed to console
 * 
 * @author Andrew Edinger
 */
public class SudokuSolver {

	/**
	 * Main method. Makes use of loadSudoku and solveSudoku methods
	 * 
	 * @param args
	 *            Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {

		Sudoku puzzle = loadSudoku();

		// final long startTime = System.nanoTime();

		solveSudoku(puzzle);

		// System.out.println("Solution found in " + (System.nanoTime() -
		// startTime)/(float)1000000 + " seconds." );

	}

	/**
	 * The loadSudoku method prompts the user for the path and filename of the
	 * sudoku puzzle, formatted as a .txt, to be solved and loads and converts the
	 * .txt file into a Sudoku object. The .txt file should be formatted as follows:
	 * The first line of the file contains two integers, N and M, separated by a
	 * space. N is the dimension of the puzzle, square. M is the size of the square
	 * sub-grids. Following the first line will be N lines, each containing N digits
	 * and dashes to represent the puzzle. Each board will consist of a matrix where
	 * a dash represents an unknown value and digits represent known values. The
	 * example below shows the input format:
	 * 
     * 9 3
     * 1-5--46--
     * --72---31
     * 4--63--9-
     * ---31-825
     * 9-------4
     * 851-72---
     * -7--56--8
     * 34---71--
     * --89--2-6	 * 
	 * 
	 * @param None.
	 * @return Sudoku Sudoku object as read from specified .txt file.
	 * 
	 */
	public static Sudoku loadSudoku() {
		int size = 0;
		int subgridSize = 0;
		Sudoku puzzle = null;

		System.out.println("Enter filename:");
		Scanner console = new Scanner(System.in);
		String fileName = console.next();

		try {

			File filePath = new File(fileName);

			Scanner sc = new Scanner(filePath);

			size = sc.nextInt();
			subgridSize = sc.nextInt();
			sc.nextLine();

			puzzle = new Sudoku(size, subgridSize);

			int i = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				/* Reads .txt file into the Sudoku object 'puzzle' */
				for (int j = 0; j < line.length(); j++) {
					char value = line.charAt(j);
					if (value != '-') {
						puzzle.setValue(i, j, Character.getNumericValue(value));
					}
				}
				i++;

			}

			// puzzle.print();
			sc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		console.close();
		return puzzle;
	}

	/**
	 * The solveSudoku method implements a recursive backtracking algorithm to
	 * determine the solution to the input Sudoku object.
	 * 
	 * @param puzzle
	 *            Sudoku object to be solved.
	 * @return Nothing.
	 */
	public static void solveSudoku(Sudoku puzzle) {

		boolean solutionFound = false;
		int[] loc = puzzle.getVar();

		/* Compares number of solved values in the puzzle with total size of puzzle */
		if (puzzle.numSet == puzzle.size * puzzle.size) {
			solutionFound = true;
			puzzle.print();
			return;
		}
		/* Checks puzzle to see if a consistent solution exists */
		else if (puzzle.vars[loc[0]][loc[1]].inconsistent()) {
			// System.out.println("Inconsistent " + loc[0] + " " + loc[1]);
			return;
		}
		/* If there is only 1 possible value for a position in the puzzle, fill it in */
		else if (puzzle.vars[loc[0]][loc[1]].domain.size() == 1) {
			puzzle.setValue(loc[0], loc[1], puzzle.vars[loc[0]][loc[1]].domain.get(0));
			solveSudoku(puzzle);
		}
		/*
		 * Otherwise, takes the location in the puzzle with fewest possible values.
		 * Copies the current puzzle, state, enters one of the values, and recursively
		 * attempts to solve the resultant puzzle. If this copy has no consistent
		 * solution, repeat the process with a new copy and different value until a
		 * solution is found or all values have been attempted without success.
		 */
		else {
			for (int i = 0; i < puzzle.vars[loc[0]][loc[1]].domain.size(); i++) {
				if (!solutionFound) {
					Sudoku copy = new Sudoku(puzzle);
					// System.out.println("Multiple values at: " + loc[0] + " " + loc[1]);
					// System.out.println("Copy:");
					copy.setValue(loc[0], loc[1], puzzle.vars[loc[0]][loc[1]].domain.get(i));

					// copy.print();
					solveSudoku(copy);

				}
			}
		}

	}

}
