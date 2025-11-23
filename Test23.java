import java.util.Arrays;

public class Test23 {
    public static void main(String[] args) {
        int[] array = new int[] { 2, 4, 1, 10, 6, 3, 8, 7, 9, 5 };
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println("排序后的整数数组是：" + Arrays.toString(array));
    }
}