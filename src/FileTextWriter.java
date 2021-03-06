import java.io.*;
import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-30.
 */
public class FileTextWriter {

    public static void saveTextToFile(String pathToFile, String text) {

        saveTextToFile(pathToFile,text,false);

    }



    public static void saveTextToFile(String pathToFile, String text, boolean yesOrNo) {


        try {
            //Otwarcie pliku
            File textFile = new File(pathToFile);
            FileWriter fw = new FileWriter(textFile, yesOrNo);
            BufferedWriter printWriter = new BufferedWriter(fw);


            //Zapis tekstu do pliku
            printWriter.write(text);
            printWriter.flush();
            //zamykamy zeby dalo sie otworzyc z zewntrz
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeBytesToFile(String pathToFile, byte [] tableOfBytes) {

        try {
            FileOutputStream stream = new FileOutputStream(pathToFile);
            stream.write(tableOfBytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBytesToFile(String pathToFile, byte [] tableOfBytes, boolean yesOrNo) {

        try {
            FileOutputStream stream = new FileOutputStream(pathToFile, yesOrNo);
            stream.write(tableOfBytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
