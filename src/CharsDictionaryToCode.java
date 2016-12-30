import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-29.
 */
public class CharsDictionaryToCode {

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

    public void addCharAndCode (char newChar,String code){
        charAndCodes.add(new CharAndCode(newChar,code));
    }

    public String getCode (char tmpChar){
        for (CharAndCode cc : charAndCodes){
            if (cc.getCharacter()== tmpChar)
                return cc.getCode();
        }
        return null;

    }

    public Character getCharByCode(String code){
        for (CharAndCode cc : charAndCodes){

            if (cc.getCode().equals(code))
                return cc.getCharacter();
        }

        return null;
    }

}
