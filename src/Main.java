import java.io.FileWriter;
import java.util.ArrayList;

public class Main {

    public static DataTable DATA_TABLES;
    public static final String PATH_TO_CONVERTED_FILE = ".\\converted.txt";

    public static CharsDictionaryToCode CHARS_DICTIONARY;

    public static void main(String[] args) {

        DATA_TABLES = new DataTable();
        CHARS_DICTIONARY = new CharsDictionaryToCode();
        TekstReader tr = new TekstReader();
        tr.readFile();

        //DATA_TABLES.printCounters();
        DATA_TABLES.sort(DATA_TABLES.getCharAndCount());

        //utwprzenie tablicy dla charow z ich kodami wyciagnietymi z drzewa
        importCodes();


//        Wypisanie tablicy kodow
//        for (CharAndCount nn : DATA_TABLES.getCharAndCount()) {
//            System.err.println("Znak : " + nn.getName() + " kod: " + CHARS_DICTIONARY.getCode(nn.getName().charAt(0)));
//        }


        //Zapis zakodowanego pliku
        BitTable bitTable = new BitTable();

        ByteFileWriter.writeBytesToFile(".\\convertedByte.txt", bitTable.setBitTable(tr.getCodedTextFromFile()));

        //tr.getTextFromFile(".\\convertedByte.txt");

        //System.out.println(tr.getTextFromFile(".\\convertedByte.txt"));


        //tutaj czytalismy plik
        MyFileWriter newFw = new MyFileWriter(".\\decodedByte.txt");


        //Tutaj zapis do pliku


        //TODO do codedString trzeba wpisać string na podstawie ciagu bajtów z zakodowanego pliku
        String codedString = readBytes(tr.getBytesFromFile(".\\convertedByte.txt"));
        System.err.println(tr.getTextFromFile(".\\convertedByte.txt"));

        String tmpString = Character.toString(codedString.charAt(0));

        boolean isEnded = false;
        for (int i = 0; i < codedString.length(); ) {

            while (CHARS_DICTIONARY.getCharByCode(tmpString) == null) {
                i++;
                try {
                    tmpString = tmpString + Character.toString(codedString.charAt(i));
                } catch (Exception e) {
                    //Wyszlismy poza zakres wiec koniec pisania bo wyrownanie ilosci bitow w bajcie
                    isEnded = true;
                    break;
                }
            }
            //wyszedl z petli wiec mamy nasz znak
            Character naszChar = CHARS_DICTIONARY.getCharByCode(tmpString);

            //Zapis naszChar do pliku
            if (!isEnded) newFw.addStringToFile(Character.toString(naszChar));
            tmpString = "";
        }

    newFw.close();
    }


    public static void importCodes() {
        Tree tree = new Tree();

        tree.budujDrzewo(DATA_TABLES.getCharAndCount());
        for (CharAndCount nn : DATA_TABLES.getCharAndCount()) {
            CHARS_DICTIONARY.addCharAndCode(nn.getName().charAt(0), tree.getCodeByChar(nn.getName().charAt(0)));

        }
    }

    public static String readBytes(ArrayList<Integer> listToDecode) {
        System.err.println("konwerruje");
        String returningString = "";
        for (Integer bb : listToDecode) {
            System.err.println(bb);
            String tempString = "";
            while (bb > 0) {
                if (bb % 2 == 1) {
                    tempString = "1" + tempString;
                } else {
                    tempString = "0" + tempString;
                }
                bb = bb / 2;
            }
            while (tempString.length() % 8 != 0) {
                tempString = "0" + tempString;
            }
            //System.err.println(bb);
            //System.err.println((int)bb);
            //System.err.println(tempString);
            returningString = returningString + tempString;
        }

        //System.err.println(returningString);

        return returningString;
    }


}
