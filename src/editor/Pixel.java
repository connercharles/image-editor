package editor;


public class Pixel {
    private int red;
    private int green;
    private int blue;

    private final static int MAX_COLOR_VALUE = 255;

    public Pixel(int red, int green, int blue) {
        // make sure it doesn't exceed the max
        this.red = Math.min(red, MAX_COLOR_VALUE);
        this.green = Math.min(green, MAX_COLOR_VALUE);
        this.blue = Math.min(blue, MAX_COLOR_VALUE);
    }

    public void setRed(int red) { this.red = red; }
    public void setGreen(int green) { this.green = green; }
    public void setBlue(int blue) { this.blue = blue; }
    public int getRed() { return red; }
    public int getGreen() { return green; }
    public int getBlue() { return blue; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return red == pixel.red &&
                green == pixel.green &&
                blue == pixel.blue;
    }
    @Override
    public String toString() {
        return "(" +
                "r=" + red +
                ", g=" + green +
                ", b=" + blue +
                ')';
    }

    // returns string of "red green blue"
    public String output() {
        return Integer.toString(red) + " " + Integer.toString(green) + " " + Integer.toString(blue);
    }

}
