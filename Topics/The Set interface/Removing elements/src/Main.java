import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        // write your code here

        String[] strArr = str.split(" ");
        Set<Integer> addToSet = new TreeSet<>();

        for (String s : strArr) {
            addToSet.add(Integer.parseInt(s));
        }


        return addToSet;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {

        // write your code here


        set.removeIf(n -> (n > 10));


    }


}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}