import java.util.Scanner;

public class Test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入圆的半径：");
        double R = sc.nextDouble();
        double area = Math.pow(R, 2) * Math.PI;
        System.out.println("半径为：" + R + "的圆的面积是：" + area);
        sc.close();
    }
}