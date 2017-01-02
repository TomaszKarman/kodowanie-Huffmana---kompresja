import java.util.ArrayList;

public class Main {

    public static DataTable DATA_TABLES;
    //public static final String PATH_TO_CONVERTED_FILE = ".\\converted.txt";
    public static final String PATH_TO_TEXT_RAW = ".\\tekst.txt";
    public static final String PATH_TO_01_FILE = ".\\converted.txt";
    public static final String PATH_TO_BYTE_FILE = ".\\convertedByte.txt";
    public static final String PATH_TO_DECODED_TO_01__FILE = ".\\decoded.txt";
    public static final String PATH_TO_DECODED_TO_BYTE__FILE = ".\\decodedByte.txt";
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
        //CHARS_DICTIONARY.showDictionary(CHARS_DICTIONARY.charAndCodes);
        //System.out.println("--->" +CHARS_DICTIONARY.charAndCodes.size());


        String convertedTo01Text = DataConverter.convertTxtTo01(txt);
        //Zapisujemy zkodowany string w pliku(01)
        FileTextWriter.saveTextToFile(PATH_TO_01_FILE, convertedTo01Text);

        Integer countOf01inText = convertedTo01Text.length();
        System.err.println("INT " + countOf01inText);

        //zapisujemy do pliku informacje o ilosci 01 do przetworzenia
        FileTextWriter.saveTextToFile(PATH_TO_DICTIONARY, countOf01inText.toString() + "\n");

        //Zapisujemy do pliku ilosc pozycji w slowniku (+1 bo enter doda nam linie w pliku tekstowym)
        // FileTextWriter.saveTextToFile(PATH_TO_BYTE_FILE, CHARS_DICTIONARY.charAndCodes.size()+1+"\n",true);

        //Zapisujemy do pliku wartosci slownika
        ;
        FileTextWriter.saveTextToFile(PATH_TO_DICTIONARY, CHARS_DICTIONARY.convertDictionaryToString(), true);

        //Zapisujemy do pliku bitowego dane z pliku TXT
        //FileTextWriter.writeBytesToFile(PATH_TO_BYTE_FILE, DataConverter.setBitTable(convertedTo01Text));
        FileTextWriter.writeBytesToFile(PATH_TO_BYTE_FILE, DataConverter.setBitTable(convertedTo01Text));

        //FileTextWriter.(PATH_TO_DECODED_TO_01__FILE, DataConverter.readBytes());


        //String dataReadedFromByteFile = DataConverter.readBytes(ReadText.getBytesFromFile(PATH_TO_BYTE_FILE));

        //FileTextWriter.saveTextToFile(PATH_TO_DECODED_TO_01__FILE, dataReadedFromByteFile);

        //Wyciagam z pliku ilosc 01 do zmiennej
        String numberOf01GetFromFile = ReadText.getVarsFromFile(PATH_TO_DICTIONARY, 0, 1);

        //System.out.println("STRING"+numberOf01GetFromFile);
        //System.out.println("STRING Length"+numberOf01GetFromFile.length());
//        for (int i = 0; i < numberOf01GetFromFile.length();i++){
//            System.out.println("Char na pozycji "+i+" to "+numberOf01GetFromFile.charAt(i));
//        }

        //Zamiana zmiennej wczytanej z pliku na wartosc Integer
        Integer numberOf01GetFromFileToInteger = ConvertStringToInt.ConvertStringIntoInt(numberOf01GetFromFile);

        //System.out.println("INT"+numberOf01GetFromFileToInteger);

        // TODO: 2016-12-31 Zrobic funkcje dla wyciagania slownika z pliku oraz danych zapisanych w bajtach


        CHARS_DICTIONARY.addCodes(ReadText.getVarsFromFile(PATH_TO_DICTIONARY, 1, 2));
        ArrayList<Integer> lsitOfBytes = ReadText.getBytesFromFile(PATH_TO_BYTE_FILE);
        lsitOfBytes.stream().forEach(integer -> System.out.println(integer));
        String rb = DataConverter.readBytes(lsitOfBytes);
        System.out.println(rb);

        //Zapisujemy do pliku koncowy tekst
        FileTextWriter.saveTextToFile(PATH_TO_DECODED_TO_BYTE__FILE, DataConverter.convertStringFrom01To_Txt(rb,numberOf01GetFromFileToInteger));


    }
}