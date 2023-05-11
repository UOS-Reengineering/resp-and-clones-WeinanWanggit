package example.project.CodeClones;

import java.util.List;

public class DotPlot {


    //word based
    public static void printDotPlot(List<String> sequence1, List<String> sequence2) {
        int rows = sequence1.size();
        int cols = sequence2.size();

        boolean[][] dotPlot = new boolean[rows][cols];

        // Construct the dot plot matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (sequence1.get(i).equals(sequence2.get(j))) {
                    dotPlot[i][j] = true;
                }
            }
        }

        // Print the dot plot
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dotPlot[i][j]) {
                    System.out.print("+ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }




//char based
//    public static void printDotPlot(char[] sequence1, char[] sequence2) {
//        int rows = sequence1.length;
//        int cols = sequence2.length;
//
//        // create a 2D array of booleans to represent the dot plot
//        boolean[][] dotPlot = new boolean[rows][cols];
//
//        // populate the dot plot array based on the matching characters
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (sequence1[i] == sequence2[j]) {
//                    dotPlot[i][j] = true;
//                }
//            }
//        }
//
//        // print the dot plot
//        System.out.print(" ");
//        for (int j = 0; j < cols; j++) {
//            System.out.print(sequence2[j]);
//        }
//        System.out.println();
//
//        for (int i = 0; i < rows; i++) {
//            System.out.print(sequence1[i]);
//            for (int j = 0; j < cols; j++) {
//                if (dotPlot[i][j]) {
//                    System.out.print("+");
//                } else {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//    }



}
