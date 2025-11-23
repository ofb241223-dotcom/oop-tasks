import java.util.HashSet;

public class Test16 {
    public static void main(String[] args) {
        int[] array1 = new int[] { 1, 2, 3, 4 };
        int[] array2 = new int[] { 2, 3, 4, 5, 6, 7 };
        HashSet<Integer> set1 = new HashSet<>();
        for (int x : array1) {
            set1.add(x);
        }
        for (int x : array2) {
            set1.add(x);
        }
        System.out.println("合并后的数组是：" + set1);
    }
}