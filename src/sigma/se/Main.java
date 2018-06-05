package sigma.se;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static int a[][]=new int[9][9];
    public static HashSet<Integer> rowSet[] = new HashSet[9];
    public static HashSet<Integer> columnSet[] = new HashSet[9];
    public static Scanner sc = new Scanner(System.in);


    /**
     * it reads the matrix whose size 9x9
     *
     * @return array int [9],[9]
     */

    public static int[][] readArray(int a[][]) {
//        int a[][] = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sc.hasNextInt())
                a[i][j] = sc.nextInt();
            }

                sc.nextLine();
        }

        return a;
    }

    /**
     * prints array whose size 3,3,9
     *
     * @param a
     */
    public static void print99(int[][] a) {
        System.out.println();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * this method checks if there is duplicates in the rows or columns
     */
    public static boolean doesMatrixContainDuplicates() {
        boolean hasDuplicate = false;
        for (int i = 0; i < 9; i++) {
            rowSet[i] = new HashSet<>();
            columnSet[i] = new HashSet<>();
        }
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (a[i][j] != 0)
                    if (!(rowSet[i].add(a[i][j]))) {
                        return hasDuplicate = true;
                    }
                if (a[j][i] != 0)
                    if (!(columnSet[j].add(a[j][i]))) {
                        return hasDuplicate = true;
                    }

            }
        return hasDuplicate;
    }

    /**
     * this method return a set of all elements of the matrix in the row i and the column j
     */
    public static HashSet<Integer> getRowColumnSet(int i, int j) {
        rowSet[i] = new HashSet<>();
        columnSet[j] = new HashSet<>();

        for (int l = 0; l < 9; l++) {
            if (a[i][l] != 0)
                rowSet[i].add(a[i][l]);
            if (a[l][j] != 0)
                columnSet[j].add(a[l][j]);
        }

        rowSet[i].addAll(columnSet[j]);
        return rowSet[i];

    }

    /**
     * this method gets the set of all elements of small matrix 3X3 where the position i,j is located.
     *
     * @param i:    the row
     * @param j:the column
     * @return hashset of the elements in matrix
     */
    public static HashSet<Integer> getMatrixSet(int i, int j) {
        int k = i - i % 3 + (j - j % 3) / 3;
        int column = 3 * (k % 3);
        int row = k - (k % 3);
        HashSet<Integer> matrixSet = new HashSet<>();
        for (int x = row; x < row + 3; x++) {
            for (int y = column; y < column + 3; y++) {
                matrixSet.add(a[x][y]);
            }
        }

        return matrixSet;
    }

    /**
     * this method return the elements that does not belong to the set s "negation"
     *
     * @param s the set that we want to get
     * @return the complement set of the set s.
     */
    public static HashSet<Integer> complement(HashSet<Integer> s) {
        HashSet<Integer> def = new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};
        for (int i : s) {
            def.remove(i);
        }
        return def;
    }

    /**
     * this method get the unioin of two sets,
     *
     * @param s first set
     * @param d secand set
     * @return a union of two sets
     */
    public static HashSet<Integer> join(HashSet<Integer> s, HashSet<Integer> d) {
        s.addAll(d);
        return s;
    }

    /**
     * this method returns the set of potential element that can be the answer according to Sudoku
     *
     * @param i:    the row
     * @param j:the column
     */
    public static HashSet<Integer> answerSet(int i, int j) {
        return complement(join(getMatrixSet(i, j), getRowColumnSet(i, j)));
    }

    /**
     * it parses all the sets of potential elements and when the set contains just one size it replace zero bye that element
     *          k:  is the multiplication of all the elements in the matrix, when k doesnt equal zero then the matrix has been solved.
     */
    public static void solve( ) {
        boolean hasSolution = false;
//      //add condition to the another sets
        boolean hasDuplicate = doesMatrixContainDuplicates();
        int k ;
        whileLoop:
        do {
            k = 1;
            if (!hasDuplicate) {
                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++) {
                        if (a[i][j] == 0) {
                            HashSet<Integer> answers = answerSet(i, j);
                           /* if (answers.size() == 0) {
                                System.out.println("\nFind another job ");
                                hasDuplicate = true;
                                break whileLoop;
                            }*/
                            if (answers.size() == 1) {
                                hasSolution = true;
                                for (int theOnlyElement : answers) {
                                    a[i][j] = theOnlyElement;
                                }
                            }
                            k *= a[i][j];
                        }
                    }


                if (!hasSolution) {
                    System.out.print("Non-unique ");
                    return;
                }
            } else {
                System.out.println("\nFind another job  ");
                return;
            }
        }while (k == 0);
        if ((!hasDuplicate) && (hasSolution)) {
            print99(a);
        }
    }

    public static void main(String[] args) {


         readArray(a);
        solve();

       
    }


}
