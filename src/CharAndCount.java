/**
 * Created by TKK on 2016-12-28.
 */
public class CharAndCount {
    private String name;
    private int counter;
    private boolean status;

    public CharAndCount(String name) {
        this.name = name;
        this.counter =1;
        this.status = false;
    }
    public CharAndCount(String name, int counter) {
        this.name = name;
        this.counter = counter;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isStatus() {
        return status;
    }

    public void increaseCounter(){
        counter++;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
