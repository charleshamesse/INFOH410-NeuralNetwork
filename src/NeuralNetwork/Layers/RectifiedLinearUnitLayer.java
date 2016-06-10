package NeuralNetwork.Layers;

import NeuralNetwork.TransferFunctions.TransferFunction;

/**
 *
 * Created by charleshamesse on 19/05/16.
 */
public class RectifiedLinearUnitLayer extends Layer {

    public RectifiedLinearUnitLayer(int[] ni, int[] no, TransferFunction tf) {
        super(ni, no, tf);
    }

    public void execute() {
        int i, j;
        double new_value;
        for(i = 0; i < getHeight(); i++)
        {
            for(j = 0; j < getWidth(); j++)
            {
                neurons[i][j].value = (previousLayer.getNeuron(i, j).value < 0.0) ? 0.0 : previousLayer.getNeuron(i, j).value;
            }
        }
    }

}
