package sigma.se;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static int a[][];
    public static int b[][][];
    public static HashSet<Integer> rowSet[] = new HashSet[9];
    public static HashSet<Integer> columnSet[] = new HashSet[9];


    /**
     * this method transform array of size 9,9 to 9 arrays of 3,3 size
     *
     * @param a: the array of 9,9 size
     * @return array of 3,3,9 size
     */
    public static int[][][] transform(int[][] a) {
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
     *
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


    public static int[][] readArray() {
        Scanner sc = new Scanner(System.in);
        int a[][] = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                a[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }
        return a;
    }


    public static void print99(int[][] a) {

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
    public static boolean getSets() {
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

    public static HashSet<Integer> getSet(int i, int j) {
        rowSet[i] = new HashSet<>();
        columnSet[j] = new HashSet<>();

        for (int k = 0; k < 9; k++) {
            if (a[i][k] != 0)
                rowSet[i].add(a[i][k]);
            if (a[k][j] != 0)
                columnSet[j].add(a[k][j]);
        }

        rowSet[i].addAll(columnSet[j]);
        return rowSet[i];

    }

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

    public static HashSet<Integer> join(HashSet<Integer> s, HashSet<Integer> d) {
        s.addAll(d);
        return s;
    }

    public static void main(String[] args) {
        a = new int[9][9];
        b = new int[3][3][9];


        a = readArray();
        boolean hasDuplicate;

        hasDuplicate = getSets();

        System.out.println(hasDuplicate);
//        b = transform(a);
//             print339(b);
    }


}
