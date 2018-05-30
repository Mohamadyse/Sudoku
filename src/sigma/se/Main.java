package sigma.se;

import java.util.HashSet;
import java.util.Scanner;

public class Main {



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

                //        System.out.print(i + "," + j);

                //        k=3* (int) (i/3)+ (int) j/3;
                //     k=  (i-i%3)/3;
                k = i - i % 3 + (j - j % 3) / 3;
                b[i % 3][j % 3][k] = a[i][j];
                // System.out.print( "("+k+ ")" + "  ");

            }
        }
        return b;
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
        int a[][]=new int[9][9];
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

    public static void main(String[] args) {

        int a[][];
        int b[][][];
        HashSet<Integer> s =new HashSet<>();
        HashSet<Integer> setRow[] = new HashSet[9];
        HashSet<Integer> setColumn[] = new HashSet[9];

        a = new int[9][9];
        b = new int[3][3][9];

        a=readArray();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
              if !(setRow[i].add( a[i][j]) )
                if ( a[i][j] == 0) {

                }
            }




        b = transform(a);
             print339(b);
    }



}
