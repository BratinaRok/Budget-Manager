import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner input = new Scanner(System.in);

        String firstWord = input.nextLine().toLowerCase();
        String secondWord = input.nextLine().toLowerCase();


       if(checkForAnagram(countChars(firstWord),countChars(secondWord))) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }


    public static Map<Character, Integer> countChars(String input) {
        Map<Character, Integer> mapWord = new HashMap<>();


        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (mapWord.containsKey(c)) {
                int cnt = mapWord.get(c);
                mapWord.put(c, ++cnt);
            } else {
                mapWord.put(c, 1);
            }
        }

        return mapWord;
    }

    public static boolean checkForAnagram(Map<Character, Integer> first, Map<Character, Integer> second) {
      return first.equals(second);


    }
}

