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
        }
        return null;
    }

    //Chcemy czytac plik od konkretnej linijki
    public static String getVarsFromFile(String pathToFile, int numberOfStartLine, int numberOfEndLine) {

        String txtLine;
        String fullText = "";
        BufferedReader bf = null;

        //boolean secondRound = false;
        try {
            bf = openBufferedReader(pathToFile);
            for (int i = 0; i < numberOfEndLine; i++) {
                // if (i == numberOfStartLine){
                //Getting each line in config file
                txtLine = bf.readLine();
                if (i >= numberOfStartLine) {
                    System.err.println(txtLine);
//                    if (txtLine ) {
//                        txtLine=Character.toString('\n');
//                    }
                    fullText += txtLine;
                }

                //secondRound = true;
            }
            bf.close();
            return fullText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // funkcja wyciagajaca z pliku wartosci bitow
    public static ArrayList<Integer> getBytesFromFile(String pathToFile) {
        try {
            ArrayList<Integer> dataInt = new ArrayList<>();
            byte[] data = Files.readAllBytes(Paths.get(pathToFile));
            //System.out.println("bity");
            for (Byte by : data) {
               // System.out.println(Byte.toUnsignedInt(by));
                dataInt.add(Byte.toUnsignedInt(by));
            }
           // System.out.println("po bity");
            return dataInt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: 2016-12-31 Zrobic funkcje czytajaca bity od konkretnej lini (chyba zawsze 4 linia) 
    public static ArrayList<Integer> getBytesFromFile(String pathToFile, int lineToStart) {
        try {
            ArrayList<Integer> dataInt = new ArrayList<>();
            byte[] data = Files.readAllBytes(Paths.get(pathToFile));
            for (Byte by : data) {
                dataInt.add(Byte.toUnsignedInt(by));
                //System.err.println(Byte.toUnsignedInt(by));
            }
            return dataInt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
