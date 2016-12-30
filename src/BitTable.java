import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-30.
 */
public class BitTable {
    //int bitValue;
    ArrayList<Integer>tableOfBits = new ArrayList<>();

//    public int getBitValue() {
//        return bitValue;
//    }
//
//    public void setBitValue(int bitValue) {
//        this.bitValue = bitValue;
//    }

    public ArrayList<Integer> getTableOfBits() {
        return tableOfBits;
    }

    public ArrayList<Integer>setBitTable(String codedText) {
    System.err.println(codedText);
        ArrayList<Integer> bitTable=new ArrayList<>();
        while(codedText.length()%8 !=0){
            codedText = codedText + "1";
        }
        System.err.println(codedText);

        for (int i = 0; i < codedText.length(); ) {
            Integer tmp = 0;
            for (int j = 0; j < 8; j++) {
                // Metoda Math.pow(a, n); podnosi liczbę a do potęgi n
                if (codedText.charAt(i + j) == '1'){
                tmp = tmp + (int) Math.pow(2, (7 - j));
                }
            }
            bitTable.add(tmp);
            i = i + 8;
        }
        bitTable.stream().forEach(integer -> {
            System.err.println(integer);
        });
        return bitTable;
    }
}
