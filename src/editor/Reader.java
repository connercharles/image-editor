package editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private PPMImage image;

    public PPMImage getImage() {
        return image;
    }

    public Reader() {
        image = new PPMImage();
    }

    // reads the PPM file and generates a PPMImage
    public void processFile(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");
        // skip header and get width, height, max value
        //**** assuming that we will never get a blank file!
        scanner.next();
        String width = scanner.next();
        String height = scanner.next();
        String max_color = scanner.next();

        // For holding data
        ArrayList<Pixel> picture = new ArrayList<Pixel>();

        //**** assuming it will not be incomplete (will have at least a pixel)
        while(scanner.hasNext()) {
            String str = scanner.next();
            int red = Integer.parseInt(str);
            str = scanner.next();
            int green = Integer.parseInt(str);
            str = scanner.next();
            int blue = Integer.parseInt(str);
            // Create Pixel
            Pixel current = new Pixel(red, green, blue);
            // Add to Array
            picture.add(current);
        }

        // set everything
        image.setWidth(Integer.parseInt(width));
        image.setHeight(Integer.parseInt(height));
        image.setMax_color_value(Integer.parseInt(max_color));
        image.setPicture(picture);
    }

}
