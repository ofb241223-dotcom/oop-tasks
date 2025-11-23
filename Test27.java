import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Test27 {
    public static void main(String[] args) {
        System.out.println("请输入一组整数（用逗号分隔）：");
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(",");
        Set<Integer> set = new TreeSet<>();
        for (String x : s) {
            set.add(Integer.parseInt(x));
        }
        System.out.println("升序排序后的这组整数为：" + set);
        sc.close();
    }
}