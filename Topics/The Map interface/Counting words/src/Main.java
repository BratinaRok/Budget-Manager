import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        // write your code here
        SortedMap<String, Integer> count = new TreeMap<>();

        for (String i : strings) {
            Integer j = count.get(i);
            count.put(i, (j == null) ? 1 : j + 1);

        }

        return count;
    }

    public static void printMap(Map<String, Integer> map) {
        // write your code here
        for (Map.Entry<String, Integer> val : map.entrySet()) {
            System.out.println(val.getKey() + " : " + val.getValue());
        }
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}