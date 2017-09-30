import java.util.ArrayList;

/**
 * SudokuVar accompanies the Sudoku object. Represents the value that has been entered in a square,
 * or the list of possible consistent values for an undetermined square.
 * 
 * @author Andrew Edinger
 */
public class SudokuVar {

	int size;
	int value;
	public ArrayList<Integer> domain;

	/**
	 * Constructs a SudokuVar for a Sudoku of the specified size.
	 * 
	 * @param size Size of Sudoku containing this SudokuVar.
	 */
	public SudokuVar(int size) {
		this.domain = new ArrayList<Integer>(size);
		this.size = size;
		this.value = -1;
		for (int i = 1; i <= size; i++) {
			this.domain.add(Integer.valueOf(i));
		}

	}

	/**
	 * Constructs a new SudokuVar that is a copy of an existing SudokuVar.
	 * 
	 * @param otherVar SudokuVar to be copied.
	 */
	public SudokuVar(SudokuVar otherVar) {
		this.domain = new ArrayList<Integer>(otherVar.domain);
		this.size = otherVar.size;
		this.value = otherVar.value;
	}

	/**
	 * @return Value of a determined square; -1 if no value has been entered.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Sets the value of the SudokuVar.
	 * 
	 * @param value Value to be entered.
	 */
	public void setValue(int value) {
		this.value = value;
		this.domain.clear();
	}

	/**
	 * Prints the value of the SudokuVar; prints "-" if no value has been entered.
	 */
	public void print() {
		if (value == -1) {
			System.out.print("-");
		} else {
			System.out.print(value);
		}

	}

	/**
	 * @return True if value has been set, false otherwise.
	 */
	public boolean isSet() {
		return value != -1;
	}

	/**
	 * @return True if a SudokuVar has no possible values, false otherwise.
	 */
	public boolean inconsistent() {
		return domain.size() == 0;
	}

}
