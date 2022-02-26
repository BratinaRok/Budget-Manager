import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;


class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner input = new Scanner(System.in);

        // range borders
        int from = input.nextInt();
        int to = input.nextInt() + 1;

        //number of key-value pairs.
        int numberOfKeyValue = input.nextInt();

        SortedMap<Integer, String> map = new TreeMap<>();

        for (int i = 0; i < numberOfKeyValue; i++) {
            int key = input.nextInt();
            String value = input.next();
            map.put(key, value);


        }

        map = map.subMap(from, to);

        for (Map.Entry mapElement : map.entrySet()) {
            int key = (int) mapElement.getKey();
            String value = (String) mapElement.getValue();

            System.out.println(key + " " + value);
        }


    }
}