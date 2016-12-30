import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-28.
 */
public class TekstReader {

    private final static String filePath = ".\\tekst.txt";

    /**
     * Set defined parameters in class or add them to "anonymous" hashmap
     *
     * @param bf BufferedFile
     */
    private void readFile(BufferedReader bf) throws IOException {

        String txtLine;
        boolean secondRound = false;
        //Getting each line in config file
        while ((txtLine = bf.readLine()) != null) {
            try {
                //Dodanie entera jesli kolejna linijka

                if (secondRound)
                    Main.DATA_TABLES.addCharacter('\n');
                decodeLine(txtLine);
                secondRound = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bf.close();

    }

    private void decodeLine(String line) {
        //tu mamy cala linie

        //Dodajmy znaki z lini do tablicy wystapien
        for (int i = 0; i < line.length(); i++) {
            Main.DATA_TABLES.addCharacter(line.charAt(i));
        }


    }



    private String readFileReturningTxt(BufferedReader bf) throws IOException {

        String txtLine;
        String returnedTxt="";
        boolean secondRound = false;
        //Getting each line in config file
        while ((txtLine = bf.readLine()) != null) {
            try {
                //Dodanie entera jesli kolejna linijka
                if (secondRound) {
                    returnedTxt = returnedTxt + '\n';
                }
                returnedTxt=returnedTxt.concat(txtLine);

                secondRound=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bf.close();
        return returnedTxt;

    }

    public String getTextFromFile(String pathFile){

        try {

            //Open buffered file
            BufferedReader bf = openBufferedReader(pathFile);

            //Set parameters
            return readFileReturningTxt(bf);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void readFile() {
        try {
            readFileAA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read and set parameters by configFile by AppName
     */
    private void readFileAA() throws Exception, IOException {

        //Open buffered file
        BufferedReader bf = openBufferedReader(filePath);

        //Set parameters
        readFile(bf);

    }

    private BufferedReader openBufferedReader(String filePath) throws Exception {

        //Open conf File
        File confFile = new File(filePath);
        //everything ok, open buffered Reader
        return new BufferedReader(new FileReader(confFile));
    }


    public String getCodedTextFromFile() {
        try {

            BufferedReader bf = openBufferedReader(filePath);
            return readFileAndCodeBytes(bf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Set defined parameters in class or add them to "anonymous" hashmap
     *
     * @param bf BufferedFile
     */
    private String readFileAndCode(BufferedReader bf) throws IOException {
        String codedText = "";
        String txtLine;
        boolean secondRound = false;
        //Getting each line in config file
        MyFileWriter fw = new MyFileWriter(Main.PATH_TO_CONVERTED_FILE);
        while ((txtLine = bf.readLine()) != null) {
            try {
                if (secondRound) {
                    codeLine(fw, Character.toString('\n'));
                }
                codeLine(fw, txtLine);
                secondRound = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bf.close();
        //fw.close();
        return codedText;
    }


    private void codeLine(MyFileWriter fw, String line) {
        for (int i = 0; i < line.length(); i++) {
            fw.addStringToFile(Main.CHARS_DICTIONARY.getCode(line.charAt(i)));
        }
    }


    public String getCodedString() {
        String txt = "";
        try {
            BufferedReader bf = openBufferedReader(Main.PATH_TO_CONVERTED_FILE);
            txt = getCodedString(bf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return txt;
    }

    private String getCodedString(BufferedReader bf) throws IOException {
        String returnedString = "";
        String txtLine = "";
        while ((txtLine = bf.readLine()) != null) {
            try {
                returnedString = returnedString + txtLine;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bf.close();
        return returnedString;

    }


    private String readFileAndCodeBytes(BufferedReader bf) throws IOException {
        String codedText = "";
        String txtLine;
        boolean secondRound = false;
        //Getting each line in config file
       // MyFileWriter fw = new MyFileWriter(Main.PATH_TO_CONVERTED_FILE);
        while ((txtLine = bf.readLine()) != null) {
            try {
                if (secondRound) {
                    codedText += Main.CHARS_DICTIONARY.getCode('\n');
                }
                for (int i = 0; i < txtLine.length(); i++) {
                    codedText += Main.CHARS_DICTIONARY.getCode(txtLine.charAt(i));
                }
                secondRound = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bf.close();
        return  codedText;
    }

    // funkcja wyciagajaca z pliku wartosci bytow
    public ArrayList<Integer> getBytesFromFile (String pathToFile){

        System.err.println("Czytam bajty");
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



    //W koded text mamy 0 i 1 odpowiadajace przekonwertowanemu tekstowi
    //System.err.println(codedText);

/*
 //metoda do wypelnienia tabeli bitow - przenioslem do nowej klasy
 public void setBitTable(String codedText, ArrayList<Integer> BitTable) {
        int z = 0;
        for (int i = 0; i < codedText.length(); ) {
            Integer tmp = 0;
            for (int j = 0; j < 8; j++) {
                // Metoda Math.pow(a, n); podnosi liczbę a do potęgi n
                tmp = tmp + (int) Math.pow(2, (7 - j)) * (int) codedText.indexOf(i + j);

            }
            BitTable.add(tmp);
            i = i + 8;

            z++;
        }

    }*/
}
