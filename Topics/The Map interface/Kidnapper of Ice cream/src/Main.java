import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner inputWords = new Scanner(System.in);

        String availableWords = inputWords.nextLine();
        String note = inputWords.nextLine();

        compareMap(putWords(availableWords), putWords(note));

    }


    public static Map<Integer, String> putWords(String words) {

        String[] arrWords = words.split(" ");


        Map<Integer, String> mapWords = new HashMap<>();

        for (int i = 0; i < arrWords.length; i++) {
            mapWords.put(i, arrWords[i]);
        }

        return mapWords;
    }


    public static void compareMap(Map<Integer, String> availableWord, Map<Integer, String> note) {


        int count = 0;
        int countSameValues = 0;
        while (count < note.size()) {

            if (availableWord.size() >= note.size() && availableWord.containsValue(note.get(count))) {
                countSameValues++;

            }
            count++;
        }
        if (countSameValues == note.size()) {
            System.out.println("You get money");
        } else {
            System.out.println("You are busted");
        }

    }

}

