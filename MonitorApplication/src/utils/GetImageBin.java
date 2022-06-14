package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

/**
 *
 * @author nathee
 */
public class GetImageBin {

    private static final Logger LOGGER = Logger.getLogger(GetImageBin.class);

    public byte[] getImagePngBin(File fileSource) {
        LOGGER.debug("getImagePngBin");
        try {
            BufferedImage image = ImageIO.read(fileSource);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(image, "png", b);
            byte[] jpgByteArray = b.toByteArray();
            return jpgByteArray;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public void writeByteImage(File fileOutput, byte[] dataImage) {
        LOGGER.debug("writeByteImage");
        try {
            try ( FileOutputStream fw = new FileOutputStream(fileOutput)) {
                fw.write(dataImage);
                fw.flush();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void copyFileUsingStream(File source, File dest) {
        LOGGER.debug("copyFileUsingStream");
        try {
            InputStream is = new FileInputStream(source);
            OutputStream os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
