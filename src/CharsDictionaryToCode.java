import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-29.
 */
public class CharsDictionaryToCode {


    private static final String CHAR_CODE_SEPARATOR = "|";

    private static final String NEXT_CHAR_SEPARATOR = "&";

    public class CharAndCode {
        char character;
        String code;

        public CharAndCode(char character, String code) {
            this.character = character;
            this.code = code;
        }

        public char getCharacter() {
            return character;
        }

        public String getCode() {
            return code;
        }
    }

    ArrayList<CharAndCode> charAndCodes = new ArrayList<>();

    public void addCharAndCode(char newChar, String code) {
        charAndCodes.add(new CharAndCode(newChar, code));
    }

    public String getCode(char tmpChar) {
        for (CharAndCode cc : charAndCodes) {
            if (cc.getCharacter()==tmpChar)
                return cc.getCode();
        }
        return null;

    }

    public Character getCharByCode(String code) {
        for (CharAndCode cc : charAndCodes) {
            if (cc.getCode().equals(code)) {
                //System.out.println("zgadza sie");
                return cc.getCharacter();
            }
        }

        return null;
    }

    public void showDictionary() {
        for (CharAndCode cc : charAndCodes) {
            System.out.println(cc.getCharacter() + CHAR_CODE_SEPARATOR + cc.getCode().toString());
        }
    }

    public String convertDictionaryToString() {
        //showDictionary();
        String returnedTxt = "";

        for (CharAndCode cc : charAndCodes) {
            String txt;
            if (cc.getCharacter() == '\n') {
                txt = "" + CHAR_CODE_SEPARATOR + cc.getCode().toString() + NEXT_CHAR_SEPARATOR;
            }
            else {
                txt = cc.getCharacter() + CHAR_CODE_SEPARATOR + cc.getCode().toString() + NEXT_CHAR_SEPARATOR;
            }
            returnedTxt = returnedTxt + txt;
        }
        returnedTxt=returnedTxt+'\n';
        return returnedTxt;
    }


    public void addCodes(String dictionaryText) {
        charAndCodes=new ArrayList<>();
        for (int i = 0; i < dictionaryText.length()-1; i++) {
            char chatToAdd;

            if (Character.toString(dictionaryText.charAt(i)).equals(CHAR_CODE_SEPARATOR)) {
                if (Character.toString(dictionaryText.charAt(i+1)).equals(CHAR_CODE_SEPARATOR)) {
                    chatToAdd = dictionaryText.charAt(i);
                    i = i + 2;
                } else {
                    chatToAdd = '\n';
                    i = i + 1;
                }
            } else {
                chatToAdd = dictionaryText.charAt(i);
                i = i + 2;
            }

            String code = "";
            while (!(Character.toString(dictionaryText.charAt(i)).equals(NEXT_CHAR_SEPARATOR))) {
                code = code + dictionaryText.charAt(i);
                i++;
            }
            //System.out.println("char : "+chatToAdd + " code: " + code);
            addCharAndCode(chatToAdd, code);
        }
    }


}
