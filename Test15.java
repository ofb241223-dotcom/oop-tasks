import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Test15 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一组数字，用空格分隔：");
        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();
            list.add(num);
        }
        HashSet<Integer> set = new HashSet<Integer>(list);
        System.out.println("去重后的结果：" + set);
    }
}