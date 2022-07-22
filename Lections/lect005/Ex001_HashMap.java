import java.util.HashMap;
import java.util.Map;
 
public class Ex001_HashMap {
    public static void main(String[] args) {
        System.out.print("\033[H\033[J");
        Map<Integer, String> db = new HashMap<>();
        db.putIfAbsent(1, "один"); 
        db.put(2, "два"); 
        db.put(null, "!null"); 
        System.out.println(db); // {null=!null, 1=один, 2=два}
        System.out.println(db.get(44)); // null
        db.remove(null); 
        System.out.println(db); // {1=один, 2=два}
        System.out.println(db.containsValue("один")); // true
        System.out.println(db.containsValue(1)); // false
        System.out.println(db.containsKey("один")); // false
        System.out.println(db.containsKey(1)); // true
        System.out.println(db.keySet()); // коллекция всех ключей - [1, 2]
        System.out.println(db.values()); // коллекция всез значений - [один, два]

        Integer a = 123;
		System.out.println(a.hashCode());
		System.out.println("z".hashCode());
    }
}