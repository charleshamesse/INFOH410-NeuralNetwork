package NeuralNetwork.TransferFunctions;

import NeuralNetwork.NeuralNetwork;

/**
 * Created by charleshamesse on 16/05/16.
 */
public interface TransferFunction
{

    public double evaluate(double value);

    public double evaluateDerivate(double value);
}