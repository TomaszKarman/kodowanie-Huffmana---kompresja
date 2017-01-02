/**
 * Created by TKK on 2016-12-31.
 */
public class ConvertStringToInt {

    public static Integer intValue = 0;

    public static int ConvertStringIntoInt (String value){
        for (int i=0; i < value.length(); i++){
            char chara = value.charAt(value.length()-1-i);
            int x = ((int)chara-48) * (int)Math.pow(10,i);
            intValue = intValue + x;
            x= 0;
        }
        return intValue;
    }

}
