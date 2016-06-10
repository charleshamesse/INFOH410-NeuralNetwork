package NeuralNetwork.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by charleshamesse on 18/05/16.
 */
public class GrayImage {

    private String path;
    private BufferedImage img;
    private int[][] values;

    public GrayImage(String path) {
        this.path = path;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e);
        }
        getArray();
    }
    public GrayImage(double[][] arr) {
        this.values = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr[0].length; ++j) {
                this.values[i][j] = (int) Math.round(255*arr[i][j]);
            }
        }
    }


    public int[][] getArray() {
        int[][] arr = new int[img.getHeight()][img.getWidth()];
        int rgb, r, g, b;

        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr[0].length; j++) {
                rgb = img.getRGB(i, j);
                r = (rgb >> 16) & 0xFF;
                g = (rgb >> 8) & 0xFF;
                b = rgb & 0xFF;

                // NTSC conversion
                arr[i][j] = (int) Math.round(0.2989*r + 0.5870*g + 0.1140*b);
            }
        values = arr;
        return arr;
    }

    public BufferedImage getBufferedImage() {
        int xLength = this.values.length;
        int yLength = this.values[0].length;
        BufferedImage b = new BufferedImage(xLength, yLength, 3);
        for(int x = 0; x < xLength; x++) {
            for(int y = 0; y < yLength; y++) {
                Color gray = new Color(values[x][y], values[x][y], values[x][y]);
                b.setRGB(x, y, gray.getRGB());
            }
        }
        return b;

    }

    public int[][] subSample(int w, int h) {
        int x_length = this.values.length;
        int y_length = this.values[0].length;
        int h_pool = y_length / h;
        int w_pool = x_length / w;
        int i, j, l, m;
        int new_value;
        int[][] new_arr = new int[h][w];
        for(i = 0; i < h; i++) {
            for(j = 0; j < w; j++) {
                new_value = 0;
                for(l = 0; l < h_pool; l++)
                    for(m = 0; m < w_pool; m++)
                        if(values[h_pool*i+l][w_pool*j+m] > new_value) new_value = values[h_pool*i+l][w_pool*j+m];

                new_arr[i][j] = new_value;
            }
        }
        this.values = new_arr;
        return new_arr;
    }

    public int[][] superSample(int w, int h) {
        int x_length = this.values.length;
        int y_length = this.values[0].length;
        int h_pool = h / y_length;
        int w_pool = w / x_length;
        int i, j, l, m;
        int new_value;
        int[][] new_arr = new int[h][w];
        for(i = 0; i < h; i++) {
            for(j = 0; j < w; j++) {
                l = (int) Math.floor(i / h_pool);
                m = (int) Math.floor(j / w_pool);
                new_arr[i][j] = values[l][m];
            }
        }
        this.values = new_arr;
        return new_arr;
    }

}
