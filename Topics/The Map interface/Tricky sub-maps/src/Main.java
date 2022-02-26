import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        // Write your code here


        Map.Entry<Integer, String> entry = map.entrySet().iterator().next();
        int firstKey = entry.getKey();

        TreeMap<Integer, String> toTreeMap = new TreeMap<>(map);
        int lastKey = toTreeMap.lastKey();

        Map<Integer, String> reverseMap = new TreeMap<>(Collections.reverseOrder());
        if (firstKey % 2 != 0) {


            reverseMap.putAll(map.subMap(firstKey,true, (firstKey + 4),true));
            return reverseMap;

        } else {
             reverseMap.putAll(map.subMap((lastKey - 4), true, lastKey, true));
             return reverseMap;
        }




    }
}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}