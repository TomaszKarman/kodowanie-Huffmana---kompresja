import java.util.ArrayList;

public class Main {

    public static DataTable DATA_TABLES;
    public static final String PATH_TO_DECODED_TO_TXT_FILE = ".\\decoded_to_txt.txt";
    public static final String PATH_TO_TEXT_RAW = ".\\tekst.txt";
    public static final String PATH_TO_01_FILE = ".\\file_01.txt";
    public static final String PATH_TO_BYTE_FILE = ".\\Byte.txt";
    //public static final String PATH_TO_DECODED_TO_01__FILE = ".\\decoded.txt";
    //public static final String PATH_TO_DECODED_TO_BYTE__FILE = ".\\decodedByte.txt";
    public static final String PATH_TO_DICTIONARY = ".\\dictionary.txt";


    public static CharsDictionaryToCode CHARS_DICTIONARY;

    public static void main(String[] args) {

        DATA_TABLES = new DataTable();
        CHARS_DICTIONARY = new CharsDictionaryToCode();

        //Wyciagamy tekst który chcemy konwertować
        String txt = ReadText.getTextFromFile(PATH_TO_TEXT_RAW);

        //Zliczmay wystapienia literek w tekscie
        DATA_TABLES.addTextToDataTable(txt);

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

        Integer countOf01inText = convertedTo01Text.length();

        //zapisujemy do pliku (slownika) informacje o ilosci 01 do przetworzenia
        FileTextWriter.saveTextToFile(PATH_TO_DICTIONARY, countOf01inText.toString() + "\n");

        //Zapisujemy do pliku wartosci slownika
        FileTextWriter.saveTextToFile(PATH_TO_DICTIONARY, CHARS_DICTIONARY.convertDictionaryToString(), true);



        //Zapisujemy do pliku bitowego dane z pliku TXT
        FileTextWriter.writeBytesToFile(PATH_TO_BYTE_FILE, DataConverter.setBitTable(convertedTo01Text));


        /*
        FileTextWriter.writeBytesToFile(PATH_TO_DICTIONARY, DataConverter.setBitTable(convertedTo01Text), true);
        */


        //FileTextWriter.(PATH_TO_DECODED_TO_01__FILE, DataConverter.readBytes());


        //String dataReadedFromByteFile = DataConverter.readBytes(ReadText.getBytesFromFile(PATH_TO_BYTE_FILE));

        //FileTextWriter.saveTextToFile(PATH_TO_DECODED_TO_01__FILE, dataReadedFromByteFile);

        //Wyciagam z pliku ilosc 01 do zmiennej
        String numberOf01GetFromFile = ReadText.getVarsFromFile(PATH_TO_DICTIONARY, 0, 1);

        //Zamiana zmiennej wczytanej z pliku na wartosc Integer
        Integer numberOf01GetFromFileToInteger = ConvertStringToInt.ConvertStringIntoInt(numberOf01GetFromFile);

        //Wczytujemy z pliku pozycje do slownika
        CHARS_DICTIONARY.addCodes(ReadText.getVarsFromFile(PATH_TO_DICTIONARY, 1, 2));

        /*
        String sameBity = ReadText.getVarsFromFile(PATH_TO_DICTIONARY, 2, 3);
        */

        //Dekodujemy bajty zapisane w pliku na Int
        ArrayList<Integer> listOfBytes = ReadText.getBytesFromFile(PATH_TO_BYTE_FILE);
        //listOfBytes.stream().forEach(integer -> System.out.println(integer));

        /*
        ArrayList<Integer> listOfBytes = ReadText.getBytesFromFile(PATH_TO_DICTIONARY,3);
        */

/*
        FileTextWriter.saveTextToFile(".\\samebity.txt", sameBity);
        ArrayList<Integer> listOfBytes = ReadText.getBytesFromFile(".\\samebity.txt");
*/
        String rb = DataConverter.readBytes(listOfBytes);
        //System.out.println(rb);

        //Zapisujemy do pliku koncowy tekst (najpierw tworzymy 01 potem txt w 1 kroku
        FileTextWriter.saveTextToFile(PATH_TO_DECODED_TO_TXT_FILE, DataConverter.convertStringFrom01To_Txt(rb,numberOf01GetFromFileToInteger));


    }
}