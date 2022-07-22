import java.util.HashMap;
import java.util.Map;

public class Ex002_HashMapEntry {
    public static void main(String[] args) {
        System.out.print("\033[H\033[J");
        Map<Integer, String> db = new HashMap<>();
        System.out.println(db.putIfAbsent(1, "один"));
        db.put(2, "два");
        db.put(3, "три");
        System.out.println(db); 

        for (var item : db.entrySet()) {
            System.out.printf("[%d: %s]\n", item.getKey(), item.getValue());
        }
    }
}