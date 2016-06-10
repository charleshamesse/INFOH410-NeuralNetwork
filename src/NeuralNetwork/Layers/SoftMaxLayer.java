package NeuralNetwork.Layers;

/**
 * Created by charleshamesse on 16/05/16.
 */
public class SoftMaxLayer {

    /*
    class SoftmaxLayer(object):

    def __init__(self, n_in, n_out, p_dropout=0.0):
    self.n_in = n_in
    self.n_out = n_out
    self.p_dropout = p_dropout
    # Initialize weights and biases
    self.w = theano.shared(
            np.zeros((n_in, n_out), dtype=theano.config.floatX),
    name='w', borrow=True)
    self.b = theano.shared(
            np.zeros((n_out,), dtype=theano.config.floatX),
    name='b', borrow=True)
    self.params = [self.w, self.b]

    def set_inpt(self, inpt, inpt_dropout, mini_batch_size):
    self.inpt = inpt.reshape((mini_batch_size, self.n_in))
    self.output = softmax((1-self.p_dropout)*T.dot(self.inpt, self.w) + self.b)
    self.y_out = T.argmax(self.output, axis=1)
    self.inpt_dropout = dropout_layer(
            inpt_dropout.reshape((mini_batch_size, self.n_in)), self.p_dropout)
    self.output_dropout = softmax(T.dot(self.inpt_dropout, self.w) + self.b)

    def cost(self, net):
            "Return the log-likelihood cost."
            return -T.mean(T.log(self.output_dropout)[T.arange(net.y.shape[0]), net.y])

    def accuracy(self, y):
            "Return the accuracy for the mini-batch."
            return T.mean(T.eq(y, self.y_out))

    */

}
