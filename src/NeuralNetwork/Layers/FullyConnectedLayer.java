package NeuralNetwork.Layers;

import NeuralNetwork.Neuron;
import NeuralNetwork.TransferFunctions.TransferFunction;
import NeuralNetwork.Utils.Matrix;

/**
 * Created by charleshamesse on 16/05/16.
 */
public class FullyConnectedLayer extends Layer {

    /**
     * FullyConnectedLayer
     * @param ni Number of input neurons
     * @param no Number of output neurons = number of neurons in this layer
     * @param tf Transfer function
     */
    public FullyConnectedLayer(int[] ni, int[] no, TransferFunction tf) {
        super(ni, no, tf);
    }

    public void execute() {
        int i, j;
        double new_value;
        for(i = 0; i < getHeight(); i++)
        {
            for(j = 0; j < getWidth(); j++)
            {
                new_value = 0.0;
                for(int l = 0; l < previousLayer.getHeight(); l++)
                    for(int m = 0; m < previousLayer.getWidth(); m++)
                        new_value += neurons[i][j].weights[l][m] * previousLayer.getNeuron(l, m).value;

                new_value += this.getNeuron(i, j).bias;

                neurons[i][j].value = tf.evaluate(new_value);
            }
        }
    }

}
