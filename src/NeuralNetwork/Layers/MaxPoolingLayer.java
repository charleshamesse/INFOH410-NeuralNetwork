package NeuralNetwork.Layers;

import NeuralNetwork.Neuron;
import NeuralNetwork.TransferFunctions.TransferFunction;

/**
 * Created by charleshamesse on 19/05/16.
 */
public class MaxPoolingLayer extends Layer{

    private int h_pool;
    private int w_pool;
    /**
     * MaxPoolingLayer
     * @param ni Number of input neurons
     * @param no Number of output neurons = number of neurons in this layer
     * @param tf Transfer function
     */
    public MaxPoolingLayer(int[] ni, int[] no, TransferFunction tf) {
        super(ni, no, tf);
        this.h_pool =  ni[1]/no[1];
        this.w_pool =  ni[0]/no[0];

    }

    public void execute() {
        int i, j, l, m;
        double new_value;
        for(i = 0; i < getHeight(); i++)
        {
            for(j = 0; j < getWidth(); j++)
            {
                new_value = 0.0;
                for(l = 0; l < h_pool; l++)
                    for(m = 0; m < w_pool; m++) {
                        if (previousLayer.getNeuron(h_pool * i + l, w_pool * j + m).value > new_value)
                            new_value = previousLayer.getNeuron(h_pool * i + l, w_pool * j + m).value;
                    }

                new_value += this.getNeuron(i, j).bias;

                neurons[i][j].value = tf.evaluate(new_value);
            }
        }
    }




}
