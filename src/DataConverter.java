import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-30.
 */
public class DataConverter {


    public static String convertTxtTo01(String txt) {
        String returnedText = "";
        for (char ch : txt.toCharArray()) {
            //Konwersja char na code(01)
            returnedText += Main.CHARS_DICTIONARY.getCode(ch);
        }
        //System.err.println(returnedText);
        return returnedText;
    }

    public static byte[] setBitTable(String codedText) {
        ArrayList<Integer> bitTable = new ArrayList<>();
        while (codedText.length() % 8 != 0) {
            codedText = codedText + "1";
        }

        for (int i = 0; i < codedText.length(); ) {
            Integer tmp = 0;
            for (int j = 0; j < 8; j++) {
                // Metoda Math.pow(a, n); podnosi liczbę a do potęgi n
                if (codedText.charAt(i + j) == '1') {
                    tmp = tmp + (int) Math.pow(2, (7 - j));
                }
            }
            bitTable.add(tmp);
            i = i + 8;
        }
//        bitTable.stream().forEach(integer -> {
//            System.err.println(integer);
//        });


        return convertFromIntToByte(bitTable);
    }

    public static byte[] convertFromIntToByte(ArrayList<Integer> inte) {
        byte[] byteArr = new byte[inte.size()];

        for (int i = 0; i < inte.size(); i++) {
            byteArr[i] = (inte.get(i).byteValue());
        }
        return byteArr;
    }


    //Konwesja tablicy liczb na string 01
    public static String readBytes(ArrayList<Integer> listToDecode) {
        String returningString = "";
        for (Integer bb : listToDecode) {

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

            returningString = returningString + tempString;
        }
        //System.err.println(returningString);
        return returningString;
    }

    public static String convertStringFrom01To_Txt(String inputString, int lenghtToConvert) {
        String tmpString = Character.toString(inputString.charAt(0));

        boolean isEnded = false;
        String txtFull = "";
        System.out.println("length");
        System.out.println(lenghtToConvert);
        System.out.println(tmpString);
        for (int i = 0; i < lenghtToConvert; ) {
            while (Main.CHARS_DICTIONARY.getCharByCode(tmpString) == null) {
                System.out.println(tmpString);
                i++;
                try {
                    tmpString = tmpString + inputString.charAt(i);
                } catch (Exception e) {
//                    Wyszlismy poza zakres wiec koniec pisania bo wyrownanie ilosci bitow w bajcie
                    isEnded = true;
                    break;
                }

            }
            //Sprawdzamy czy wychopdzimy z petli
            if (!isEnded) {

                //wyszedl z petli wiec mamy nasz znak
                Character naszChar = Main.CHARS_DICTIONARY.getCharByCode(tmpString);

                //Zapis naszChar do pliku
                tmpString = "";

                txtFull = txtFull + naszChar;

            }
        }
        return txtFull;

    }

}
