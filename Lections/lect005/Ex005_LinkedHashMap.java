import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ex005_LinkedHashMap {
    public static void main(String[] args) {
        Map<Integer,String> linkmap = new LinkedHashMap<>();
        linkmap.put(11, "один один");
        linkmap.put(1, "два");
        linkmap.put(2, "один");
        System.out.println(linkmap); // {11=один один, 1=два, 2=один}
        linkmap.remove(1); // удаляем пару с ключом 1
        System.out.println(linkmap); // {11=один один, 2=один}
        Map<Integer,String> map = new HashMap<>();
        map.put(11, "один один");
        map.put(2, "два");
        map.put(1, "один");
        System.out.println(map); // {1=один, 2=два, 11=один один}
        map.remove(2); // удаляем пару с ключом 2
        System.out.println(map); // {1=один, 11=один один}
    }
}
