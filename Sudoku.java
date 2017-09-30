/**
 * Represents a sudoku puzzle to be solved by the Sudoku Solver.
 * 
 * @author Andrew Edinger
 */
public class Sudoku {

	SudokuVar[][] vars; // 2 dimensional array representing the positions of the puzzle
	int size; // Size of puzzle, unsquared
	int subgridSize; // Size of puzzle subgrid, unsquared
	int numSet; // Number of solved squares in the puzzle

	// Takes a 2d array of integers and converts it into a 2d array of SudokuNodes
	public Sudoku(int size, int subgridSize) {
		vars = new SudokuVar[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				vars[i][j] = new SudokuVar(size);
			}
		}
		this.size = size;
		this.subgridSize = subgridSize;
		this.numSet = 0;

	}

	/**
	 * Constructs a new Sudoku object that is a copy of an existing Sudoku object.
	 * 
	 * @param puzzle
	 *            Sudoku object to be copied.
	 */
	public Sudoku(Sudoku puzzle) {
		this.size = puzzle.size;
		vars = new SudokuVar[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.vars[i][j] = new SudokuVar(puzzle.vars[i][j]);
			}
		}

		this.subgridSize = puzzle.subgridSize;
		this.numSet = puzzle.numSet;
	}

	/**
	 * Sets the value of a square in the sudoku and updates the possible values of
	 * the rest of the sudoku accordingly.
	 * 
	 * @param row
	 *            Row of square in which the value will be entered.
	 * @param col
	 *            Column of square in which the value will be entered.
	 * @param value
	 *            Value to enter in the specified square.
	 */
	public void setValue(int row, int col, int value) {
		this.vars[row][col].setValue(value);
		for (int i = 0; i < this.size; i++) {
			vars[i][col].domain.remove(Integer.valueOf(value)); // updates domain of column
			vars[row][i].domain.remove(Integer.valueOf(value)); // updates domain of row
		}

		// updates domain of subgrid
		for (int i = (row / this.subgridSize) * this.subgridSize; i < ((row / this.subgridSize) * this.subgridSize
				+ this.subgridSize); i++) {
			for (int j = (col / this.subgridSize) * this.subgridSize; j < ((col / this.subgridSize) * this.subgridSize
					+ this.subgridSize); j++) {
				this.vars[i][j].domain.remove(Integer.valueOf(value));
			}
		}

		this.numSet++;
	}

	/**
	 * Determines which square in the puzzle has the smallest domain of possible
	 * values and returns its position.
	 * 
	 * @return int[] containing the row and column of the desired square.
	 */
	public int[] getVar() {
		int min = size + 1;
		int[] which = new int[2];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((!vars[i][j].isSet()) && vars[i][j].domain.size() < min) {
					min = vars[i][j].domain.size();
					which[0] = i;
					which[1] = j;
					if (min == 1) {
						return which;
					}
				}
			}
		}

		return which;
	}

	/**
	 * Prints a text representation of the Sudoku as rows of integers to the screen.
	 */
	public void print() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (vars[i][j].isSet()) {
					vars[i][j].print();
				} else {
					System.out.print("-");
				}

			}
			System.out.print("\n");

		}
		System.out.print("\n");

	}

}
