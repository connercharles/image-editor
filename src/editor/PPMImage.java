package editor;
import java.util.ArrayList;

public class PPMImage {
    private int width;
    private int height;
    private int max_color_value;
    private ArrayList<Pixel> picture;

    private static final int ACTUAL_MAX = 255;

    // Setters
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setMax_color_value(int max_color_value) {
        this.max_color_value = max_color_value;
    }
    public void setPicture(ArrayList<Pixel> picture) {
        this.picture = picture;
    }

    // Getters
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getMax_color_value() {
        return max_color_value;
    }
    public ArrayList<Pixel> getPicture() {
        return picture;
    }

    public PPMImage(){}

    public PPMImage(int width, int height, int max_color_value, ArrayList<Pixel> picture) {
        this.width = width;
        this.height = height;
        this.max_color_value = Math.min(max_color_value, ACTUAL_MAX);
        this.picture = picture;
    }



    // writer function

    // needs to print P3 first every time!
}
