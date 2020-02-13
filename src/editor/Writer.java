package editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Writer {
    private PPMImage image;

    public void setImage(PPMImage image) {
        this.image = image;
    }

    public Writer(PPMImage image){
        this.image = image;
    }


    // Write out to a file
    public void exportFile(File file) throws IOException {
        // make sure the file is there or make a new one?
        FileWriter fw = new FileWriter(file);

        fw.write("P3 ");
        fw.write(image.getWidth() + " ");
        fw.write(image.getHeight() + " ");
        fw.write(image.getMax_color_value() + " ");
        fw.write("\n");

        ArrayList<Pixel> pixelList = image.getPicture();

        // write all Pixels
        for (Pixel cur : pixelList) {
            fw.write(cur.output());
            fw.write(" ");
        }

        fw.close();
    }
}
