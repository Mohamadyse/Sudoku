# Sudoku
developing to algorithm to solve Sudoku  
The popular game of Sudoku requires the player to fill in a 9-by-9 board using the digits 1 through 9, such that no row, column, or 3-by-3 square (see diagram) has more than one copy of each digit. The player is given a partially completed board and must fill in the blank squares. Designing a Sudoku puzzle appears easy – just start with a solved, valid puzzle, and erase the digits in some of the squares. However, if the designer erases too many squares, then there might be multiple solutions. This can make solving the puzzle more difficult, and potentially less fun. Thus, you should write a program to help a designer find out if their puzzle has exactly one solution.
# Input
Input consists of up to 100 Sudoku boards. Each board is given as a 9-by-9 matrix using digits 0 through 9, one matrix row per line. If a board square contains 0, that means it’s been erased. There is a blank line between pairs of boards. Input ends at end of file.
# Output
For each board, if there is a unique solution, print the solution using the same format as the input. If there are multiple solutions, print “Non-unique”. If the designer has made a terrible mistake and there is no solution, print “Find another job”. Separate the outputs for adjacent cases with a blank line.
