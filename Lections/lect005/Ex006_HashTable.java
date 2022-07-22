import java.util.Hashtable;
import java.util.Map;

public class Ex006_HashTable {
    public static void main(String[] args) {
        Map<Integer,String> table = new Hashtable<>();
        table.put(1, "один");
        table.put(11, "один один");
        table.put(2, "два");
        System.out.println(table); // {2=один, 1=два, 11=один один}
        // table.put(null, "ничего"); // ! java.lang.NullPointerException !
    }
}
