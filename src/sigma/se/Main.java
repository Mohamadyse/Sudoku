package sigma.se;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static int a[][];
    public static int b[][][];
    public static HashSet<Integer> rowSet[] = new HashSet[9];
    public static HashSet<Integer> columnSet[] = new HashSet[9];
   public static Scanner sc = new Scanner(System.in);



    /**
     * this method transform array of size 9,9 to 9 arrays of 3,3 size
     *
     * @param a: the array of 9,9 size
     * @return array of 3,3,9 size
     */
  /*  public static int[][][] transform(int[][] a) {
        int k;
        int b[][][] = new int[3][3][9];

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                k = i - i % 3 + (j - j % 3) / 3;
                b[i % 3][j % 3][k] = a[i][j];

            }
        }

        return b;
    }
*/
    public static HashSet<Integer>[] makeSetsOfArrays(int[][][] b) {

        HashSet<Integer>[] arraysSet = new HashSet[9];
        for (int k = 0; k < 9; k++) {
            arraysSet[k] = new HashSet<>();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    arraysSet[k].add(b[i][j][k]);
        }
        return arraysSet;
    }

    /**
     * prints array whose size 3,3,9
     * @param b
     */
    public static void print339(int[][][] b) {

        for (int m = 0; m < 9; m++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(b[i][j][m] + " ");
                }
                System.out.println();
            }
            System.out.println("...........");
        }
    }

    /**
     * it reads the matrix whose size 9x9
     * @return array int [9],[9]
     */

    public static int[][] readArray() {
        int a[][] = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                a[i][j] = sc.nextInt();
            }
           if(sc.hasNextLine()) sc.nextLine();
        }

        return a;
    }

    /**
     * prints array whose size 3,3,9
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
     * this method form sets of rows and columns
     *
     * @return
     */
    public static boolean matrixContainsDuplicates() {
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
     * @param i: the row
     * @param j:the column
     * @return hashset of the elements in matrix
     */
    public static  HashSet<Integer>  getMatrixSet(int i, int j) {
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
     * this method return the elemnts that does not belong to the set s "negation"
     * @param s the set that we want to get
     * @return the complement set of the set s.
     */
    public static HashSet<Integer> complement(HashSet<Integer> s) {
        HashSet<Integer> def = new HashSet<>() {{
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
     *@param i: the row
     * @param j:the column
     */
    public static HashSet<Integer> answerSet(int i, int j){
      return complement ( join ( getMatrixSet( i,  j),getRowColumnSet( i,  j)));
    }

    /**
     * it parses all the sets of potential elements and when the set contains just one size it replace zero bye that element
     * @param a the matrix that wanted to solve according to Sudoku
     * k:  is the multiplication of all the elements in the matrix, when k doesnt equal zero then the matrix has been solved.
     */
    public static void solve(int[][] a){
        boolean hasSolution=false;
        boolean hasDuplicate = matrixContainsDuplicates();
        int k = 0;
        out:
        while (k == 0) {
            if (!hasDuplicate) {
                k = 1;
                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++) {
                        if (a[i][j] == 0) {
                            HashSet<Integer> temp = answerSet(i, j);
                            if (temp.size() == 0) {
                                System.out.println("\nFind another job ");
                                hasDuplicate = true;
                                break out;
                            }
                            if (temp.size() == 1) {
                                hasSolution = true;
                                for (int theOnlyElement : temp) {
                                    a[i][j] = theOnlyElement;
                                }
                            }
                            k *= a[i][j];
                        }
                    }


                    if(!hasSolution) {
                        System.out.println("\nNon-unique ");
                        break ;
                    }
            } else {
                System.out.println("\nFind another job ");
                break;
            }
        }
        if( (!hasDuplicate)&&(hasSolution) ){
            print99(a);
        }
    }
    public static void main(String[] args) {
        a = new int[9][9];


        a = readArray();
        solve(a);

        a = readArray();
        solve(a);

        a = readArray();
        solve(a);
    }


}
