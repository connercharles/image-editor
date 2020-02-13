package editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageEditor {

    // java ImageEditor [0]inputFileName [1]outputFileName [2]{grayscale|invert|emboss|motionblur blurLength} [3]
    public static void main(String args[]){
        // read in file and data
        File inputFile = new File(args[0]);
        PPMImage data = new PPMImage();
        Reader readFile = new Reader();
        try{
            readFile.processFile(inputFile);
            data = readFile.getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // make transformation
        switch(args[2])
        {
            case "grayscale":
                grayscaleImage(data);
                break;
            case "invert":
                invertImage(data);
                break;
            case "emboss":
                embossImage(data);
                break;
            case "motionblur":
                blurImage(data, Integer.parseInt(args[3]));
                break;
            default:
                break;
        }

        // export file
        File outputFile = new File(args[1]);
        Writer writeFile = new Writer(data);
        try{
            writeFile.exportFile(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Transformation functions

    // Grayscale
    public static void grayscaleImage(PPMImage image) {
        // get data
        ArrayList<Pixel> temp = image.getPicture();

        // change all Pixels
        for (Pixel cur : temp) {
            // Average of three pixel values
            int average = (cur.getRed() + cur.getGreen() + cur.getBlue()) / 3;

            cur.setRed(average);
            cur.setGreen(average);
            cur.setBlue(average);
        }

        // put data back
        image.setPicture(temp);
    }

    // Invert - take every pixel num and replace the inverse
    public static void invertImage(PPMImage image) {
        // get data
        ArrayList<Pixel> temp = image.getPicture();
        int max_num = image.getMax_color_value();

        // change all Pixels
        for (Pixel cur : temp) {
            // max pixel value - current number
            cur.setRed(max_num - cur.getRed());
            cur.setGreen(max_num - cur.getGreen());
            cur.setBlue(max_num - cur.getBlue());
        }

        // put data back
        image.setPicture(temp);
    }

    // Emboss
    public static void embossImage(PPMImage image) {
        // get data
        ArrayList<Pixel> old = image.getPicture();
        ArrayList<Pixel> newPicture = new ArrayList<Pixel>();
        int width = image.getWidth();

        // change all Pixels
        for (int i = 0; i < old.size(); i++) {
            // check if top row
            if (i < width || ((i % width) == 0) || i == 0) // check if left column or top row or top corner
            {
                Pixel newPixel = new Pixel(128, 128, 128);
                newPicture.add(newPixel);
            } else // default to any other part of matrix -(w+1)
            {
                int upperLeft = i - (width + 1);
                // find diff b/t each color
                int redDiff = old.get(i).getRed() - old.get(upperLeft).getRed();
                int greenDiff = old.get(i).getGreen() - old.get(upperLeft).getGreen();
                int blueDiff = old.get(i).getBlue() - old.get(upperLeft).getBlue();

                // check which is the greatest distance
                int max = Math.max(Math.max(Math.abs(redDiff), Math.abs(greenDiff)), Math.abs(blueDiff));
                // switch back to correct sign
                if (max == redDiff || max == -redDiff) {
                    max = redDiff;
                } else if (max == greenDiff || max == -greenDiff) {
                    max = greenDiff;
                } else if (max == blueDiff || max == -blueDiff) {
                    max = blueDiff;
                }

                // bound check
                int finalValue = max + 128;
                if (finalValue < 0) {
                    finalValue = 0;
                } else if (finalValue > 255) {
                    finalValue = 255;
                }

                // set all to that
                Pixel newPixel = new Pixel(finalValue, finalValue, finalValue);
                newPicture.add(newPixel);
            }
        }
        // put data back
        image.setPicture(newPicture);
    }

    // Blur
    public static void blurImage(PPMImage image, int blurLength) {
        // get data
        ArrayList<Pixel> old = image.getPicture();
        ArrayList<Pixel> newPicture = new ArrayList<Pixel>();
        int width = image.getWidth();

        // ASSUMING length IS GREATER THAN ZERO

        // if blurlength is 1 no need to waste time doing nothing
        if (blurLength == 1) { return; }

        // change all Pixels
        for (int i = 0; i < old.size(); i++) {
            // get average from i to blurLength
            int redSum = 0;
            int greenSum = 0;
            int blueSum = 0;
            int counter = 1; // b/c it'll break before it can count last one

            for (int j = i; j < (i + blurLength); j++, counter++) {
                // add to sums
                redSum += old.get(j).getRed();
                greenSum += old.get(j).getGreen();
                blueSum += old.get(j).getBlue();

                // check if end of the line
                if (((j + 1) % width) == 0) { break; }
            }

            if (counter > blurLength) { counter = blurLength; }

            // get average of pixel colors
            int avgRed = redSum / counter;
            int avgGreen = greenSum / counter;
            int avgBlue = blueSum / counter;

            // make new data and add it
            Pixel newPixel = new Pixel(avgRed, avgGreen, avgBlue);
            newPicture.add(newPixel);
        }

        // put data back
        image.setPicture(newPicture);
    }
}

