import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Test22 {
    public static void main(String[] args) {
        System.out.println("请输入一个字符串: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            map.put(input.charAt(i), map.getOrDefault(input.charAt(i), 0) + 1);
        }
        Set<Character> set = map.keySet();
        for (Character key : set) {
            System.out.println(key + ":" + map.get(key));
        }
        sc.close();
    }
}