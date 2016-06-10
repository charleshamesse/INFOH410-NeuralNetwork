package SimpleNet;
import NeuralNetwork.*;
import NeuralNetwork.Layers.*;
import NeuralNetwork.TransferFunctions.Sigmoid;
import NeuralNetwork.TransferFunctions.TransferFunction;
import NeuralNetwork.Utils.GrayImage;
import NeuralNetwork.Utils.Matrix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextArea mainTextArea;

    @FXML
    private LineChart<Double, Double> graph;

    @FXML
    private LineChart<Double, Double> graph2;

    @FXML
    private ScatterChart<Number, Number> matrixGraph;

    @FXML
    private HBox imageViewBox;

    @FXML
    private TextField imageViewCaption;

    ObservableList<XYChart.Series<Double, Double>> lineChartData;
    LineChart.Series<Double, Double> series1;
    
    ObservableList<XYChart.Series<Double, Double>> lineChart2Data;
    LineChart.Series<Double, Double> series2a;
    LineChart.Series<Double, Double> series2b;

    NeuralNetwork net;


    public MainController() {
    }

    public void initialize(URL url, ResourceBundle rb) {
        // Plots
        lineChartData = FXCollections.observableArrayList();
        graph.setData(lineChartData);
        graph.setTitle("Training instances");
        graph.createSymbolsProperty();
        series1 = new LineChart.Series<>();
        series1.setName("Training set");
        lineChartData.add(series1);
        // Plots 2
        lineChart2Data = FXCollections.observableArrayList();
        graph2.setData(lineChart2Data);
        graph2.setTitle("Testing instances");
        graph2.createSymbolsProperty();
        series2a = new LineChart.Series<>();
        series2a.setName("Sine wave");
        lineChart2Data.add(series2a);
        series2b = new LineChart.Series<>();
        series2b.setName("Neural sine wave");
        lineChart2Data.add(series2b);

        // NeuralNetwork
        TransferFunction tf = new Sigmoid();

        Layer[] layers = new Layer[]{
                new FullyConnectedLayer(new int[]{0, 0}, new int[]{1, 1}, tf),
                new FullyConnectedLayer(new int[]{1, 1}, new int[]{6, 6}, tf),
                new FullyConnectedLayer(new int[]{6, 6}, new int[]{6, 6}, tf),
                new FullyConnectedLayer(new int[]{6, 6}, new int[]{1, 1}, tf)
        };

        net = new NeuralNetwork(layers, 0.1);
    }
    @FXML
    private void train() {
        trainNetwork(net);
    }


    @FXML
    private void test() {
        testNetwork(net);
    }

    private void trainNetwork(NeuralNetwork net) {

		/* Learning */
        for (int i = 0; i < 250000; i++) {
            double[][] inputs = new double[1][1];
            Matrix.initMat(inputs);
            double[][] output = new double[1][1];
            double error;

            output[0][0] = generateOutput(inputs);

            error = net.backPropagate(inputs, output);
            if(i % 1000 == 0) {
                series1.getData().add(new XYChart.Data<>((double)i, error));
            }
            if(i % 10000 == 0) {
                System.out.println("Iteration " + i);
            }
        }

        mainTextArea.appendText("Learning completed!\n");

    }

    private void testNetwork(NeuralNetwork net) {

        double[][] inputs = new double[1][1];
        Matrix.initMat(inputs);
        double output = net.execute(inputs)[0][0];
        double error = Math.abs(output - inputs[0][0]);
        mainTextArea.appendText("\n\nTest:\n-Input:\t" + inputs[0][0] + "\n-Target:\t" + generateOutput(inputs) + "\n-Output:\t" + output + "\n-Error:\t" + error);

        double[][] input = new double[1][1];
        for (double i = 0; i < 6.29; i+=0.02) {
            input[0][0] = i % 1;
            series2a.getData().add(new XYChart.Data<>((double)i, generateOutput(input)));
            series2b.getData().add(new XYChart.Data<>((double)i, net.execute(input)[0][0]));

        }


    }

    private double generateOutput(double[][] input) {
        return Math.sin(input[0][0]);
    }
}
