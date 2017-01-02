import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by TKK on 2016-12-28.
 */
public class DataTable {


    private ArrayList<CharAndCount> charAndCount = new ArrayList<>();

    public void addCharacter(char a) {
        //Sprawdzenie czy nasza lista zawiera juz ten znak
        for (CharAndCount e : charAndCount) {
            if (e.getName().equals(Character.toString(a))) {
                //Jest juz w liscie wiec zwiekszamy counter
                e.increaseCounter();
                return;
            }
            ;
        }
        //Nie ma w liscie więc dodajemy nowy
        charAndCount.add(new CharAndCount(Character.toString(a)));
    }

    public void addTextToDataTable(String txt){

        //Iteracja po calym tekscie
        for(char ch:txt.toCharArray()){
            addCharacter(ch);
        }
    }


    /**
     * Wypisanie wszystkich wartości z tablicy
     */
    public void printCounters() {
        for (CharAndCount ch : charAndCount) {
            System.err.println("Znak :" + ch.getName() + " wystapien: " + ch.getCounter());
        }
    }

    public static void sort(ArrayList<CharAndCount> list) {
        list.sort(new Comparator<CharAndCount>() {
            @Override
            public int compare(CharAndCount o1, CharAndCount o2) {
                if (o1.getCounter() == o2.getCounter()) {
                    if (o1.isStatus() && !o2.isStatus())
                        return 1;

                if (o2.isStatus()) {
                    return -1;
                }
            }

            return o1.getCounter()-o2.getCounter();
        }
    }

    );
}

    public ArrayList<CharAndCount> getCharAndCount() {
        return charAndCount;
    }
}
