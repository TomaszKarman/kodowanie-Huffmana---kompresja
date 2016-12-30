import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-30.
 */
public class ReadText {


    // otwieramy plik tekstowy z ktorego chcemy czytac
    private static BufferedReader openBufferedReader(String filePath) throws Exception {

        //Open conf File
        File confFile = new File(filePath);
        //everything ok, open buffered Reader
        return new BufferedReader(new FileReader(confFile));
    }


    // wyciagamy tekst z otwartego pliku do stringa (bedzie baaaardzo duzy)
    public static String getTextFromFile(String pathToFile) {
        String txtLine;
        String fullText = "";
        boolean secondRound = false;
        BufferedReader bf = null;
        try {
            bf = openBufferedReader(pathToFile);
            //Getting each line in config file
            while ((txtLine = bf.readLine()) != null) {

                //Dodanie entera jesli kolejna linijka
                if (secondRound) {
                    fullText += '\n';
                }

                fullText += txtLine;

                secondRound = true;
            }
            bf.close();
            return fullText;
        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }


    // funkcja wyciagajaca z pliku wartosci bitow
    public static ArrayList<Integer> getBytesFromFile (String pathToFile){
        try{
            ArrayList<Integer> dataInt = new ArrayList<>();
            byte[] data = Files.readAllBytes(Paths.get(pathToFile));
            for(Byte by : data){
                dataInt.add(Byte.toUnsignedInt(by));
                System.err.println(Byte.toUnsignedInt(by));
            }
            return dataInt;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
