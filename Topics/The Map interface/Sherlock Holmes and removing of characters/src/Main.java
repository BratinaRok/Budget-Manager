import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner input = new Scanner(System.in);

        String firstInput = input.nextLine().toLowerCase();
        String secondInput = input.nextLine().toLowerCase();

        compareMap(putMap(firstInput), putMap(secondInput));

    }


    public static Map<Character, Integer> putMap(String input) {


        Map<Character, Integer> map = new HashMap<>();


        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (map.containsKey(c)) {
                int cnt = map.get(c);
                map.put(c, ++cnt);
            } else {
                map.put(c, 1);
            }

        }
        //System.out.println(map);

        return map;
    }


    public static void compareMap(Map<Character, Integer> firstMap, Map<Character, Integer> secondMap) {

        int count = 0;
        int counter = 0;

        Iterator it = firstMap.entrySet().iterator();


        while (it.hasNext()) {
            Map.Entry item = (Map.Entry) it.next();
            char firstKey = (char) item.getKey();
            int firstValue = (int) item.getValue();

            if (secondMap.containsKey(firstKey)) {
                int secondValue = secondMap.get(firstKey);
                int newValue = firstValue - secondValue;
                count += newValue;
                firstMap.replace(firstKey, newValue);
                secondMap.replace(firstKey,newValue);


            } else {
                counter+= firstValue;
            }


        }
        //System.out.println(firstMap);


        Iterator itsec = secondMap.entrySet().iterator();

        while (itsec.hasNext()) {
            Map.Entry item = (Map.Entry) itsec.next();
            char secondKey = (char) item.getKey();
            int secondValue = (int) item.getValue();

            if (firstMap.containsKey(secondKey)) {
                int firstValue = firstMap.get(secondKey);
                int newValue = secondValue - firstValue;
                count += newValue;

                secondMap.replace(secondKey, newValue);
            } else {
                counter+= secondValue;
            }


        }

        //System.out.println(secondMap);

       // System.out.println(count);
        //System.out.println(counter);
        System.out.println(Math.abs(-count) + counter);
    }
}









