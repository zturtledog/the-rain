package main.library;

public class util {
    public static String intarrtostr(int[] arr) {
        String retrn = "@int["+arr.length+"]: {";

        for (int i = 0; i < arr.length-1; i++) {
            retrn += arr[i] + ", ";
        }

        retrn += arr[arr.length-1];

        return retrn+"}";
    }

    public static int map(double val, int start1, int stop1, int start2, int stop2) {
        return (int) (((val-start1)/(stop1-start1))*(stop2-start2)+start2);
    }
}


/*
 * 1:4
 * 1:10
 * 1:8
 * 1:12
 * 
 * 6:20
 * 6:6
 * 
 * 8:10
 */