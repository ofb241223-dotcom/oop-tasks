import java.util.Scanner;

public class Test09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("请输入一个数");
            double x = scanner.nextDouble();
            if (x < 0) {
                throw new NegativeNumberException("错误，输入的数字不能为负数！");
            }
            System.out.println("输入合法，数字为：" + x);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class NegativeNumberException extends Exception {
    public NegativeNumberException() {
    }

    public NegativeNumberException(String message) {
        super(message);
    }
}