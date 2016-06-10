package NeuralNetwork.Layers;

import NeuralNetwork.Neuron;
import NeuralNetwork.TransferFunctions.TransferFunction;

/**
 * Created by charleshamesse on 16/05/16.
 */
public class Layer {

    protected int nix, niy, nox, noy;
    protected double[] w, b;
    protected TransferFunction tf;
    protected Neuron[][] neurons;
    protected Layer previousLayer;


    /**
     * Layer
     * @param ni Number of input neurons
     * @param no Number of output neurons = number of neurons in this layer
     * @param tf Transfer function
     */
    public Layer(int[] ni, int[] no, TransferFunction tf) {
        this.nix = ni[0];
        this.niy = ni[1];
        this.nox = no[0];
        this.noy = no[1];

        this.neurons = new Neuron[noy][nox];
        this.tf = tf;

        for(int i = 0; i < noy; i++)
            for(int j = 0; j < nox; j++)
                neurons[i][j] = new Neuron(new int[] {nix, niy});

    }

    public double[][] getNeuronValues() {
        double[][] arr = new double[getHeight()][getWidth()];
        for(int i = 0; i < noy; ++i) {
            for(int j = 0; j < nox; ++j) {
                arr[i][j] = neurons[i][j].value;
            }
        }
        return arr;
    }
    public int getHeight() {
        return this.noy;
    }
    public int getWidth() {
        return this.nox;
    }
    public Neuron[][] getNeurons() { return this.neurons; }
    public Neuron getNeuron(int i, int j) {
        return this.neurons[i][j];
    }
    public TransferFunction getTransferFunction() {
        return this.tf;
    }
    public void connectPreviousLayer(Layer l) {
        this.previousLayer = l;
    }
    public void execute() {
        System.out.println("This layer has no swagg");
    };
}