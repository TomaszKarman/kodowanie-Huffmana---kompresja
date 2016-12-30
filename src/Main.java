import java.util.ArrayList;

public class Main {

    public static DataTable DATA_TABLES;
    //public static final String PATH_TO_CONVERTED_FILE = ".\\converted.txt";
    public static final String PATH_TO_TEXT_RAW = ".\\tekst.txt";
    public static final String PATH_TO_01_FILE = ".\\converted.txt";
    public static final String PATH_TO_BYTE_FILE = ".\\convertedByte.txt";
    public static final String PATH_TO_DECODED_TO_01__FILE = ".\\decoded.txt";
    public static final String PATH_TO_DECODED_TO_BYTE__FILE = ".\\decodedByte.txt";


    public static CharsDictionaryToCode CHARS_DICTIONARY;

    public static void main(String[] args) {

        DATA_TABLES = new DataTable();
        CHARS_DICTIONARY = new CharsDictionaryToCode();

        //Wyciagamy tekst który chcemy konwertować
        String txt = ReadText.getTextFromFile(PATH_TO_TEXT_RAW);

        //Zliczmay wystapienia literek w tekscie
        DATA_TABLES.addTextToToDataTable(txt);

        //Budujemy drzewo na podstawie wystapien charow
        Tree tree = new Tree();
        tree.buildTree(DATA_TABLES.getCharAndCount());

        //Dodajemy wartyości do słownika
        for (CharAndCount nn : DATA_TABLES.getCharAndCount()) {
            CHARS_DICTIONARY.addCharAndCode(nn.getName().charAt(0), tree.getCodeByChar(nn.getName().charAt(0)));
        }


        String convertedTo01Text = DataConverter.convertTxtTo01(txt);
        //Zapisujemy zkodowany string w pliku(01)
        FileTextWriter.saveTextToFile(PATH_TO_01_FILE, convertedTo01Text);


        //Zapisujemy do pliku bitowego dane z pliku TXT
        FileTextWriter.writeBytesToFile(PATH_TO_BYTE_FILE, DataConverter.setBitTable(convertedTo01Text));


        String wczytanyZplikubitpwego = DataConverter.readBytes(ReadText.getBytesFromFile(PATH_TO_BYTE_FILE));


        FileTextWriter.saveTextToFile(PATH_TO_DECODED_TO_BYTE__FILE, DataConverter.convertStringFrom01To_Txt(wczytanyZplikubitpwego));
    }
}