package com.lab.ly.persist;

import org.junit.Test;

/**
 * Created by haswell on 1/18/15.
 */
public class BitTest {

    @Test
    public void ensureSubsetSumWorks() {

        int[] a = {11,18,21,28,31,38,40,55,60,62};
        System.out.println(hasSum(a,  67));

        int[] b = {18,11,21,28,3,38,40,55,60,62};
        System.out.println(hasSum(b, 66));
    }

    @Test
    public void ensureCountPathsWorks() {
        int[][] m = {
                new int[] {1,0,0,0},
                new int[] {1,0,0,0},
                new int[] {1,0,0,0},
                new int[] {1,1,1,0}

        };

        System.out.println(numberOfPaths(m));


    }

    int count = 0;
    void countPaths(int[][] array, int x, int y)
    {
        int m = array.length - 1; int n = array[0].length -1;
        if (x==array.length && y==array[0].length){
            count++;
            return;
        }

        if (array[x][y]==0 || x>m || y>n){
            return;
        }
        countPaths(array, x, y + 1);
        countPaths(array, x + 1, y);
    }


    int numberOfPaths(int[][] count)
    {
        int m = count.length;
        int n = count.length;
        for (int i = 1; i < count.length; i++)
        {
            for (int j = 1; j < count[i].length; j++) {
                count[i][j] = count[i - 1][j] + count[i][j - 1];// + count[i-1][j-1];
            }

        }
        return count[m-1][n-1];
    }


    public static int hasSum(int[] elements, int sum) {
        return hasSum(elements, 0, sum);
    }


    public static int hasSum(int [] elements, int start, int sum) {
        boolean s = (sum == 0 || start <= elements.length - 1
                && ((hasSum(elements, start + 1, sum) == 1) ||
                (hasSum(elements, start + 1, sum - elements[start])) == 1));
        return s ? 1 : 0;
    }
}
