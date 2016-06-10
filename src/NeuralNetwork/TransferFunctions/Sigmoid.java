package NeuralNetwork.TransferFunctions;

/**
 * Created by charleshamesse on 16/05/16.
 */
public class Sigmoid  implements TransferFunction{

    @Override
    public double evaluate(double value)
    {
        return 1 / (1 + Math.pow(Math.E, - value));
    }

    @Override
    public double evaluateDerivate(double value)
    {
        return (value - Math.pow(value, 2));
    }
}