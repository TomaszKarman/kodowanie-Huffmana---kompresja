import javafx.scene.input.KeyCode;

import javax.imageio.IIOException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-30.
 */
public class ByteFileWriter {
    public static void writeBytesToFile(String pathToFile, ArrayList<Integer> tableOfBytes) {
        byte[] byteArr = new byte[tableOfBytes.size()];

        for (int i = 0; i < tableOfBytes.size(); i++) {
            byteArr[i] = (tableOfBytes.get(i).byteValue());
        }
        try {
            FileOutputStream stream = new FileOutputStream(pathToFile);
            stream.write(byteArr);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
