# Sudoku-Solver


The Sudoku Solver program prompts the user for the path and filename of the
sudoku puzzle, formatted as a .txt, to be solved and loads and converts the
.txt file into a Sudoku object. The .txt file should be formatted as follows:
The first line of the file contains two integers, N and M, separated by a
space. N is the dimension of the puzzle, square. M is the size of the square
sub-grids. Following the first line will be N lines, each containing N digits
and dashes to represent the puzzle. Each board will consist of a matrix where
a dash represents an unknown value and digits represent known values. Note that
this repository contains several sudoku text files for testing and example purposes.
After the file has been properly loaded into memory, a recursive backtracking 
algorithm is used to find a solution to the sudoku, and the solution is then
printed to console.

The source code contained in this repository was written by Andrew Edinger as part 
of an assignment for Dr. Michael Zmuda's course in Intro to Artificial Intelligence 
at Miami University of Oxford, Ohio.

