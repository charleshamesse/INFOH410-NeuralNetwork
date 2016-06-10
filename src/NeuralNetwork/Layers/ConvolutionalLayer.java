package NeuralNetwork.Layers;

import NeuralNetwork.Neuron;
import NeuralNetwork.TransferFunctions.TransferFunction;
import NeuralNetwork.Utils.Matrix;

/**
 * Created by charleshamesse on 16/05/16.
 */
public class ConvolutionalLayer extends Layer {

    private double[][] kernel;
    private int stride;

    /**
     * FullyConnectedLayer
     * @param ni Number of input neurons
     * @param no Number of output neurons = number of neurons in this layer
     * @param tf Transfer function
     */
    public ConvolutionalLayer(int[] ni, int[] no, TransferFunction tf) {
        super(ni, no, tf);
        // Stride
        this.stride = 1;
        // Kernel with F = 3
        this.kernel = new double[][]{
                {0.0, 0.0, 0.0},
                {-1.0, 1.0, -1.0},
                {0.0, 0.0, 0.0}
        };

    }

    public void connectPreviousLayer(Layer l) {
        this.previousLayer = l;
    }

    public void execute() {
        // TO-DO
        int i, j, l, m;
        double new_value;
        // Pad input
        Double[][] input = applyPadding();
        // Convolve kernel
        for(i = 0; i < getHeight(); ++i) {
            for(j = 0; j < getWidth(); ++j) {

                // For each this layer's cells
                new_value = 0.0;
                // Weight does not apply if we're on the border
                for(l = 0; l < kernel.length; ++l) {
                    for(m = 0; m < kernel[0].length; ++m) {
                        if(stride*i+l < getHeight() && stride*j+m < getWidth()) {
                            new_value += neurons[i][j].weights[stride*i+l][stride*j+m] * input[stride*i+l][stride*j+m] * kernel[l][m];
                        }
                    }
                }

                new_value += this.getNeuron(i, j).bias;
                neurons[i][j].value = tf.evaluate(new_value);
            }
        }

    }
    private Double[][] applyPadding() {
        int h = previousLayer.getHeight() + 2;
        int w = previousLayer.getWidth() + 2;
        int i, j;
        Double[][] new_input = new Double[h][w];
        for(i = 0; i < h; ++i) {
            for(j = 0; j < w; ++j) {
                if(i == 0 || j == 0 || i == h-1 || j == w-1) new_input[i][j] = 0.0;
                else new_input[i][j] = previousLayer.getNeuron(i-1,j-1).value;
            }
        }
        return new_input;
    }

}
