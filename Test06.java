import java.util.Scanner;

public class Test06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入第一个数字: ");
        double num1 = scanner.nextDouble();
        System.out.print("请输入运算符(+、-、*、/): ");
        char operator = scanner.next().charAt(0);
        System.out.print("请输入第二个数字: ");
        double num2 = scanner.nextDouble();
        double result = 0;
        boolean valid = true;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("错误：除数不能为0");
                    valid = false;
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                System.out.println("错误：不支持的运算符");
                valid = false;
        }
        if (valid) {
            System.out.printf("计算结果: %.2f %c %.2f = %.2f\n",
                    num1, operator, num2, result);
        }
        scanner.close();
    }
}