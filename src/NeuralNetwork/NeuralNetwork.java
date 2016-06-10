package NeuralNetwork;


import NeuralNetwork.Layers.Layer;
import NeuralNetwork.TransferFunctions.TransferFunction;
import NeuralNetwork.Utils.Matrix;

import java.io.*;

/**
 * Created by charleshamesse on 16/05/16.
 */

public class NeuralNetwork implements Serializable {
    protected double learningRate = 0.2;
    protected Layer[] layers;
    protected TransferFunction transferFunction;


    /**
     * Constructor
     *
     * @param layers Array of layers for this network
     * @param lr learning rate
     */
    public NeuralNetwork(Layer[] layers, double lr)
    {
        learningRate = lr;
        this.layers = layers;

    }


    /**
     * 
     *
     * @param input 
     * @return 
     */
    public double[][] execute(double[][] input)
    {
        int i;
        int j;
        int k;
        double new_value;

        double output[][] = new double[layers[layers.length - 1].getHeight()][layers[layers.length - 1].getWidth()];

        // Put input
        for(i = 0; i < layers[0].getHeight(); i++)
            for(j = 0; j < layers[0].getWidth(); j++)
                layers[0].getNeuron(i, j).value = input[i][j];

        // Execute - hiddens + output
        for(k = 1; k < layers.length; k++)
        {
            layers[k].connectPreviousLayer(layers[k-1]);
            layers[k].execute();
        }


        // Get output
        for(i = 0; i < layers[layers.length - 1].getHeight(); i++)
            for(j = 0; j < layers[layers.length - 1].getWidth(); j++) {
                output[i][j] = layers[layers.length - 1].getNeuron(i, j).value;
            }

        return output;
    }




    /**
     * Backprop algorithm
     *
     * @param input
     * @param output
     * @return
     */

    public double backPropagate(double[][] input, double[][] output)
    {
        double new_output[][] = execute(input); // 1X1
        double error;
        int i, j, k, l, m;

        // Adjust last layer
        for(i = 0; i < layers[layers.length - 1].getHeight(); i++) {
            for (j = 0; j < layers[layers.length - 1].getWidth(); j++) {
                error = output[i][j] - new_output[i][j];
                layers[layers.length - 1].getNeuron(i,j).delta = error * layers[layers.length - 1].getTransferFunction().evaluateDerivate(new_output[i][j]);
            }
        }

        // Adjust all other layers
        for(k = layers.length - 2; k >= 0; k--)
        {
            // Compute delta
            for(i = 0; i < layers[k].getHeight(); i++)
            {
                for(j = 0; j < layers[k].getWidth(); j++)
                {
                    error = 0.0;
                    for(l = 0; l < layers[k + 1].getHeight(); l++)
                        for(m = 0; m < layers[k + 1].getWidth(); m++)
                            error += layers[k + 1].getNeuron(l, m).delta * layers[k + 1].getNeuron(l, m).weights[i][j];

                    layers[k].getNeuron(i, j).delta = error * layers[k].getTransferFunction().evaluateDerivate(layers[k].getNeuron(i, j).value);
                }
            }

            // Propagate and adjust weight
            for(i = 0; i < layers[k + 1].getHeight(); i++)
            {
                for(j = 0; j < layers[k + 1].getWidth(); j++)
                {
                    for(l = 0; l < layers[k].getHeight(); l++)
                        for(m = 0; m < layers[k].getWidth(); m++)
                            layers[k + 1].getNeuron(i, j).weights[l][m] += learningRate * layers[k + 1].getNeuron(i, j).delta * layers[k].getNeuron(l, m).value;

                    layers[k + 1].getNeuron(i, j).bias += learningRate * layers[k + 1].getNeuron(i, j).delta;
                }
            }
        }

        // Compute error
        error = 0.0;

        for(i = 0; i < output.length; i++)
        {
            for(j = 0; j < output[i].length; j++)
            {
                error += Math.abs(new_output[i][j] - output[i][j]);
            }
        }

        error = error / output.length;
        return error;
    }

    public Layer[] getLayers() {
        return layers;
    }

    public double getLearningRate()
    {
        return learningRate;
    }
    public void	setLearningRate(double rate)
    {
        learningRate = rate;
    }

}
