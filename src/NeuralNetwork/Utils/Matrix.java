package NeuralNetwork.Utils;

import NeuralNetwork.Neuron;

/**
 * Created by charleshamesse on 17/05/16.
 */
public class Matrix {

    public static String format(double[][] a) {
        int row, column;
        String aString = "";
        for (row = 0; row < a.length; row++) {
            aString = aString + "\n";
            if (a[row] != null && a[row].length > 0) {
                aString = aString + a[row][0];

                for (column = 1; column < a[row].length; column++) {
                    aString = aString + " " + a[row][column];
                }
            }
        }
        return aString;
    }
    public static String format(Neuron[][] a) {
        int row, column;
        String aString = "";
        for (row = 0; row < a.length; row++) {
            aString = aString + "\n";
            if (a[row] != null && a[row].length > 0) {
                aString = aString + a[row][0].value;

                for (column = 1; column < a[row].length; column++) {
                    aString = aString + " " + a[row][column].value;
                }
            }
        }
        return aString;
    }
    public static void initMat(double[][] mat){
        for(int j=0;j<mat.length;j++){
            for(int k=0;k<mat[j].length;k++){
                mat[j][k] = Math.random();
            }
        }
    }
}
