public class Test14 {
    public static void main(String[] args) {
        Integer[] arrayint = new Integer[] { 1, 2, 3, 4, 5 };
        Character[] arraychar = new Character[] { 'a', 'b', 'c', 'd', 'e' };
        System.out.println("arrayint中的最大值是：" + findMaxInArray(arrayint));
        System.out.println("arraychar中的最大值是：" + findMaxInArray(arraychar));
    }

    public static <T extends Comparable<T>> T findMaxInArray(T[] array) {
        if (array.length == 0 || array == null) {
            return null;
        }
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
}